package ru.jpol.vocabot.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jpol.vocabot.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    String ROLE_USER = "ROLE_USER";
    String ROLE_ADMIN = "ROLE_ADMIN";

    Role findByName(String name);

    default Role getRoleUser() {
        return findByName(ROLE_USER);
    }

    default Role getRoleAdmin() {
        return findByName(ROLE_ADMIN);
    }
}
