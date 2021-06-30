package ru.jpol.vocabot.service.restImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jpol.vocabot.entity.Role;
import ru.jpol.vocabot.entity.User;
import ru.jpol.vocabot.repository.RoleRepository;
import ru.jpol.vocabot.repository.UserRepository;
import ru.jpol.vocabot.service.RoleService;
import ru.jpol.vocabot.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserServiceImpl implements UserService, RoleService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String roleUser = "ROLE_USER";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findUserByName(String userName) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            logger.warn("Could not find user by username: {}", userName);
        }
        else {
            logger.info("Found user: {} by id: {}", user.getUsername(), user.getId());
        }
        return user;
    }

    @Override
    public Role findRole(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            logger.warn("Could not find role by name: {}", name);
        }
        else {
            logger.info("Found role: {}", role.getName());
        }
        return role;
    }


    @Override
    public List<User> findAllUser() {
        List<User> users = userRepository.findAll();
        logger.info("Found {} users", users.size());

        return users;
    }

    @Override
    public User findUser(Long id) {
        User user = null;
        try {
            user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
            logger.info("Found user: {} by id: {}", user.getUsername(), user.getId());
        } catch (NoSuchElementException e) {
            logger.warn("Could not find user by id: {}", id);
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
        logger.info("User with id: {} added", user.getId());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        logger.info("User by id: {} deleted", id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
        logger.info("User by id: {} updated", user.getId());
    }
}
