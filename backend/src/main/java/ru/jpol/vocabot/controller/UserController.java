package ru.jpol.vocabot.controller;

import io.tej.SwaggerCodgen.api.UserApi;
import io.tej.SwaggerCodgen.model.UserInfo;
import org.hibernate.PropertyValueException;
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
public class UserController implements UserApi{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> createUser(UserInfo userInfo) {
        logger.info(String.format("Request createUser() with user_id = %d", userInfo.getId()));

        if (userInfo.getId() == null) {
            String message = "Invalid value of the request body, id is empty";
            logger.error(message);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
        if (userService.findUser(userInfo.getId()) != null) {
            String message = String.format("User with id = %d already exists", userInfo.getId());
            logger.error(message);
            throw new ResponseStatusException(HttpStatus.CONFLICT, message);
        }

        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        try {
            userService.createUser(user);
        }
        catch (PropertyValueException e) {
            String message = String.format("User with id = %d already exists", user.getId());
            logger.error(message);
            throw new ResponseStatusException(HttpStatus.CONFLICT, message);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
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


    @Override
    public ResponseEntity<UserInfo> getUserById(Long id) {
        logger.info(String.format("Request getUserById() with id = %d", id));

        User user = userService.findUser(id);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }

        UserInfo resultUser = new UserInfo();

        BeanUtils.copyProperties(user, resultUser, "created", "updated");
        resultUser.setCreated(user.getCreated().toInstant().atOffset(ZoneOffset.UTC));
        resultUser.setUpdated(user.getUpdated().toInstant().atOffset(ZoneOffset.UTC));

        return ResponseEntity.ok(resultUser);
    }

    @Override
    public ResponseEntity<UserInfo> updateUserById(Long id, UserInfo userInfo) {
        logger.info(String.format("Request updateUserById() with id = %d, user_id = %d",
                id, userInfo.getId()));

        if (!id.equals(userInfo.getId())) {
            String message = "User id in the path and in the request body should be the same";
            logger.error(message);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }

        User user = userService.findUser(id);
        if (user == null) {
            String message = String.format("User with id = %d not found", id);
            logger.error(message);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }

        BeanUtils.copyProperties(userInfo, user, "id");

        userService.updateUser(user);

        return ResponseEntity.ok().build();
    }
}
