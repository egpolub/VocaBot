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
        UserDetails jwtUser = userDetailsService.loadUserByUsername(getUsernameFromToken(token));
        return new UsernamePasswordAuthenticationToken(jwtUser, "", jwtUser.getAuthorities());
    }

    /**
     * Generate JSON Web Token
     * @param username
     * @param roles - The user roles
     * @return token
     */
    public String generateToken(String username, List<Role> roles) {
        Claims claims = Jwts.claims().setSubject(username);
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
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        }
        catch (ExpiredJwtException e) {
            logger.warn("JWT is accepted after it expires", e);
        }
        catch (UnsupportedJwtException e) {
            logger.warn("JWT does not match the format expected by the application", e);
        }
        catch (MalformedJwtException e) {
            logger.warn("JWT is not correctly constructed", e);
        }
        catch (Exception e) {
            logger.warn("Token is not validity", e);
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
     * Get username from token
     * @param token - JSON Web Token
     * @return username
     */
    public String getUsernameFromToken(String token) {
        String username = Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token).getBody().getSubject();
        logger.info("From token get username={}", username);
        return username;

    }

    private List<String> getRoleNames(List<Role> userRoles) {
        List<String> result = new ArrayList<>();
        userRoles.forEach(role -> result.add(role.getName()));
        return result;
    }
}
