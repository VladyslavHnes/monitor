package com.eco.monitor.resource;

import com.eco.monitor.dto.UserDto;
import com.eco.monitor.request.SignUpUserRequest;
import com.eco.monitor.request.SignUpUserResponse;
import com.eco.monitor.service.TokenService;
import com.eco.monitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UsersResource {

    @Autowired
    public UserService userService;

    @Autowired
    public TokenService tokenService;

    @PostMapping(path = "/sign-up")
    public ResponseEntity<SignUpUserResponse> signUpUser(@RequestBody SignUpUserRequest request) throws Exception {
        UserDto user = userService.addUser(request.getEmail(), request.getPassword());

        String token = tokenService.createJWT(user.getId().toString());
        System.out.println("NEW USER WITH ID = " + user.getId() + " WAS REGISTERED");
        return ResponseEntity.ok(new SignUpUserResponse(token, user.getId()));
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<SignUpUserResponse> signInUser(@RequestBody SignUpUserRequest request) throws Exception {
        UserDto user = userService.getUser(request.getEmail(), request.getPassword());

        String token = tokenService.createJWT(user.getId().toString());
        System.out.println("NEW USER WITH ID = " + user.getId() + " WAS LOGGED IN");
        return ResponseEntity.ok(new SignUpUserResponse(token, user.getId()));
    }
}
