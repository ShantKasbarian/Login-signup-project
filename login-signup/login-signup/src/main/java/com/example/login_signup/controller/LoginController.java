package com.example.signup.controller;

import com.example.signup.converter.UserConverter;
import com.example.signup.entity.User;
import com.example.signup.model.UserJson;
import com.example.signup.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private LoginService service;
    private UserConverter converter;

    @Autowired
    public LoginController(LoginService service) {
        this.service = service;
        this.converter = new UserConverter();
    }

    @GetMapping("/login")
    public CsrfToken login(HttpServletRequest request, @RequestBody UserJson userJson) {
        User user = this.service.login(this.converter.convertToEntity(new User(), userJson));
        return getToken(request);
    }

    public CsrfToken getToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
