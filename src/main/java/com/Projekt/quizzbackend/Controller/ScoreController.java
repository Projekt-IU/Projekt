package com.Projekt.quizzbackend.Controller;

import com.Projekt.quizzbackend.Dao.DTO.TeamMapper;
import com.Projekt.quizzbackend.Dao.DTO.Templates.TeamDTO;
import com.Projekt.quizzbackend.Dao.DTO.Templates.TeamScoreListDTO;
import com.Projekt.quizzbackend.Dao.DTO.Templates.UserScoreListDTO;
import com.Projekt.quizzbackend.Dao.DTO.UserMapper;
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

import java.util.Comparator;
import java.util.List;


@RestController
@RequestMapping("/api/score")

public class ScoreController {

    @Autowired
    private UserMapper userMapper;
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


    @PostMapping("/getScoreUserList")
    public ResponseEntity<?> getScoreUserList(@RequestBody AuthRequest authRequest) {
        System.out.println("Anfrage für Score für user : " + authRequest.getUsername());
        authRequest = FilterLogin.filterLogin(authRequest);

        if (authRequest.getAnfrageName()==null)

        {
            authRequest.setAnfrageName("all");
        }
//benötigt AnfrageName all, gesamt, monat, woche


        User user = repository.findByUserName(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {

            List<User> allUsers  = (List<User>) repository.findAll();  // Implementiere die Sortierung

            Comparator<User> comparator;
            switch (authRequest.getAnfrageName().toLowerCase()) {
                case "gesammt":
                    comparator = Comparator.comparing(u -> u.getScoreUser().getPunkteGesamt());
                    break;
                case "woche":
                    comparator = Comparator.comparing(u -> u.getScoreUser().getPunkteWoche());
                    break;
                case "monat":
                    comparator = Comparator.comparing(u -> u.getScoreUser().getPunkteMonat());
                    break;
                case "all":
                     comparator = Comparator.comparing((User u) -> u.getScoreUser().getPunkteGesamt())
                            .thenComparing(u -> u.getScoreUser().getPunkteMonat())
                            .thenComparing(u -> u.getScoreUser().getPunkteWoche());
                    break;
                default:
                    // Standard-Sortierung
                    comparator = Comparator.comparing(u -> u.getScoreUser().getPunkteGesamt());
            }

            // Sortiere Benutzer nach dem ausgewählten Kriterium
            allUsers.sort(comparator.reversed());

            List<UserScoreListDTO> sortedScores = userMapper.convertUserScoresToDTO(allUsers, authRequest.getAnfrageName());  // Implementiere die Konvertierung

            return new ResponseEntity<>(sortedScores, HttpStatus.OK);

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


            TeamDTO dto1 = teamMapper.entityToDTO(teams, true);
            return ResponseEntity.ok(dto1.getScoreTeam());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }



    @PostMapping("/getScoreTeamList")
    public ResponseEntity<?> getScoreTeamList(@RequestBody AuthRequest authRequest) {
        System.out.println("Anfrage für Score für user : " + authRequest.getUsername());
        authRequest = FilterLogin.filterLogin(authRequest);

        if (authRequest.getAnfrageName()==null)

        {
            authRequest.setAnfrageName("all");
        }
//benötigt AnfrageName all, gesamt, monat, woche


        User user = repository.findByUserName(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {

            List<Teams> allTeams  = (List<Teams>) teamsRepository.findAll();  // Implementiere die Sortierung

            Comparator<Teams> comparator;
            switch (authRequest.getAnfrageName().toLowerCase()) {
                case "gesammt":
                    comparator = Comparator.comparing(t -> t.getScoreTeam().getPunkteGesamt());
                    break;
                case "woche":
                    comparator = Comparator.comparing(t -> t.getScoreTeam().getPunkteWoche());
                    break;
                case "monat":
                    comparator = Comparator.comparing(t -> t.getScoreTeam().getPunkteMonat());
                    break;
                case "all":
                    comparator = Comparator.comparing((Teams t) -> t.getScoreTeam().getPunkteGesamt())
                            .thenComparing(t -> t.getScoreTeam().getPunkteMonat())
                            .thenComparing(t -> t.getScoreTeam().getPunkteWoche());
                    break;
                default:
                    // Standard-Sortierung
                    comparator = Comparator.comparing(t -> t.getScoreTeam().getPunkteGesamt());
            }

            // Sortiere Teams nach dem ausgewählten Kriterium
            allTeams.sort(comparator.reversed());

            // Implementiere die Konvertierung
            List<TeamScoreListDTO> sortedScores = teamMapper.convertTeamScoresToDTO(allTeams, authRequest.getAnfrageName());

            return new ResponseEntity<>(sortedScores, HttpStatus.OK);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }



    }
