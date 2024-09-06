package com.example.login_signup.controller;

import com.example.login_signup.converter.UserConverter;
import com.example.login_signup.entity.User;
import com.example.login_signup.model.IdJson;
import com.example.login_signup.model.UserJson;
import com.example.login_signup.service.SignupService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.security.NoSuchAlgorithmException;

@RestController
public class SignupController {
    @Autowired
    private SignupService service;
    @Autowired
    private UserConverter converter;



    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public IdJson signup(@RequestBody UserJson userJson) throws NoSuchAlgorithmException {
        User user = this.service.signup(this.converter.convertToEntity(new User(), userJson));
        return new IdJson(user.getId());
    }

}
