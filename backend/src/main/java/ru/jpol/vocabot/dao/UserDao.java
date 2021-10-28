package ru.jpol.vocabot.dao;

import ru.jpol.vocabot.entity.User;
import ru.jpol.vocabot.exception.CustomDuplicateKeyDaoException;

import java.util.List;

public interface UserDao {
    /**
     * Find list of users without Role mapping
     *
     * @return list of users, empty list - if not found users
     */
    List<User> getListUsers();

    /**
     * Find user by userId without Role mapping
     *
     * @param userId - user id
     * @return user - if present in the system, null if not found user
     */
    User getUser(Long userId);

    /**
     * Add user to the system with roles mapping
     *
     * @param user
     * @return true - if the user was added to the system, false - otherwise
     * @throws CustomDuplicateKeyDaoException if user id or email not unique
     */
    boolean createUser(User user) throws CustomDuplicateKeyDaoException;

    /**
     * Update an existing user in the system
     *
     * @param user
     * @return true - if the user was updated to the system, false - otherwise
     * @throws CustomDuplicateKeyDaoException if email not unique
     */
    boolean updateUser(User user) throws CustomDuplicateKeyDaoException;

    /**
     * Delete an existing user from the system
     *
     * @param userId - user id
     * @return true - if the user was deleted from the system, false - otherwise
     */
    boolean deleteUser(Long userId);
}
