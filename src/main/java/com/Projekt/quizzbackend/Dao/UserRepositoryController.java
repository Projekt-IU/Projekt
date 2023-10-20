package com.Projekt.quizzbackend.Dao;


import com.Projekt.quizzbackend.Score.ScoreUser;
import com.Projekt.quizzbackend.Team.Teams;
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
    private final TeamsRepository teamsRepository;
    @Autowired
    public UserRepositoryController(UserRepository userRepository, TeamsRepository teamsRepository) {

        this.userRepository = userRepository;
        this.teamsRepository = teamsRepository;
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
                createUser("test", "test1234", "test12", "testTest", "test@test.de", 000153445, "fefefeffw"),
                createUser("test", "test1", "test1", "test", "fefefs@ghhhhh.eu", 000144445, "fefefeffw"),
                createUser("test", "test2", "test1Test", "testTest", "max@guckstdu.de", 001455545, "fefefeffw"),
                createUser("test", "test3", "test13454", "test35435", "test1@nix.com", 000314454455, "fefefeffw"),
                createUser("test", "test4", "test12", "testTest", "test24@test.de", 0001553445, "fefefeffw")
        };

        for (User registryUser : users) {
            System.out.println("Registrierungsanfrage:  " + registryUser.getUserName() + registryUser.getPassword());

            registryUser = Filter.filterUser(registryUser);
            System.out.println("Registrierungsanfrage:  " + registryUser.getUserName() + registryUser.getPassword());

            String rawPassword = registryUser.getPassword();
            registryUser.setPassword(passwordEncoder.encode(rawPassword));
            System.out.println("Passwort verschlüsselt:  " + registryUser.getUserName() + registryUser.getPassword());

            userRepository.save(registryUser);
        }User user = userRepository.findByUserName("test123");
        Teams team = new Teams();
        team.setName("Winners");
        team.setStudiengang("Informatik");
        team.setAdmin(user);

        teamsRepository.save(team);
        user.setTeam(team);
        userRepository.save(user);

        user = userRepository.findByUserName("test2");
        Teams team1 = new Teams();
        team1.setName("Looser");
        team1.setStudiengang("Informatik");
        team1.setAdmin(user);
        ScoreUser scoreUser = new ScoreUser();
        scoreUser.setFragenGesamt(50);
        scoreUser.setPunkteMonat(2);
        scoreUser.setPunkteWoche(34);
        scoreUser.setPunkteGesamt(18);
        user.setScoreUser(scoreUser);
        teamsRepository.save(team1);
        userRepository.findByUserName("test123").setTeam(team);
        userRepository.save(user);


        user = userRepository.findByUserName("test123444444");
        user.setTeam(team);


        scoreUser.setFragenGesamt(40);
        scoreUser.setPunkteMonat(5);
        scoreUser.setPunkteWoche(21);
        scoreUser.setPunkteGesamt(12);
        user.setScoreUser(scoreUser);
        userRepository.save(user);




        user = userRepository.findByUserName("test1234575");
        user.setTeam(team);

        scoreUser.setFragenGesamt(60);
        scoreUser.setPunkteMonat(15);
        scoreUser.setPunkteWoche(25);
        scoreUser.setPunkteGesamt(11);
        user.setScoreUser(scoreUser);
        userRepository.save(user);



        user = userRepository.findByUserName("test1234");
        user.setTeam(team);

        scoreUser.setFragenGesamt(600);
        scoreUser.setPunkteMonat(2);
        scoreUser.setPunkteWoche(5);
        scoreUser.setPunkteGesamt(500);
        user.setScoreUser(scoreUser);

        userRepository.save(user);
        teamsRepository.save(team);

        user = userRepository.findByUserName("test1");
        user.setTeam(team1);

        scoreUser.setFragenGesamt(200);
        scoreUser.setPunkteMonat(1);
        scoreUser.setPunkteWoche(3);
        scoreUser.setPunkteGesamt(100);
        user.setScoreUser(scoreUser);

        userRepository.save(user);

        user = userRepository.findByUserName("test3");
        user.setTeam(team1);

        scoreUser.setFragenGesamt(200);
        scoreUser.setPunkteMonat(12);
        scoreUser.setPunkteWoche(15);
        scoreUser.setPunkteGesamt(150);
        user.setScoreUser(scoreUser);

        userRepository.save(user);

        user = userRepository.findByUserName("test4");
        user.setTeam(team1);

        scoreUser.setFragenGesamt(10);
        scoreUser.setPunkteMonat(1);
        scoreUser.setPunkteWoche(1);
        scoreUser.setPunkteGesamt(2);
        user.setScoreUser(scoreUser);

        userRepository.save(user);

        teamsRepository.save(team1);




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




