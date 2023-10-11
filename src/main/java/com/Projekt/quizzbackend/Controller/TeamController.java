package com.Projekt.quizzbackend.Controller;

import com.Projekt.quizzbackend.Dao.DTO.TeamMapper;
import com.Projekt.quizzbackend.Dao.DTO.Templates.NewTeamDTO;
import com.Projekt.quizzbackend.Dao.DTO.Templates.TeamDTO;
import com.Projekt.quizzbackend.Dao.TeamsRepository;
import com.Projekt.quizzbackend.Dao.UserRepository;
import com.Projekt.quizzbackend.Team.Teams;
import com.Projekt.quizzbackend.User.Login.AuthRequest;
import com.Projekt.quizzbackend.User.User;
import jakarta.transaction.Transactional;
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


    @PostMapping("/getAllTeams")
    public ResponseEntity<?> getAllTeams(@RequestBody AuthRequest authRequest) {

        System.out.println("Frage Team ab: " + authRequest.getAnfrageName() +authRequest.getUsername() + authRequest.getPassword());

        User user = userRepository.findByUserName(authRequest.getUsername());
        System.out.println("User: " + user.getUserName());

        Iterable<Teams> teams = teamsRepository.findAll();
        Iterable<TeamDTO> teamDTOs = teamMapper.convertToDTO(teams);
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {

            return ResponseEntity.ok(teamDTOs);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/getTeam")
    public ResponseEntity<?> getTeam(@RequestBody AuthRequest authRequest) {

        System.out.println("Frage Team ab: " + authRequest.getAnfrageName() +authRequest.getUsername() + authRequest.getPassword());

        User user = userRepository.findByUserName(authRequest.getUsername());
        System.out.println("User: " + user.getUserName());
        Teams teams = teamsRepository.findByName(authRequest.getAnfrageName());

        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            TeamDTO dto = teamMapper.entityToDTO(teams, true);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/addUser")
    @Transactional
    public ResponseEntity<?> addUser(@RequestBody AuthRequest authRequest) {

        System.out.println("Frage Team ab: " + authRequest.getAnfrageName() +authRequest.getUsername() + authRequest.getPassword());

        User user = userRepository.findByUserName(authRequest.getUsername());
        System.out.println("User: " + user.getUserName());
        Teams team = teamsRepository.findByName(authRequest.getAnfrageName());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        if (team == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            user.setTeam(team);
            userRepository.save(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    @PostMapping("/newTeam")
    @Transactional
    public ResponseEntity<?> newTeam(@RequestBody NewTeamDTO authRequest) {
        User user = userRepository.findByUserName(authRequest.getUsername());
        System.out.println("User: " + user.getUserName());


        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            if (teamsRepository.findByName(authRequest.getName()) == null)
            {
                Teams team = new Teams();
                team.setName(authRequest.getName());
                team.setStudiengang(authRequest.getStudiengang());
                team.setAdmin(user);

                teamsRepository.save(team);
                user.setTeam(team);
                userRepository.save(user);

                return ResponseEntity.ok().build();

            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


    //Test
    @PostMapping("/newTeamDemo")
    public ResponseEntity<?> newTeamDemo(@RequestBody AuthRequest authRequest) {
       User user = userRepository.findUserByUserID(1);

        Teams teams = new Teams ();
        teams.setTeamsId(1);
        teams.setName("Winners");
        teams.setStudiengang("Informatik");
        teams.setAdmin(user);
        teamsRepository.save(teams);



        return ResponseEntity.ok(teams);
    }}



