package com.Projekt.quizzbackend.User.Registation;




import com.Projekt.quizzbackend.Dao.UserRepository;
import com.Projekt.quizzbackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // Controller, wird direkt initialisiert
@RequestMapping(path="api/registration") // Erreichbar unter /registration. dient als Oberpfad.
public class Registrierung {


    private final UserRepository userRepository;

    @Autowired
    public Registrierung(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping(path="/") //
    public ResponseEntity<User> registry(@RequestBody User user) {
        User registryUser = new User();
        registryUser = Filter.filterUser(user);
        System.out.println("Registrierungsanfrage:  " + registryUser.getUserName() +registryUser.getPassword());
        //sollte als objekt übergeben werden. Muss noch um andere Daten ergänzt werden!

        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawPassword));
        System.out.println("Passwort verschlüsselt:  " + registryUser.getUserName() +registryUser.getPassword());

        userRepository.save(registryUser);
        return ResponseEntity.ok().build();
    }

}
