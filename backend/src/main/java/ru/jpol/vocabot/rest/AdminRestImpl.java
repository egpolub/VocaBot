package ru.jpol.vocabot.rest;

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
import ru.jpol.vocabot.dao.restImpl.UserDao;
import ru.jpol.vocabot.entity.User;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminRestImpl implements AdminApi {
    private static final Logger logger = LoggerFactory.getLogger(AdminRestImpl.class);

    private final UserDao userService;

    @Autowired
    public AdminRestImpl(UserDao userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> deleteUserByUserId(Long userId) {
        logger.info("Request deleteUserByUserId() with userId={}", userId);

        if (userService.findUser(userId) == null) {
            String message = String.format("User with userId=%d not found", userId);
            logger.warn(message);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }

        userService.deleteUser(userId);

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
