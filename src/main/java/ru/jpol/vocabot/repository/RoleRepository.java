package ru.jpol.vocabot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jpol.vocabot.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
