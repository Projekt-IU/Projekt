package com.Projekt.quizzbackend.Controller;

import com.Projekt.quizzbackend.Dao.DTO.FragenMapper;
import com.Projekt.quizzbackend.Dao.DTO.Templates.FrageErstellen;
import com.Projekt.quizzbackend.Dao.DTO.Templates.FragenSendDTO;
import com.Projekt.quizzbackend.Dao.FragenRepository;
import com.Projekt.quizzbackend.Dao.TeamsRepository;
import com.Projekt.quizzbackend.Dao.UserRepository;
import com.Projekt.quizzbackend.Quiz.FrageHolen;
import com.Projekt.quizzbackend.Quiz.Fragen;
import com.Projekt.quizzbackend.Team.ScoresTeam;
import com.Projekt.quizzbackend.Team.Teams;
import com.Projekt.quizzbackend.User.User;
import com.Projekt.quizzbackend.User.UserScore.ScoreUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

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

    @PostMapping("/frageHolen")
    public ResponseEntity<?> FrageHolen(@RequestBody FrageHolen frageHolen) {
        User user = userRepository.findByUserName(frageHolen.getUsername());

        if (user != null && passwordEncoder.matches(frageHolen.getPassword(), user.getPassword())) {
            List<Fragen> alleFragen;

            if (frageHolen.getModul() != null) {
                alleFragen = fragenRepository.findByModul(frageHolen.getModul());

            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Modul muss angegeben werden");
            }

            if (alleFragen.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Keine Fragen gefunden");
            }

            // Wählen Sie eine zufällige Frage aus
            Random rand = new Random();
            Fragen zufaelligeFrage = alleFragen.get(rand.nextInt(alleFragen.size()));

            // Konvertieren Sie die Frage in ein DTO
            FragenSendDTO fragenSendDTO = FragenMapper.toSendDTO(zufaelligeFrage);
            ScoreUser scoreUser = user.getScoreUser();
            scoreUser.setFragenGesamt(scoreUser.getFragenGesamt() + 1);
            userRepository.save(user);

            if (user.getTeam() != null)

            {
                Teams team = user.getTeam();
                ScoresTeam scoreTeam = team.getScoreTeam();
                scoreTeam.setFragenGesamt(scoreTeam.getFragenGesamt() + 1);
                teamsRepository.save(team);
            }

            return ResponseEntity.ok(fragenSendDTO);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/frageBeantworten")

    public ResponseEntity<?> FrageBeantworten(@RequestBody FrageErstellen frageErstellen) {
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