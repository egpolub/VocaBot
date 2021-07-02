package ru.jpol.vocabot.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.jpol.vocabot.config.JwtSecurityConfig;
import ru.jpol.vocabot.entity.Role;
import ru.jpol.vocabot.security.SecurityUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    private JwtSecurityConfig jwtConfig;
    private SecurityUserDetailsService userDetailsService;

    @Autowired
    public JwtProvider(JwtSecurityConfig jwtConfig, SecurityUserDetailsService userDetailsService) {
        this.jwtConfig = jwtConfig;
        this.userDetailsService = userDetailsService;
    }

    /**
     *
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {
        UserDetails jwtUser = userDetailsService.loadUserById(getUserIdFromToken(token));
        return new UsernamePasswordAuthenticationToken(jwtUser, "", jwtUser.getAuthorities());
    }

    /**
     * Generate JSON Web Token
     * @param id - user ID
     * @param roles - The user roles
     * @return token
     */
    public String generateToken(String id, List<Role> roles) {
        Claims claims = Jwts.claims().setSubject(id);
        claims.put("roles", getRoleNames(roles));

        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtConfig.getExpiredTime());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret())
                .compact();
    }

    /**
     * Checks the validity of the token
     * @param token
     * @return true - if the token has not expired, otherwise - false
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        }
        catch (UnsupportedJwtException | MalformedJwtException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    /**
     * Check several conditions for {@link HttpServletRequest request}:
     * <ul>
     * <li>The request has header - "Authorization"</li>
     * <li>The header with key - "Authorization" has value starting with - "Bearer_"</li>
     * </ul>
     * @param request
     * @return token - if request is valid, otherwise - null
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Get user ID from token
     * @param token - JSON Web Token
     * @return id
     */
    public String getUserIdFromToken(String token) {
        String id = Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token).getBody().getSubject();
        logger.info("From token get user id={}", id);
        return id;

    }

    private List<String> getRoleNames(List<Role> userRoles) {
        List<String> result = new ArrayList<>();
        userRoles.forEach(role -> result.add(role.getName()));
        return result;
    }
}
