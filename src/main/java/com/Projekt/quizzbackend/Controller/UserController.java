package com.Projekt.quizzbackend.Controller;

import com.Projekt.quizzbackend.Dao.DTO.Templates.UserDTO;
import com.Projekt.quizzbackend.Dao.DTO.Templates.UserWithScore;
import com.Projekt.quizzbackend.Dao.DTO.UserMapper;
import com.Projekt.quizzbackend.Dao.TeamsRepository;
import com.Projekt.quizzbackend.Dao.UserRepository;
import com.Projekt.quizzbackend.Mail.EmailService;
import com.Projekt.quizzbackend.Mail.Mail;
import com.Projekt.quizzbackend.Team.Teams;
import com.Projekt.quizzbackend.User.Login.AuthRequest;
import com.Projekt.quizzbackend.User.Login.FilterLogin;
import com.Projekt.quizzbackend.User.Logout.LogoutRequest;
import com.Projekt.quizzbackend.User.Registation.Filter;
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

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/api")

public class UserController {

    @Autowired
    private final UserRepository repository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private final TeamsRepository teamsRepository;

    public UserController(UserRepository repository, TeamsRepository teamsRepository) {
        this.repository = repository;

        this.teamsRepository = teamsRepository;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;


    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody AuthRequest authRequest) {
        System.out.println("login anfrage erhalten: " + authRequest.getUsername() + authRequest.getPassword());

        User user = repository.findByUserName(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            user.login();
            System.out.println(user.isLoggedIn());

            UserDTO dto = userMapper.entityToDto(user);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("logout")
    public ResponseEntity<UserDTO> logout(@RequestBody LogoutRequest logoutRequest) {

        System.out.println(logoutRequest.getUserId() + logoutRequest.getUsername());
        System.out.println("Loggout anfrage erhalten: " + logoutRequest.getUsername() + logoutRequest.getUserId());
        System.out.println(logoutRequest);
        User user = repository.findUserByUserIDAndUserName(logoutRequest.getUserId(), (logoutRequest.getUsername()));
        System.out.println(user);
        user.logout();
        return ResponseEntity.ok().body(null); // Erfolgreiche Logout-Antwort.
    }

    @PostMapping("getProfil")
    public ResponseEntity<?> getProfil(@RequestBody AuthRequest authRequest) {

        System.out.println("Profieldaten angefrage " + authRequest.getUsername() + authRequest.getPassword());
        authRequest = FilterLogin.filterLogin(authRequest);

        User user = repository.findByUserName(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            UserWithScore userDto = userMapper.entityWithScoreToDto(user);
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/forgotPw")
    public ResponseEntity<Mail> mail(@RequestBody Mail email) {
        System.out.println("PW vergessen:  " + email);

        User user = repository.findByEmail(email.getEmail());

        if (user == null) {
            System.out.println("Mail ungültig ");
            // Benutzer mit der angegebenen E-Mail-Adresse wurde nicht gefunden
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        // Generiere ein temporäres Passwort
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        String tempPassword = Base64.getEncoder().encodeToString(bytes);

        // Verschlüssle und speichere das temporäre Passwort
        user.setPassword(passwordEncoder.encode(tempPassword));
        repository.save(user);

        // Sende das temporäre Passwort per E-Mail
        emailService.sendSimpleMessage(
                email.getEmail(),
                "Ihr temporäres Passwort",
                "Ihr temporäres Passwort lautet: " + tempPassword
        );

        return ResponseEntity.ok().build();
    }

    //neu
    @PostMapping("/newPw")
    public ResponseEntity<?> newPasswordMail(@RequestBody AuthRequest authRequest) {


        authRequest = FilterLogin.filterLogin(authRequest);

        User user = repository.findByUserName(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {


            // Verschlüssle und speichere das neue Passwort
            user.setPassword(passwordEncoder.encode(authRequest.getAnfrageName()));
            repository.save(user);
            System.out.println("Password geändert");
            return ResponseEntity.ok().build();
        } else {
            System.out.println("Mail ungültig ");
            // Benutzer mit der angegebenen E-Mail-Adresse wurde nicht gefunden
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


    @PostMapping("/changeUserName")
    public ResponseEntity<?> changeUserName(@RequestBody AuthRequest authRequest) {
        System.out.println("änderungsanfrage erhalten: " + authRequest.getUsername() + authRequest.getAnfrageName());
        authRequest = FilterLogin.filterLogin(authRequest);

        User user = repository.findByUserName(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword()) && repository.findByUserName(authRequest.getAnfrageName()) == null) {

            user.setUserName(authRequest.getAnfrageName());
            repository.save(user);
            UserDTO dto = userMapper.entityToDto(user);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("UserName bereits vergeben");
        }
    }

    @PostMapping(path = "/userRegistrieren")
    public ResponseEntity<User> registry(@RequestBody User user) {
        User registryUser = Filter.filterUser(user);
        System.out.println("Registrierungsanfrage:  " + user.getUserName());


        String rawPassword = user.getPassword();
        registryUser.setPassword(passwordEncoder.encode(rawPassword));
        System.out.println("Passwort verschlüsselt:  " + registryUser.getUserName() + registryUser.getPassword());

        repository.save(registryUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dropUser")
    @Transactional
    public ResponseEntity<User> dropUser(@RequestBody AuthRequest authRequest) {
        System.out.println("Anfrage zum Löschen erhalten: " + authRequest.getUsername());
        authRequest = FilterLogin.filterLogin(authRequest);

        User user = repository.findByUserName(authRequest.getUsername());
        Teams team = user.getTeam();
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            // Benutzer löschen
            System.out.println(user.getUserName());
            repository.delete(user);
            System.out.println("gelöscht");

            // Überprüfen, ob der Benutzer der Admin eines Teams war
            if (team != null && team.getAdmin().equals(user)) {
                // Einen neuen Admin finden oder das Team löschen
                List<User> remainingMembers = team.getMembers();
                remainingMembers.remove(user);

                if (!remainingMembers.isEmpty()) {
                    // Einen neuen Admin zufällig zuweisen
                    User newAdmin = remainingMembers.get(new Random().nextInt(remainingMembers.size()));
                    team.setAdmin(newAdmin);
                    teamsRepository.save(team);
                } else {
                    // Das Team löschen, wenn keine Mitglieder mehr übrig sind
                    teamsRepository.delete(team);
                }
            }

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}




