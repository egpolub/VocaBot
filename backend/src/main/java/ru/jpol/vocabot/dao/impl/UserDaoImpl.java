package ru.jpol.vocabot.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.jpol.vocabot.dao.UserDao;
import ru.jpol.vocabot.dao.repository.RoleRepository;
import ru.jpol.vocabot.entity.Role;
import ru.jpol.vocabot.entity.User;
import ru.jpol.vocabot.exception.CustomDuplicateKeyDaoException;
import ru.jpol.vocabot.exception.CustomUserRolesRelationDaoException;
import ru.jpol.vocabot.utils.DaoUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private static final String TABLE_USER_NAME = "users";
    private static final String TABLE_USER_ROLES_NAME = "users_roles";

    private final RoleRepository roleRepository;

    @Value("${spring.flyway.schemas}")
    private String DEFAULT_SCHEMA_NAME;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, RoleRepository roleRepository) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getListUsers() {
        List<User> users = Collections.emptyList();
        try {
            String sql = "SELECT * FROM " + DaoUtils.getExtendedTableName(DEFAULT_SCHEMA_NAME, TABLE_USER_NAME);

            users = namedParameterJdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(User.class));

            logger.info("Found count={} users", users.size());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
        return users;
    }

    @Override
    public User getUser(Long userId) {
        User user = null;
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("userId", userId);

            String sql = "SELECT * FROM " +
                    DaoUtils.getExtendedTableName(DEFAULT_SCHEMA_NAME, TABLE_USER_NAME) +
                    " WHERE " +
                    "user_id = :userId";

            user = namedParameterJdbcTemplate.queryForObject(
                    sql,
                    namedParameters,
                    new BeanPropertyRowMapper<>(User.class));

            assert user != null;
            logger.info("Found user with username={} by userId={}", user.getUsername(), user.getUserId());
        } catch (EmptyResultDataAccessException e) {
            logger.warn("User with userId={} does not exist in the system", userId);
        }
        return user;
    }

    @Transactional
    @Override
    public boolean createUser(User user) throws CustomDuplicateKeyDaoException {
        if (user == null) {
            throw new IllegalArgumentException("Parameter 'user' cannot be null");
        }
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setUpdated(user.getCreated());
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("userId", user.getUserId())
                    .addValue("username", user.getUsername())
                    .addValue("firstname", user.getFirstname())
                    .addValue("email", user.getEmail())
                    .addValue("created", user.getCreated())
                    .addValue("updated", user.getUpdated());

            String sql = "INSERT INTO " +
                    DaoUtils.getExtendedTableName(DEFAULT_SCHEMA_NAME, TABLE_USER_NAME) +
                    " (user_id, username, firstname, email, created, updated)" +
                    " VALUES (:userId, :username, :firstname, :email, :created, :updated )";

            int rows = namedParameterJdbcTemplate.update(
                    sql,
                    namedParameters);
            if (rows == 1) {
                // add ROLE_USER to user
                addRoleToUser(user);
                // add relation between roleId and userId
                addUserRolesRelation(user);

                logger.info("Added user with userId={}", user.getUserId());

                return true;
            }
        } catch (DuplicateKeyException e) {
            String errorMessage = String.format("Could not create user with duplicated userId=%d || email=%s",
                    user.getUserId(), user.getEmail());
            logger.warn(errorMessage);
            throw new CustomDuplicateKeyDaoException(errorMessage);
        } catch (CustomUserRolesRelationDaoException e2) {
            // do nothing, looks like impossible behavior
        } catch (DataAccessException e3) {
            logger.error(e3.getMessage(), e3);
        }
        logger.warn("Could not add user with userId={}", user.getUserId());

        return false;
    }

    @Override
    public boolean deleteUser(Long userId) {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("userId", userId);

            String sql = "DELETE FROM " +
                    DaoUtils.getExtendedTableName(DEFAULT_SCHEMA_NAME, TABLE_USER_NAME) +
                    " WHERE user_id = :userId";
            int rows = namedParameterJdbcTemplate.update(
                    sql,
                    namedParameters);
            if (rows == 1) {
                logger.info("Deleted user by userId={}", userId);
                return true;
            }
        } catch (DataAccessException e) {
            logger.error(e.getMessage(), e);
        }
        logger.warn("Could not delete user by userId={}", userId);

        return false;
    }

    @Override
    public boolean updateUser(User user) throws CustomDuplicateKeyDaoException {
        if (user == null) {
            throw new IllegalArgumentException("Parameter 'user' cannot be null");
        }

        user.setUpdated(new Timestamp(System.currentTimeMillis()));
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("userId", user.getUserId())
                    .addValue("username", user.getUsername())
                    .addValue("firstname", user.getFirstname())
                    .addValue("email", user.getEmail())
                    .addValue("created", user.getCreated())
                    .addValue("updated", user.getUpdated());

            String sql = "UPDATE " +
                    DaoUtils.getExtendedTableName(DEFAULT_SCHEMA_NAME, TABLE_USER_NAME) +
                    " SET username = :username," +
                    "firstname = :firstname," +
                    "email = :email," +
                    "created = :created," +
                    "updated = :updated" +
                    " WHERE user_id = :userId";
            int rows = namedParameterJdbcTemplate.update(
                    sql,
                    namedParameters);
            if (rows == 1) {
                logger.info("Updated user by userId={}", user.getUserId());
                return true;
            }
        } catch (DuplicateKeyException e) {
            String errorMessage = String.format("Could not update user with userId=%d due to duplicated email=%s",
                    user.getUserId(), user.getEmail());
            logger.warn(errorMessage);
            throw new CustomDuplicateKeyDaoException(errorMessage);
        } catch (DataAccessException e2) {
            logger.error(e2.getMessage(), e2);
        }
        logger.warn("Could not update user with userId={}", user.getUserId());

        return false;
    }

    private void addRoleToUser(User user) {
        if (user.getRoles() == null) {
            List<Role> roles = new ArrayList<>(1);
            roles.add(roleRepository.getRoleUser());
            user.setRoles(roles);
        }
    }

    private void addUserRolesRelation(User user) throws CustomUserRolesRelationDaoException {
        Long userId = user.getUserId();
        List<Role> roles = user.getRoles();
        try {
            if (userId != null && roles != null) {
                String sql = "INSERT INTO " +
                        DaoUtils.getExtendedTableName(DEFAULT_SCHEMA_NAME, TABLE_USER_ROLES_NAME) +
                        " (user_id, role_id)" +
                        " VALUES (:userId, :roleId )";
                roles.forEach(role -> {
                    SqlParameterSource namedParameters = new MapSqlParameterSource()
                            .addValue("userId", userId)
                            .addValue("roleId", role.getRoleId());
                    try {
                        namedParameterJdbcTemplate.update(
                                sql,
                                namedParameters);

                    } catch (DataAccessException e) {
                        logger.error("Could not add relation between userId={} and roleId={}",userId, role.getRoleId(), e);
                        throw e;
                    }
                });
            }
        } catch (DataAccessException e) {
            throw new CustomUserRolesRelationDaoException(e);
        }
    }
}
