package com.Projekt.quizzbackend.API;

import com.Projekt.quizzbackend.Dao.DTO.TeamMapper;
import com.Projekt.quizzbackend.Dao.DTO.Templates.TeamDTO;
import com.Projekt.quizzbackend.Dao.DTO.Templates.UserDTO;
import com.Projekt.quizzbackend.Dao.DTO.UserMapper;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiTest {

//zum test der Datenbank
@Autowired
private UserMapper userMapper;
@Autowired
TeamMapper teamMapper;
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
    public ResponseEntity<List<?>> getData() {

        List<User> users = (List<User>) repository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<UserDTO> userDTOs = users.stream()
                    .map(userMapper::entityWithScoreToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(userDTOs);
        }
    }

    @GetMapping("/Team")
    public ResponseEntity<List<?>> getDataTeam() {

        List<Teams> teamsList = (List<Teams>) teamsRepository.findAll();

        System.out.println("frage daten ab: abgeschlossen");
        if (teamsList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<TeamDTO> teamlistDto = teamsList.stream()
                    .map(teams -> teamMapper.entityToDTO(teams, true)) // oder false, je nach Bedarf
                    .collect(Collectors.toList());
            return ResponseEntity.ok(teamlistDto);
        }
    }
}

