package ru.jpol.vocabot.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;
import ru.jpol.vocabot.VocaBotApplicationTest;
import ru.jpol.vocabot.entity.User;
import ru.jpol.vocabot.exception.CustomDuplicateKeyDaoException;

import java.util.List;


public class UserDaoTest extends VocaBotApplicationTest {

    @Test
    public void testCreateUser() throws Exception{
        User user = new User();
        user.setUserId(0L);
        user.setUsername("test_username");
        user.setFirstname("test_firstname");
        user.setEmail("test@test.com");

        Assertions.assertTrue(userDao.createUser(user));

        User createdUser = userDao.getUser(user.getUserId());
        Assertions.assertNotNull(createdUser);
        Assertions.assertEquals(user.getUserId(), createdUser.getUserId());
        Assertions.assertEquals(user.getUsername(), createdUser.getUsername());
        Assertions.assertEquals(user.getFirstname(), createdUser.getFirstname());
        Assertions.assertEquals(user.getEmail(), createdUser.getEmail());
        Assertions.assertNotNull(createdUser.getCreated());
        Assertions.assertTrue(createdUser.getCreated().getTime() < System.currentTimeMillis());
        Assertions.assertEquals(createdUser.getUpdated().getTime(), createdUser.getCreated().getTime());

        // check roles relations
        createdUser = userRepository.findById(user.getUserId()).orElseThrow();
        Assertions.assertEquals(1, createdUser.getRoles().size());
        Assertions.assertEquals("ROLE_USER", createdUser.getRoles().get(0).getName());


        // try to add user with existent userId
        User user2 = new User();
        user2.setUserId(createdUser.getUserId());
        user2.setUsername("test_username2");
        user2.setFirstname("test_firstname2");
        user2.setEmail("test2@test.com");

        Assertions.assertThrows(CustomDuplicateKeyDaoException.class,
                () -> userDao.createUser(user2));

        // try to add user with existent email
        User user3 = new User();
        user3.setUserId(3L);
        user3.setUsername("test_username3");
        user3.setFirstname("test_firstname3");
        user3.setEmail(user.getEmail());

        Assertions.assertThrows(CustomDuplicateKeyDaoException.class,
                () -> userDao.createUser(user3));


        // try to add user with null username
        User user4 = new User();
        user4.setUserId(4L);
        user4.setUsername(null); // username cannot bu null according to design
        Assertions.assertFalse(userDao.createUser(user4));
    }

    @Test
    public void testFindUser() {
        config_01();

        List<User> users = userDao.getListUsers();
        Assertions.assertEquals(defaultListSize, users.size());

        // find user by userName
        Assertions.assertNotNull(userRepository.findByUsername("username0"));

        // user does not exist in the system
        Assertions.assertNull(userRepository.findByUsername("unknown username"));

        // user does not exist in the system
        Long unknownUserId = 123456L;
        Assertions.assertNull(userDao.getUser(unknownUserId));
    }

    @Test
    public void testUpdateUser() throws Exception{
        config_01();

        User user = userDao.getUser(0L);
        Assertions.assertEquals("test0@test.com", user.getEmail());

        // successful update
        user.setEmail("updatedTest@test.com");
        userDao.updateUser(user);

        User updatedUser = userDao.getUser(user.getUserId());
        Assertions.assertEquals(user.getEmail(), updatedUser.getEmail());

        // try to update by non-unique email
        User user2 = userDao.getUser(1L);
        user2.setEmail(user.getEmail()); // non unique email

        Assertions.assertThrows(CustomDuplicateKeyDaoException.class,
                () -> userDao.updateUser(user2));

        // try to update user with unknown userId
        User unknownUser = new User();
        unknownUser.setUsername("test username");
        unknownUser.setUserId(234234234L); // unknown userId
        Assertions.assertFalse(userDao.updateUser(unknownUser));
    }

    @Transactional
    @Test
    public void testDeleteUser() {
        config_01();

        TestTransaction.flagForCommit();
        TestTransaction.end();
        TestTransaction.start();

        Assertions.assertEquals(defaultListSize, userDao.getListUsers().size());
        Assertions.assertEquals(defaultListSize, roleRepository.getRoleUser().getUsers().size());

        User user = userRepository.findById(0L).orElseThrow();
        Assertions.assertTrue(roleRepository.getRoleUser().getUsers().contains(user));
        userDao.deleteUser(0L);

        TestTransaction.flagForCommit();
        TestTransaction.end();
        TestTransaction.start();

        // check that relation between role and user was removed
        Assertions.assertFalse(roleRepository.getRoleUser().getUsers().contains(user));
        Assertions.assertEquals(defaultListSize - 1, roleRepository.getRoleUser().getUsers().size());

        userDao.deleteUser(1L);
        userDao.deleteUser(2L);
        userDao.deleteUser(3L);
        userDao.deleteUser(4L);

        TestTransaction.flagForCommit();
        TestTransaction.end();
        TestTransaction.start();

        List<User> users = userDao.getListUsers();
        Assertions.assertEquals(0, users.size());
        Assertions.assertNull(userDao.getUser(1L));
        Assertions.assertTrue(userDao.getListUsers().isEmpty());
    }
}
