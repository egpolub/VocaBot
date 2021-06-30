package ru.jpol.vocabot.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.jpol.vocabot.config.JwtSecurityConfig;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {

    private JwtSecurityConfig jwtConfig;

    @Autowired
    public JwtProvider(JwtSecurityConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(String login) {
        Date date = Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
    }
}
