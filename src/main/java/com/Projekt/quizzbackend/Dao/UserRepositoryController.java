package com.Projekt.quizzbackend.Dao;


import com.Projekt.quizzbackend.User.Registation.Filter;
import com.Projekt.quizzbackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // Controller, wird direkt initialisiert
@RequestMapping(path="/demo") // Erreichbar unter /demo. dient als Oberpfad.
public class UserRepositoryController {


    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryController( UserRepository userRepository) {

        this.userRepository = userRepository;
    }
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//zum Test zum erstellen von Usern
    //unter demo/reg wird ein neuer Nutzer erstellt


    @GetMapping("/reg")
    public ResponseEntity<String>ok()  {

        User registryUser = new User();
        registryUser.setPassword("test");
        registryUser.setUserName("test123");
        registryUser.setFirstName("test1");
        registryUser.setLastName("test");
        registryUser.setEmail("ffddf@fffdd.de");
        registryUser.setMatrikelNr(00014445);
        registryUser.setCourseOfStudy("fefefeffw");

        System.out.println("Registrierungsanfrage:  " + registryUser.getUserName() +registryUser.getPassword());
        //sollte als objekt übergeben werden. Muss noch um andere Daten ergänzt werden!


            registryUser = Filter.filterUser(registryUser);
            System.out.println("Registrierungsanfrage:  " + registryUser.getUserName() +registryUser.getPassword());
            //sollte als objekt übergeben werden. Muss noch um andere Daten ergänzt werden!

            String rawPassword = registryUser.getPassword();
            registryUser.setPassword(passwordEncoder.encode(rawPassword));
            System.out.println("Passwort verschlüsselt:  " + registryUser.getUserName() +registryUser.getPassword());

            userRepository.save(registryUser);





            return ResponseEntity.ok("ok");
        }



//test filter
    @GetMapping("/reg1")
    public ResponseEntity<String> ok1() {

        User registryUser = new User();
        registryUser.setPassword("test");
        registryUser.setUserName("test12;*");
        registryUser.setFirstName("''test1''");
        registryUser.setLastName("test");
        registryUser.setEmail(",;//ffddf@fffdd");
        registryUser.setMatrikelNr(00014445);
        registryUser.setCourseOfStudy("fefefe^^ffw;");
        System.out.println("Registrierungsanfrage:  " + registryUser.getUserName() +registryUser.getPassword());
        //sollte als objekt übergeben werden. Muss noch um andere Daten ergänzt werden!
        registryUser = Filter.filterUser(registryUser);
        userRepository.save(registryUser);



        return ResponseEntity.ok("ok");
    }


}




