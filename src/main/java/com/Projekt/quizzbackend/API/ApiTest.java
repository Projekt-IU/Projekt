package com.Projekt.quizzbackend.API;

import com.Projekt.quizzbackend.Dao.UserRepository;
import com.Projekt.quizzbackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api")
public class ApiTest {

//zum test der Datenbank
    private final UserRepository repository;

    //Nur zu testzwecken. Ruft unter /api/data die Nutzerdaten ab
    @Autowired
    public ApiTest(UserRepository repository) {
        this.repository = repository;}


    @GetMapping("/data")
    public ResponseEntity<List<User>> getData() {

        List<User> users = (List<User>) repository.findAll();

        System.out.println("frage daten ab: abgeschlossen");
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }
}

