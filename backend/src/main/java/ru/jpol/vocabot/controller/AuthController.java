package ru.jpol.vocabot.controller;

import io.tej.SwaggerCodgen.api.AuthApi;
import io.tej.SwaggerCodgen.model.AuthInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.jpol.vocabot.entity.User;
import ru.jpol.vocabot.security.jwt.JwtProvider;
import ru.jpol.vocabot.service.restImpl.UserServiceImpl;

@RestController
public class AuthController implements AuthApi {
    private final JwtProvider jwtProvider;
    private final UserServiceImpl userService;

    @Autowired
    public AuthController(JwtProvider jwtProvider, UserServiceImpl userService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> registerUser(AuthInfo authInfo) {
        if (authInfo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        // TODO Checking authorization for receiving date
        if (authInfo.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID in request body can not be null or empty");
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
