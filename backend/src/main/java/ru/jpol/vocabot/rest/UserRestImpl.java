package ru.jpol.vocabot.rest;

import io.tej.SwaggerCodgen.api.UserApi;
import io.tej.SwaggerCodgen.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.jpol.vocabot.dao.impl.UserDaoImpl;
import ru.jpol.vocabot.entity.User;
import ru.jpol.vocabot.exception.CustomDuplicateKeyDaoException;

import java.time.ZoneOffset;

@RestController
public class UserRestImpl implements UserApi{
    private static final Logger logger = LoggerFactory.getLogger(UserRestImpl.class);

    private final UserDaoImpl userService;

    @Autowired
    public UserRestImpl(UserDaoImpl userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserInfo> getUserByUserId(Long userId) {
        logger.info("Request getUserByUserId() with userId={}", userId);

        User user = userService.getUser(userId);
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
    public ResponseEntity<Void> updateUserByUserId(Long userId, UserInfo userInfo) {
        logger.info("Request updateUserByUserId() with userId={}", userId);

        if (userInfo.getUserId() != null && !userInfo.getUserId().equals(userId))
        {
            String message = "UserId in the path and in the request body should be the same";
            logger.warn(message);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }

        User user = userService.getUser(userId);
        if (user == null) {
            String message = String.format("User with userId=%d not found", userId);
            logger.warn(message);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }

        BeanUtils.copyProperties(userInfo, user, "userId");

        try {
            userService.updateUser(user);
        } catch (CustomDuplicateKeyDaoException e) {
            // TODO throw exception
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> createUser(UserInfo userInfo) {
        logger.info("Request createUser() with userId={}", userInfo.getUserId());

        if (userInfo.getUserId() == null) {
            String message = "Invalid value of the request body, userId is empty";
            logger.warn(message);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
        if (userService.getUser(userInfo.getUserId()) != null) {
            String message = String.format("User with userId=%d already exists", userInfo.getUserId());
            logger.warn(message);
            throw new ResponseStatusException(HttpStatus.CONFLICT, message);
        }

        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        try {
            if (!userService.createUser(user)) {

            };
        }
        catch (CustomDuplicateKeyDaoException e) {
            // add error description
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
