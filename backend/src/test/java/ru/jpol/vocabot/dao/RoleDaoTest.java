package ru.jpol.vocabot.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.jpol.vocabot.VocaBotApplicationTest;
import ru.jpol.vocabot.entity.Role;

public class RoleDaoTest extends VocaBotApplicationTest {

    @Test
    public void testFindRole() {
        // ROLE_USER, ROLE_ADMIN must exist in table Roles
        Role userRole = roleRepository.findByName("ROLE_USER");
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");

        Assertions.assertEquals("ROLE_USER", userRole.getName());
        Assertions.assertEquals("ROLE_ADMIN", adminRole.getName());
        Assertions.assertNull(roleRepository.findByName("ROLE_UNKNOWN"));
    }
}
