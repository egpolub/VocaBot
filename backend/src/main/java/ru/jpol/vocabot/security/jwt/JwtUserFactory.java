package ru.jpol.vocabot.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.jpol.vocabot.entity.Role;
import ru.jpol.vocabot.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {
    public static JwtUserDetails create(User user) {
        return new JwtUserDetails(
                user.getId(),
                null,
                user.getFirstname(),
                user.getUsername(),
                user.getEmail(),
                mapToGrantedAuthority(user.getRoles()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthority(List<Role> roles) {
        return roles.stream().map(role ->
            new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
    }
}
