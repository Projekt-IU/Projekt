package com.Projekt.quizzbackend.API;

import com.Projekt.quizzbackend.Dao.TeamsRepository;
import com.Projekt.quizzbackend.Dao.UserRepository;
import com.Projekt.quizzbackend.Team.Teams;
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
    @Autowired
    private final TeamsRepository teamsRepository;
    //Nur zu testzwecken. Ruft unter /api/data die Nutzerdaten ab
    @Autowired
    public ApiTest(UserRepository repository, TeamsRepository teamsRepository) {
        this.repository = repository;
        this.teamsRepository = teamsRepository;
    }


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

    @GetMapping("/Team")
    public ResponseEntity<List<Teams>> getDataTeam() {

        List<Teams> teamsList = (List<Teams>) teamsRepository.findAll();

        System.out.println("frage daten ab: abgeschlossen");
        if (teamsList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(teamsList);
        }
    }
}

