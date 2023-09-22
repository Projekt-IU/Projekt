package com.Projekt.quizzbackend.User.Logout;

import com.Projekt.quizzbackend.Dao.UserRepository;
import com.Projekt.quizzbackend.User.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class LogoutController {

    private final UserRepository repository;




    public LogoutController(UserRepository repository) {
        this.repository = repository;
    }





    @PostMapping("logout")
    public ResponseEntity<User> logout(@RequestBody LogoutRequest logoutRequest) {

        System.out.println(logoutRequest.getUserId()+ logoutRequest.getUsername());
        System.out.println("Loggout anfrage erhalten: " + logoutRequest.getUsername() + logoutRequest.getUserId());
        System.out.println(logoutRequest);
        User user = repository.findUserByUserIDAndUserName(logoutRequest.getUserId(), (logoutRequest.getUsername()));
        System.out.println(user);
        user.logout();
        return ResponseEntity.ok().body(null); // Erfolgreiche Logout-Antwort.
    }
}