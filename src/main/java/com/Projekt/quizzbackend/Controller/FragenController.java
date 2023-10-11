package com.Projekt.quizzbackend.Controller;

import com.Projekt.quizzbackend.Dao.DTO.FragenMapper;
import com.Projekt.quizzbackend.Dao.DTO.Templates.FrageErstellen;
import com.Projekt.quizzbackend.Dao.FragenRepository;
import com.Projekt.quizzbackend.Dao.TeamsRepository;
import com.Projekt.quizzbackend.Dao.UserRepository;
import com.Projekt.quizzbackend.Quiz.Fragen;
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
@RequestMapping("/api/quiz")

public class FragenController {

    @Autowired
    private FragenRepository fragenRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final TeamsRepository teamsRepository;

    public FragenController(UserRepository userRepository, TeamsRepository teamsRepository, FragenRepository fragenRepository) {
        this.userRepository = userRepository;
        this.teamsRepository = teamsRepository;
        this.fragenRepository = fragenRepository;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/frageErstellen")

    public ResponseEntity<?> FrageErstellen(@RequestBody FrageErstellen frageErstellen) {
        User user = userRepository.findByUserName(frageErstellen.getUsername());

        if (user != null && passwordEncoder.matches(frageErstellen.getPassword(), user.getPassword())) {
            frageErstellen.setUser(user);
            Fragen fragen = FragenMapper.toEntity(frageErstellen);  // Stellen Sie sicher, dass der Mapper die User-ID setzt
            fragen.setUser(user);
            fragenRepository.save(fragen);  // Speichern der Frage


            return ResponseEntity.ok().build();

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}