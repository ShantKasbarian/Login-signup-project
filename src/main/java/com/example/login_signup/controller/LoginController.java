package com.example.login_signup.controller;

import com.example.login_signup.converter.UserConverter;
import com.example.login_signup.entity.User;
import com.example.login_signup.model.UserJson;
import com.example.login_signup.service.JWTService;
import com.example.login_signup.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.security.NoSuchAlgorithmException;

@RestController
public class LoginController {
    @Autowired
    private LoginService service;
    @Autowired
    private UserConverter converter;
    @Autowired
    private JWTService jwtService;
    private String token;

    @GetMapping("/login")
    public String login(@RequestBody UserJson userJson) throws NoSuchAlgorithmException {
        User user = this.converter.convertToEntity(new User(), userJson);
        token = this.service.login(user);
        return token;
    }

    @GetMapping(value = "/")
    public String greet(HttpServletRequest request) {
        return "chal raha hai " + token;
    }
}
