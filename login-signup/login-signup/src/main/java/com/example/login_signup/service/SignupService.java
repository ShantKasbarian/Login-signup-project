package com.example.signup.service;

import com.example.signup.entity.User;
import com.example.signup.repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SignupService {
    private UserRepo repo;

    @Autowired
    public SignupService(UserRepo repo) {
        this.repo = repo;
    }

    public User signup(User user) throws NoSuchAlgorithmException {
        if (this.repo.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("email has already been used");
        }

        if (!isEmailValid(user.getEmail())) {
            throw new RuntimeException("email is invalid");
        }

        if (!isPasswordValid(user.getPassword())) {
            throw new RuntimeException("password is invalid");
        }

        if (user.getName() == null) {
            throw new RuntimeException("name must be specified");
        }
        String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        user.setPassword(pw_hash);

        return this.repo.save(user);
    }

    private boolean isEmailValid(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isPasswordValid(String password) {
        if (password.length() < 6) {
            throw new RuntimeException("password must be at least 6 characters long");
        }

        Pattern numberPattern = Pattern.compile("[0-9]");
        Pattern uppercasePattern = Pattern.compile("[A-Z]");
        Pattern lowercasePattern = Pattern.compile("[a-z]");
        Pattern specialCharacterPattern = Pattern.compile("[$&+,:;=?@#|'<>.-^*()%!]");

        Matcher number = numberPattern.matcher(password);
        Matcher uppercase = uppercasePattern.matcher(password);
        Matcher lowercase = lowercasePattern.matcher(password);
        Matcher specialCharacter = specialCharacterPattern.matcher(password);

        boolean hasNumber = number.find();
        boolean hasUppercase = uppercase.find();
        boolean hasLowercase = lowercase.find();
        boolean hasSpecialCharacter = specialCharacter.find();

        int numberCount = 0;
        int uppercaseCount = 0;
        int lowercaseCount = 0;
        int specialCharacterCount = 0;

        for (int i = 0; i < password.length(); i++) {
            if (
                    (numberCount + uppercaseCount + lowercaseCount + specialCharacterCount) == 4
            ) {
                return true;
            }
            if (hasNumber && numberCount == 0) {
                numberCount++;
            }
            if (hasUppercase && uppercaseCount == 0) {
                uppercaseCount++;
            }
            if (hasLowercase && lowercaseCount == 0) {
                lowercaseCount++;
            }
            if (hasSpecialCharacter && specialCharacterCount == 0) {
                specialCharacterCount++;
            }
        }
        return false;
    }

}
