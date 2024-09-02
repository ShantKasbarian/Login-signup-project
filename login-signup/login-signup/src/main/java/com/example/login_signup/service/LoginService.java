package com.example.login_signup.service;

import com.example.login_signup.entity.User;
import com.example.login_signup.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private UserRepo repo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public LoginService(UserRepo repo) {
        this.repo = repo;
    }

    public User login(User currentUser) {
        User user = this.repo.findByEmail(currentUser.getEmail());

        if (
                user == null ||
                !BCrypt.checkpw(currentUser.getPassword(), user.getPassword())
        ) {
            throw new RuntimeException("bad credentials");
        }

        return currentUser;
    }
}
