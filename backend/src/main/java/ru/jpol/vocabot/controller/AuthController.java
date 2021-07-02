package ru.jpol.vocabot.controller;

import io.tej.SwaggerCodgen.api.AuthApi;
import io.tej.SwaggerCodgen.model.AuthInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.jpol.vocabot.config.AdditionalSecurityConfig;
import ru.jpol.vocabot.entity.User;
import ru.jpol.vocabot.security.jwt.JwtProvider;
import ru.jpol.vocabot.service.restImpl.UserServiceImpl;
import ru.jpol.vocabot.utils.RestUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
public class AuthController implements AuthApi {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final JwtProvider jwtProvider;
    private final UserServiceImpl userService;
    private final AdditionalSecurityConfig securityConfig;

    @Autowired
    public AuthController(JwtProvider jwtProvider, UserServiceImpl userService, AdditionalSecurityConfig securityConfig) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.securityConfig = securityConfig;
    }

    @Override
    public ResponseEntity<Void> registerUser(AuthInfo authInfo) {
        if (authInfo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (authInfo.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID in request body can not be null or empty");
        }
        if (authInfo.getHash() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hash is a required field");
        }
        String hash;
        try {
            String dataCheckString = RestUtils.fieldSeparator(authInfo);
            hash = RestUtils.encode(securityConfig.getToken(), dataCheckString);
        } catch (IllegalAccessException e) {
            logger.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect request body");
        } catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!authInfo.getHash().equals(hash)) {
            logger.info("Forbidden telegram authentication for authInfo with id={}", authInfo.getId());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        if (userService.findUser(authInfo.getId()) != null) {
            // TODO if user exists in the system?
        }
        User user = new User();
        BeanUtils.copyProperties(authInfo, user);
        userService.createUser(user);

        String token = jwtProvider.generateToken(user.getId().toString(), user.getRoles());

        return ResponseEntity.ok().header("Authentication", "Bearer_" + token).build();
    }
}
