package com.Projekt.quizzbackend.Controller;

import com.Projekt.quizzbackend.Dao.DTO.TeamMapper;
import com.Projekt.quizzbackend.Dao.DTO.Templates.TeamDTO;
import com.Projekt.quizzbackend.Dao.TeamsRepository;
import com.Projekt.quizzbackend.Dao.UserRepository;
import com.Projekt.quizzbackend.Team.Teams;
import com.Projekt.quizzbackend.User.Login.AuthRequest;
import com.Projekt.quizzbackend.User.Login.FilterLogin;
import com.Projekt.quizzbackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/score")

public class ScoreController {

    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private final UserRepository repository;
    @Autowired
    private final TeamsRepository teamsRepository;
    public ScoreController(UserRepository repository, TeamsRepository teamsRepository) {
        this.repository = repository;
        this.teamsRepository = teamsRepository;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/getScoreUser")
    public ResponseEntity<?> getScoreUser(@RequestBody AuthRequest authRequest) {
        System.out.println("Anfrage für Score für user : " + authRequest.getUsername());
        authRequest = FilterLogin.filterLogin(authRequest);

        User user = repository.findByUserName(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {


            return ResponseEntity.ok(user.getScoreUser());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/getScoreTeam")
    public ResponseEntity<?> getScore(@RequestBody AuthRequest authRequest) {
        System.out.println("Anfrage für Score für Team : " + authRequest.getAnfrageName());

        authRequest = FilterLogin.filterLogin(authRequest);

        User user = repository.findByUserName(authRequest.getUsername());
        Teams teams = teamsRepository.findByName(authRequest.getAnfrageName());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {


            TeamDTO dto1 = teamMapper.convertToDTO(teams, true);
            return ResponseEntity.ok(dto1.getScoreTeam());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    }
