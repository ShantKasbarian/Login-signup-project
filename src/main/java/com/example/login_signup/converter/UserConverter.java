package com.example.login_signup.converter;

import com.example.login_signup.entity.User;
import com.example.login_signup.model.UserJson;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserJson> {
    @Override
    public User convertToEntity(User user, UserJson userJson) {
        user.setId(userJson.getId());
        user.setName(userJson.getName());
        user.setEmail(userJson.getEmail());
        user.setPassword(userJson.getPassword());
        return user;
    }

    @Override
    public UserJson convertToModel(User user, UserJson userJson) {
        userJson.setId(user.getId());
        userJson.setName(user.getName());
        userJson.setEmail(user.getEmail());
        userJson.setPassword(user.getPassword());
        return userJson;
    }
}
