package ru.jpol.vocabot.dao;

import ru.jpol.vocabot.entity.User;

import java.util.List;

public interface UserService {
    /**
     * Find list of users
     *
     * @return list of users, empty list - if not found users
     */
    List<User> findAllUser();

    /**
     * Find user by userId
     *
     * @param userId - user id
     * @return user - if present in the system, null if not found user
     */
    User findUser(Long userId);

    /**
     * Find user by user name
     *
     * @param userName - user name
     * @return user - if present in the system, null if not found user
     */
    User findUserByName(String userName);

    void createUser(User user);

    void deleteUser(Long userId);

    void updateUser(User user);
}
