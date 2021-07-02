package ru.jpol.vocabot.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.jpol.vocabot.entity.User;
import ru.jpol.vocabot.security.jwt.JwtUserDetails;
import ru.jpol.vocabot.security.jwt.JwtUserFactory;
import ru.jpol.vocabot.service.UserService;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(SecurityUserDetailsService.class);

    private final UserService userDetails;

    public SecurityUserDetailsService(UserService userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDetails.findUserByName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User with username=" + userName + " not found");
        }
        JwtUserDetails jwtUser = JwtUserFactory.create(user);
        logger.info("Create JWT user with username={}", jwtUser.getUsername());
        return jwtUser;
    }
}
