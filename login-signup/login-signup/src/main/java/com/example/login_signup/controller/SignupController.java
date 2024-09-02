package com.example.signup.controller;

import com.example.signup.converter.UserConverter;
import com.example.signup.entity.User;
import com.example.signup.model.IdJson;
import com.example.signup.model.UserJson;
import com.example.signup.service.SignupService;
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
    private SignupService service;
    private UserConverter converter;

    @Autowired
    private SignupController(SignupService service) {
        this.service = service;
        this.converter = new UserConverter();
    }

    @GetMapping(value = "/")
    public String greet(HttpServletRequest request) {
        return "chal raha hai " + request.getAttribute("_csrf");
    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public IdJson signup(@RequestBody UserJson userJson) throws NoSuchAlgorithmException {
        User user = this.service.signup(this.converter.convertToEntity(new User(), userJson));
        return new IdJson(user.getId());
    }

}
