package com.example.login_signup.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJson {
    private int id;
    private String name;
    private String email;
    private String password;
}
