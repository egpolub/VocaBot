package ru.jpol.vocabot.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.jpol.vocabot.entity.User;
import ru.jpol.vocabot.security.jwt.JwtUserDetails;
import ru.jpol.vocabot.security.jwt.JwtUserFactory;
import ru.jpol.vocabot.service.restImpl.UserServiceImpl;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(SecurityUserDetailsService.class);

    private final UserServiceImpl userDetails;

    @Autowired
    public SecurityUserDetailsService(UserServiceImpl userDetails) {
        this.userDetails = userDetails;
    }

    public UserDetails loadUserById(String _id) {
        Long id = Long.valueOf(_id); // XXX Is time to ignore NumberFormatException???
        User user = userDetails.findUser(id);
        if (user == null) {
            throw new UsernameNotFoundException("User with id=" + id + " not found");
        }
        JwtUserDetails jwtUser = JwtUserFactory.create(user);
        logger.info("Create JWT user with id={}", jwtUser.getId());
        return jwtUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDetails.findUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username=" + username + " not found");
        }
        JwtUserDetails jwtUser = JwtUserFactory.create(user);
        logger.info("Create JWT user with username={}", jwtUser.getUsername());
        return jwtUser;
    }
}
