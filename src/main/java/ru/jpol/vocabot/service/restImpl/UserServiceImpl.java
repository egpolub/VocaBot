package ru.jpol.vocabot.service.restImpl;

import ru.jpol.vocabot.entity.Role;
import ru.jpol.vocabot.entity.User;
import ru.jpol.vocabot.service.RoleService;
import ru.jpol.vocabot.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService, RoleService {
    @Override
    public Role findRole(String name) {
        return null;
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }

    @Override
    public User findUser(Long id) {
        return null;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}
