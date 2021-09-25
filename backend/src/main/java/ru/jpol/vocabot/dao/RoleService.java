package ru.jpol.vocabot.dao;

import ru.jpol.vocabot.entity.Role;

public interface RoleService {
    Role findRole(String name);
}
