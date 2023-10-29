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
    public ResponseEntity<String> registerUsersAndTeams() {
        // Erstellen der Benutzer
        User[] users = {
                createUser("test", "test123", "test1", "test", "tobiasknauss@wk-vertrieb.eu", 00014445, "fefefeffw"),
                createUser("test", "test123444444", "test1Test", "testTest", "was@guckstdu.de", 01455445, "fefefeffw"),
                createUser("test", "test1234575", "test13454", "test35435", "nix@nix.com", 00014454455, "fefefeffw"),
                createUser("test", "test1234", "test12", "testTest", "test@test.de", 000153445, "fefefeffw"),
                createUser("test", "test1", "test1", "test", "fefefs@ghhhhh.eu", 000144445, "fefefeffw"),
                createUser("test", "test2", "test1Test", "testTest", "max@guckstdu.de", 001455545, "fefefeffw"),
                createUser("test", "test3", "test13454", "test35435", "test1@nix.com", 000314454455, "fefefeffw"),
                createUser("test", "test4", "test12", "testTest", "test24@test.de", 0001553445, "fefefeffw"),
                createUser("newPassword1", "newUser1", "newFirstName1", "newLastName1", "newUser1@example.com", 1234567, "Informatik"),
                createUser("newPassword2", "newUser2", "newFirstName2", "newLastName2", "newUser2@example.com", 2345678, "Informatik"),
                createUser("newPassword3", "newUser3", "newFirstName3", "newLastName3", "newUser3@example.com", 3456789, "Informatik")
        };

        for (User user : users) {
            ScoreUser scoreUser = new ScoreUser();
            scoreUser.setFragenGesamt(100);
            scoreUser.setPunkteMonat(10);
            scoreUser.setPunkteWoche(5);
            scoreUser.setPunkteGesamt(50);
            scoreUser.setFrageRichtig(45);
            user.setScoreUser(scoreUser);
            userRepository.save(user);
        }

        // Erstellen der Teams
        Teams team1 = createTeam("Team1", "Informatik", userRepository.findByUserName("test123"));
        Teams team2 = createTeam("Team2", "Informatik", userRepository.findByUserName("test123444444"));
        Teams team3 = createTeam("Team3", "Informatik", userRepository.findByUserName("newUser1"));

        teamsRepository.save(team1);
        teamsRepository.save(team2);
        teamsRepository.save(team3);

        for (User user : users) {
            if (user.getUserName().equals("test123") || user.getUserName().equals("test1234575") || user.getUserName().equals("test1234")) {
                user.setTeam(team1);
            } else if (user.getUserName().equals("test123444444") || user.getUserName().equals("test2") || user.getUserName().equals("test3")) {
                user.setTeam(team2);
            } else {
                user.setTeam(team3);
            }
            userRepository.save(user);
        }

        return ResponseEntity.ok("OK");
    }

    private User createUser(String password, String userName, String firstName, String lastName, String email, int matrikelNr, String courseOfStudy) {
        User user = new User();
        String rawPassword = password;
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setMatrikelNr(matrikelNr);
        user.setCourseOfStudy(courseOfStudy);
        return user;
    }




    private Teams createTeam(String name, String studiengang, User admin) {
        Teams team = new Teams();
        team.setName(name);
        team.setStudiengang(studiengang);
        team.setAdmin(admin);
        return team;
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




