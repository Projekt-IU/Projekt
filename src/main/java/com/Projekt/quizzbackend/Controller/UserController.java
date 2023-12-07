package com.Projekt.quizzbackend.Controller;

import com.Projekt.quizzbackend.Dao.ChatRepository;
import com.Projekt.quizzbackend.Dao.DTO.Templates.UserDTO;
import com.Projekt.quizzbackend.Dao.DTO.Templates.UserWithScore;
import com.Projekt.quizzbackend.Dao.DTO.UserMapper;
import com.Projekt.quizzbackend.Dao.FragenRepository;
import com.Projekt.quizzbackend.Dao.TeamsRepository;
import com.Projekt.quizzbackend.Dao.UserRepository;
import com.Projekt.quizzbackend.Mail.EmailService;
import com.Projekt.quizzbackend.Mail.Mail;
import com.Projekt.quizzbackend.Quiz.Fragen;
import com.Projekt.quizzbackend.Team.Chat;
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
    private final TeamsRepository teamsRepository;
    private final UserRepository repository;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FragenRepository fragenRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
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
        System.out.println("login anfrage erhalten: ");

        User user = repository.findByUserName(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())&& user.isAccess()) {
            user.login();
            UserDTO dto = userMapper.entityToDto(user);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("logout")
    public ResponseEntity<UserDTO> logout(@RequestBody LogoutRequest logoutRequest) {

        User user = repository.findUserByUserIDAndUserName(logoutRequest.getUserId(), (logoutRequest.getUsername()));
        user.logout();
        return ResponseEntity.ok().body(null); // Erfolgreiche Logout-Antwort.
    }

    @PostMapping("getProfil")
    public ResponseEntity<?> getProfil(@RequestBody AuthRequest authRequest) {

        System.out.println("Profildaten angefragt ");
        authRequest = FilterLogin.filterLogin(authRequest);

        User user = repository.findByUserName(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())&& user.isAccess()) {
            UserWithScore userDto = userMapper.entityWithScoreToDto(user);

            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/forgotpw")
    public ResponseEntity<Mail> mail(@RequestBody Mail email) {
        System.out.println("PW vergessen:  " + email);

        User user = repository.findByEmail(email.getEmail());

        if (user == null) {
            System.out.println("Mail ungültig ");
            // Benutzer mit der angegebenen E-Mail-Adresse wurde nicht gefunden
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        if (user.isAccess()) {
            // Generiere ein temporäres Passwort
            SecureRandom random = new SecureRandom();
            byte[] bytes = new byte[20];
            random.nextBytes(bytes);
            String tempPassword = Base64.getEncoder().encodeToString(bytes);

            // Verschlüsselt und speichert das temporäre Passwort
            user.setPassword(passwordEncoder.encode(tempPassword));
            repository.save(user);

            // Sende das temporäre Passwort per E-Mail
            emailService.sendSimpleMessage(
                    email.getEmail(),
                    "Ihr temporäres Passwort",
                    "Hallo " + user.getUserName() + ",  \n" +
                            "\n Ihr temporäres Passwort lautet: " + tempPassword
            );

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/newPw")
    public ResponseEntity<?> newPasswordMail(@RequestBody AuthRequest authRequest) {
        authRequest = FilterLogin.filterLogin(authRequest);
        User user = repository.findByUserName(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())&& user.isAccess()) {
            // Verschlüssle und speichere das neue Passwort
            user.setPassword(passwordEncoder.encode(authRequest.getAnfrageName()));
            repository.save(user);
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
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword()) && repository.findByUserName(authRequest.getAnfrageName()) == null && user.isAccess())  {

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

        String rawPassword = registryUser.getPassword();
        registryUser.setPassword(passwordEncoder.encode(rawPassword));
        repository.save(registryUser);

        emailService.sendSimpleMessage(
                registryUser.getEmail(),
                "Willkommen bei unserem Quiz",
                "Willkommen und danke, dass du unserer Community beigetreten bist. Wir wünschen dir viel Spaß und Erfolg "
        );

        return ResponseEntity.ok().build();
    }

    @PostMapping("/dropUser")
    @Transactional
    public ResponseEntity<User> dropUser(@RequestBody AuthRequest authRequest) {
        System.out.println("Anfrage zum Löschen erhalten: " + authRequest.getUsername());
        authRequest = FilterLogin.filterLogin(authRequest);

        User user = repository.findByUserName(authRequest.getUsername());
        Teams team = user.getTeam();
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())&& user.isAccess()) {

            // Überprüfen, ob der Benutzer der Admin eines Teams war
            if (team != null && team.getAdmin().equals(user)) {
                // Einen neuen Admin finden oder das Team löschen
                List<User> remainingMembers = team.getMembers();
                remainingMembers.remove(user);
                user.setTeam(null);
                repository.delete(user);

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

            // Platzhalter-Benutzer holen oder erstellen
            User deletedUser = createOrGetDeletedUser();

            // Alle Chat-Nachrichten des gelöschten Benutzers auf den Platzhalter-Benutzer umstellen
            List<Chat> chats = chatRepository.findByUser(user);
            for (Chat chat : chats) {
                chat.setUser(deletedUser);
            }
            chatRepository.saveAll(chats);

            //Fragen Platzhalter einfügen
            List<Fragen> fragen = fragenRepository.findByUser(user);
            for (Fragen frage : fragen) {
                frage.setUser(deletedUser);

            }
            fragenRepository.saveAll(fragen);
            repository.delete(user);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


    //platzhalter User
    public User createOrGetDeletedUser() {
        String placeholderName = "gelöschter Benutzer";

        // Überprüfen, ob ein Benutzer mit diesem Namen bereits existiert
        User deletedUser = repository.findByUserName(placeholderName);

        // Wenn nicht, erstellen Sie einen neuen
        if (deletedUser == null) {
            deletedUser = new User();
            deletedUser.setUserName(placeholderName);
            deletedUser.setFirstName("gelöschter");
            deletedUser.setLastName("Benutzer");
            deletedUser.setMatrikelNr(00000);
            deletedUser.setCourseOfStudy("nicht verfügbar");
            deletedUser.setPassword(passwordEncoder.encode("System"));
            deletedUser.setEmail("service@quiz.de");
            deletedUser.setRole("PlaceHolder");
            repository.save(deletedUser);
        }

        return deletedUser;
    }


}




