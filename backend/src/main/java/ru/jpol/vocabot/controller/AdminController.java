package ru.jpol.vocabot.controller;

import io.tej.SwaggerCodgen.api.AdminApi;
import io.tej.SwaggerCodgen.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.jpol.vocabot.entity.User;
import ru.jpol.vocabot.service.restImpl.UserServiceImpl;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController implements AdminApi {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> deleteUserById(Long id) {
        logger.info(String.format("Request deleteUserById() with id = %d", id));

        if (userService.findUser(id) == null) {
            String message = String.format("User with id = %d not found", id);
            logger.error(message);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<UserInfo>> getListUsers() {
        logger.info("Request getListUsers()");

        List<User> users = userService.findAllUser();
        List<UserInfo> resultUsers = new ArrayList<>();

        users.forEach(user -> {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(user, userInfo, "created", "updated");
            userInfo.setCreated(user.getCreated().toInstant().atOffset(ZoneOffset.UTC));
            userInfo.setUpdated(user.getUpdated().toInstant().atOffset(ZoneOffset.UTC));
            resultUsers.add(userInfo);
        });

        return resultUsers.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(resultUsers);
    }
}
