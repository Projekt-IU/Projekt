package com.Projekt.quizzbackend.User.Login;

import com.Projekt.quizzbackend.Dao.UserRepository;
import com.Projekt.quizzbackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// regelt das Login
@RestController
public class LoginController {
    private final UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public LoginController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/api/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
    System.out.println("login anfrage erhalten: " + loginRequest.getUsername() +loginRequest.getPassword());
        loginRequest = FilterLogin.filterLogin(loginRequest);

        User user = repository.findByUserName(loginRequest.getUsername());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            user.login();
            System.out.println(user.isLoggedIn());

            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
