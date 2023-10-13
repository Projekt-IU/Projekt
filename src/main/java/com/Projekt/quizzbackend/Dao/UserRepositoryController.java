package com.Projekt.quizzbackend.Dao;


import com.Projekt.quizzbackend.User.Registation.Filter;
import com.Projekt.quizzbackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        User[] users = new User[] {
                createUser("test", "test123", "test1", "test", "tobiasknauss@wk-vertrieb.eu", 00014445, "fefefeffw"),
                createUser("test", "test123444444", "test1Test", "testTest", "was@guckstdu.de", 01455445, "fefefeffw"),
                createUser("test", "test1234575", "test13454", "test35435", "nix@nix.com", 00014454455, "fefefeffw"),
                createUser("test", "test1234", "test12", "testTest", "test@test.de", 000153445, "fefefeffw")
        };

        for (User registryUser : users) {
            System.out.println("Registrierungsanfrage:  " + registryUser.getUserName() + registryUser.getPassword());

            registryUser = Filter.filterUser(registryUser);
            System.out.println("Registrierungsanfrage:  " + registryUser.getUserName() + registryUser.getPassword());

            String rawPassword = registryUser.getPassword();
            registryUser.setPassword(passwordEncoder.encode(rawPassword));
            System.out.println("Passwort verschlüsselt:  " + registryUser.getUserName() + registryUser.getPassword());

            userRepository.save(registryUser);
        }

        return ResponseEntity.ok("ok");
    }

    private User createUser(String password, String userName, String firstName, String lastName, String email, int matrikelNr, String courseOfStudy) {
        User user = new User();
        user.setPassword(password);
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setMatrikelNr(matrikelNr);
        user.setCourseOfStudy(courseOfStudy);
        return user;
    }








//test filter
    @GetMapping("/reg11")
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




