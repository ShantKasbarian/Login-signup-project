package com.example.login_signup.service;

import com.example.login_signup.entity.UserPrincipal;
import com.example.login_signup.entity.User;
import com.example.login_signup.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = repo.findByName(name);

        if (user == null) {
            throw new UsernameNotFoundException("bad credentials");
        }

        return new UserPrincipal(user);
    }
}
