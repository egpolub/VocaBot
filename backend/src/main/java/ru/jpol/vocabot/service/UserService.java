package ru.jpol.vocabot.service;

import ru.jpol.vocabot.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();

    /**
     * Find user by id
     * @param id - user id
     * @return null if not found user
     */
    User findUser(Long id);

    void createUser(User user);

    void deleteUser(Long id);
}
