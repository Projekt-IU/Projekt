package com.Projekt.quizzbackend.Controller;

import com.Projekt.quizzbackend.Dao.UserRepository;
import com.Projekt.quizzbackend.Mail.EmailService;
import com.Projekt.quizzbackend.Mail.Mail;
import com.Projekt.quizzbackend.User.Login.FilterLogin;
import com.Projekt.quizzbackend.User.Login.LoginRequest;
import com.Projekt.quizzbackend.User.Logout.LogoutRequest;
import com.Projekt.quizzbackend.User.Registation.Filter;
import com.Projekt.quizzbackend.User.Registation.NewPasswort;
import com.Projekt.quizzbackend.User.User;
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


@RestController
@RequestMapping("/api")

public class UserController {


        private final UserRepository repository;
        public UserController(UserRepository repository) {
            this.repository = repository;
        }

        @Autowired
        private BCryptPasswordEncoder passwordEncoder;

         @Autowired
         private EmailService emailService;


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("login anfrage erhalten: " + loginRequest.getUsername() +loginRequest.getPassword());
        loginRequest = FilterLogin.filterLogin(loginRequest);

        User user = repository.findByUserName(loginRequest.getUsername());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            user.login();
            System.out.println(user.isLoggedIn());

            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

        @PostMapping("logout")
        public ResponseEntity<User> logout(@RequestBody LogoutRequest logoutRequest) {

            System.out.println(logoutRequest.getUserId()+ logoutRequest.getUsername());
            System.out.println("Loggout anfrage erhalten: " + logoutRequest.getUsername() + logoutRequest.getUserId());
            System.out.println(logoutRequest);
            User user = repository.findUserByUserIDAndUserName(logoutRequest.getUserId(), (logoutRequest.getUsername()));
            System.out.println(user);
            user.logout();
            return ResponseEntity.ok().body(null); // Erfolgreiche Logout-Antwort.
        }

    @PostMapping("/forgotpw")
    public ResponseEntity<Mail> mail(@RequestBody Mail email) {
        System.out.println("PW vergessen:  " + email);

        User user = repository.findByEmail(email.getEmail());

        if (user == null) {
            System.out.println("Mail ungültig " );
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


    @PostMapping("/newPw")
    public ResponseEntity<NewPasswort> newPasswordMail(@RequestBody NewPasswort newPasswordMail) {
        System.out.println("PW vergessen:  " + newPasswordMail);

        User user = repository.findByEmailAndPassword(newPasswordMail.getEmail(), newPasswordMail.getPassword());

        if (user == null) {
            System.out.println("Mail ungültig " );
            // Benutzer mit der angegebenen E-Mail-Adresse wurde nicht gefunden
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }


        // Verschlüssle und speichere das neue Passwort
        user.setPassword(passwordEncoder.encode(newPasswordMail.getNewPassword()));
        repository.save(user);
        System.out.println("Password geändert" );
        return ResponseEntity.ok().build();
    }

    @PostMapping(path="/userregistrieren") //
    public ResponseEntity<User> registry(@RequestBody User user) {
        User registryUser = new User();
        registryUser = Filter.filterUser(user);
        System.out.println("Registrierungsanfrage:  " + registryUser.getUserName() +registryUser.getPassword());
        //sollte als objekt übergeben werden. Muss noch um andere Daten ergänzt werden!

        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawPassword));
        System.out.println("Passwort verschlüsselt:  " + registryUser.getUserName() +registryUser.getPassword());

        repository.save(registryUser);
        return ResponseEntity.ok().build();
    }


}




