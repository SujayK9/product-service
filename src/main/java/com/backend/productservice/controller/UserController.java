package com.backend.productservice.controller;

import com.backend.productservice.dtos.LoginRequestDto;
import com.backend.productservice.dtos.LogoutRequestDto;
import com.backend.productservice.dtos.UserDto;
import com.backend.productservice.model.Token;
import com.backend.productservice.service.UserService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.productservice.dtos.SignUpRequestDto;
import com.backend.productservice.model.User;

@RestController
@RequestMapping("/v1/users")
public class UserController {

private UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        
     String email = signUpRequestDto.getEmail();
     String password = signUpRequestDto.getPassword();
     String fullname = signUpRequestDto.getFullname();

        User user = userService.signUp(fullname, email, password);

        return ResponseEntity.ok(UserDto.from(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody LoginRequestDto loginRequestDto) {

     String email = loginRequestDto.getEmail();
     String password = loginRequestDto.getPassword();

        Token token = userService.login(email, password);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        userService.logout(logoutRequestDto.getToken());
        return ResponseEntity.ok("Logged out");
    }

    @PostMapping("/validateToken/{token}")
    public ResponseEntity<UserDto> validateToken(@PathVariable("token") String token) {
        return ResponseEntity.ok(UserDto.from(userService.validateToken(token)));
    }
}
