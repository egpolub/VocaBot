package ru.jpol.vocabot.dao.DaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jpol.vocabot.dao.RoleService;
import ru.jpol.vocabot.dao.UserService;
import ru.jpol.vocabot.dao.repository.RoleRepository;
import ru.jpol.vocabot.dao.repository.UserRepository;
import ru.jpol.vocabot.entity.Role;
import ru.jpol.vocabot.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserDaoImpl implements UserService, RoleService {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private static final String roleUser = "ROLE_USER";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public UserDaoImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findUserByName(String userName) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            logger.warn("Could not find user by username={}", userName);
        }
        else {
            logger.info("Found user with username={} by userId={}", user.getUsername(), user.getUserId());
        }
        return user;
    }

    @Override
    public Role findRole(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            logger.warn("Could not find role by name={}", name);
        }
        else {
            logger.info("Found role={}", role.getName());
        }
        return role;
    }


    @Override
    public List<User> findAllUser() {
        List<User> users = userRepository.findAll();
        logger.info("Found count={} users", users.size());

        return users;
    }

    @Override
    public User findUser(Long userId) {
        User user = null;
        try {
            user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
            logger.info("Found user with username={} by userId={}", user.getUsername(), user.getUserId());
        } catch (NoSuchElementException e) {
            logger.warn("Could not find user by userId={}", userId);
        }
        return user;
    }

    @Override
    public void createUser(User user) {
        Role role = findRole(roleUser);
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);

        userRepository.save(user);
        logger.info("User with userId={} added", user.getUserId());
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        logger.info("User by id={} deleted", userId);
    }

    public void updateUser(User user) {
        userRepository.save(user);
        logger.info("User by id={} updated", user.getUserId());
    }
}
