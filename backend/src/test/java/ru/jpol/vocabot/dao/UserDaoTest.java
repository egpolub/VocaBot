package ru.jpol.vocabot.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.jpol.vocabot.VocaBotApplicationTest;
import ru.jpol.vocabot.entity.Role;
import ru.jpol.vocabot.entity.User;

import java.util.List;


public class UserDaoTest extends VocaBotApplicationTest {

    @AfterEach
    public void cleanByTableName() {
        super.cleanUp("users");
    }

    @Test
    public void testFindRole() {
        /*
          ROLE_USER, ROLE_ADMIN must exist in table Roles
         */
        Role userRole = userService.findRole("ROLE_USER");
        Role adminRole = userService.findRole("ROLE_ADMIN");

        Assertions.assertNull(userService.findRole("ROLE_UNKNOWN"));

        Assertions.assertEquals("ROLE_USER", userRole.getName());
        Assertions.assertEquals("ROLE_ADMIN", adminRole.getName());


        Assertions.assertTrue(userRole.getCreated().getTime() < System.currentTimeMillis());
        Assertions.assertTrue(adminRole.getCreated().getTime() < System.currentTimeMillis());
        Assertions.assertEquals(userRole.getCreated(), userRole.getUpdated());
        Assertions.assertEquals(adminRole.getCreated(), adminRole.getUpdated());
    }

    @Test
    public void testFindUser() {
        config_01();

        List<User> users = userService.findAllUser();
        Assertions.assertEquals(defaultListSize, users.size());

        User testUser = new User();
        testUser.setId(0L);
        testUser.setUsername("username0");
        testUser.setFirstname("firstname0");
        testUser.setEmail("test0@test.com");

        User createdUser = userService.findUser(testUser.getId());
        Assertions.assertEquals(testUser.getId(), createdUser.getId());
        Assertions.assertEquals(testUser.getUsername(), createdUser.getUsername());
        Assertions.assertEquals(testUser.getEmail(), createdUser.getEmail());
        Assertions.assertEquals(testUser.getFirstname(), createdUser.getFirstname());
        Assertions.assertTrue(createdUser.getCreated().getTime() < System.currentTimeMillis());
        Assertions.assertTrue(createdUser.getUpdated().getTime() < System.currentTimeMillis());
        Assertions.assertEquals(1, createdUser.getRoles().size());
        Assertions.assertEquals("ROLE_USER", createdUser.getRoles().get(0).getName());
    }
    
    @Test
    public void testUpdateUser() {
        config_01();

        User user = userService.findUser(0L);
        user.setEmail("updatedTest@test.com");
        userService.updateUser(user);
        
        User updatedUser = userService.findUser(user.getId());
        Assertions.assertEquals(user.getId(), updatedUser.getId());
        Assertions.assertEquals(user.getUsername(), updatedUser.getUsername());
        Assertions.assertEquals(user.getEmail(), updatedUser.getEmail());
        Assertions.assertEquals(user.getFirstname(), updatedUser.getFirstname());
        Assertions.assertEquals(user.getCreated(), updatedUser.getCreated());
        Assertions.assertTrue(user.getUpdated().getTime() < updatedUser.getUpdated().getTime());
        Assertions.assertEquals(1, updatedUser.getRoles().size());
        Assertions.assertEquals("ROLE_USER", updatedUser.getRoles().get(0).getName());
    }

    @Test
    public void testDeleteUser() {
        config_01();

        Assertions.assertEquals(defaultListSize, userService.findAllUser().size());

        userService.deleteUser(0L);
        userService.deleteUser(1L);
        userService.deleteUser(2L);
        userService.deleteUser(3L);
        userService.deleteUser(4L);

        List<User> users = userService.findAllUser();
        Assertions.assertEquals(0, users.size());
        Assertions.assertNull(userService.findUser(1L));
        Assertions.assertTrue(userService.findAllUser().isEmpty());
    }

}
