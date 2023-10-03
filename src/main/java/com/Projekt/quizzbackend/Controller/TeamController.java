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
@RequestMapping("/api/Team")

public class TeamController {

    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final TeamsRepository teamsRepository;
    public TeamController(UserRepository userRepository, TeamsRepository teamsRepository) {
        this.userRepository = userRepository;
        this.teamsRepository = teamsRepository;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;




    @PostMapping("/getTeam")
    public ResponseEntity<?> getScoreUser(@RequestBody AuthRequest authRequest) {

        System.out.println("Frage Team ab: " + authRequest.getAnfrageName() +authRequest.getUsername() + authRequest.getPassword());
        authRequest = FilterLogin.filterLogin(authRequest);
        User user = userRepository.findByUserName(authRequest.getUsername());
        System.out.println("User: " + user.getUserName());
        Teams teams = teamsRepository.findByName(authRequest.getAnfrageName());

        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            TeamDTO dto = teamMapper.convertToDTO(teams, false);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


    @PostMapping("/newTeam")
    public ResponseEntity<?> getScore(@RequestBody AuthRequest authRequest) {
       User user = userRepository.findUserByUserID(1);

        Teams teams = new Teams ();
        teams.setTeamsId(1);
        teams.setName("Winners");
        teams.setStudiengang("Informatik");
        teams.setAdmin(user);
        teamsRepository.save(teams);



        return ResponseEntity.ok(teams);
    }}



