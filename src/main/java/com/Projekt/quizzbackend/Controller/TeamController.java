package com.Projekt.quizzbackend.Controller;

import com.Projekt.quizzbackend.Dao.ChatRepository;
import com.Projekt.quizzbackend.Dao.DTO.TeamMapper;
import com.Projekt.quizzbackend.Dao.DTO.Templates.AddUserToTeam;
import com.Projekt.quizzbackend.Dao.DTO.Templates.DropUserTeam;
import com.Projekt.quizzbackend.Dao.DTO.Templates.NewTeamDTO;
import com.Projekt.quizzbackend.Dao.DTO.Templates.TeamDTO;
import com.Projekt.quizzbackend.Dao.TeamsRepository;
import com.Projekt.quizzbackend.Dao.UserRepository;
import com.Projekt.quizzbackend.Team.Chat;
import com.Projekt.quizzbackend.Team.Teams;
import com.Projekt.quizzbackend.User.Login.AuthRequest;
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

import java.util.List;


@RestController
@RequestMapping("/api/Team")

public class TeamController {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final TeamsRepository teamsRepository;
    public TeamController(UserRepository userRepository, TeamsRepository teamsRepository) {
        this.userRepository = userRepository;
        this.teamsRepository = teamsRepository;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//Liste aller Teams
    @PostMapping("/getAllTeams")
    public ResponseEntity<?> getAllTeams(@RequestBody AuthRequest authRequest) {

        User user = userRepository.findByUserName(authRequest.getUsername());
        if(authRequest.getAnfrageName()== null)
        {authRequest.setAnfrageName(user.getTeam().getName());}
        System.out.println("Frage Teams ab: ");

        Iterable<Teams> teams = teamsRepository.findAll();
        Iterable<TeamDTO> teamDTOs = teamMapper.convertToDTO(teams);
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())&& user.isAccess()) {

            return ResponseEntity.ok(teamDTOs);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/getTeam")
    public ResponseEntity<?> getTeam(@RequestBody AuthRequest authRequest) {



        User user = userRepository.findByUserName(authRequest.getUsername());
        if(authRequest.getAnfrageName()== null)
        {authRequest.setAnfrageName(user.getTeam().getName());}
        System.out.println("Frage Team ab: ");

        Teams teams = teamsRepository.findByName(authRequest.getAnfrageName());

        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())&& user.isAccess()) {
            TeamDTO dto = teamMapper.entityToDTO(teams, true);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/addUser")
    @Transactional
    public ResponseEntity<?> addUser(@RequestBody AddUserToTeam authRequest) {


        User user = userRepository.findByUserName(authRequest.getUsername());
        User newMember = userRepository.findByUserName(authRequest.getNewMember());
        Teams team = user.getTeam();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        if (authRequest.getNewMember() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        if (team == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())&& user.isAccess()) {
            if(newMember.getTeam() ==null) {
                newMember.setTeam(team);
                userRepository.save(newMember);
                return ResponseEntity.ok().build();
            } else if (newMember.getTeam() != null){ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Bereits in einem Team");

        }
    }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    @PostMapping("/newTeam")
    @Transactional
    public ResponseEntity<?> newTeam(@RequestBody NewTeamDTO authRequest) {
        User user = userRepository.findByUserName(authRequest.getUsername());
        System.out.println("User: " + user.getUserName());


        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())&& user.isAccess()) {
            if (teamsRepository.findByName(authRequest.getName()) == null)
            {
                Teams team = new Teams();
                team.setName(authRequest.getName());
                team.setStudiengang(authRequest.getStudiengang());
                team.setAdmin(user);

                teamsRepository.save(team);
                user.setTeam(team);
                userRepository.save(user);

                return ResponseEntity.ok().build();

            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }




    @PostMapping("/dropTeam")
    @Transactional
    public ResponseEntity<?> dropTeam(@RequestBody AuthRequest authRequest) {
        User user = userRepository.findByUserName(authRequest.getUsername());
        System.out.println("Test1");
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())&& user.isAccess()) {
            if (user.getTeam() != null)
            {
                Teams team = user.getTeam();
                System.out.println("Test2");
                List<User> TeamMembers = team.getMembers();
                System.out.println("Test3" + TeamMembers);
                for (User userTeam : TeamMembers) {
                    userTeam.setTeam(null);
                    userRepository.save(user);
                }

                List<Chat> chatForDel = chatRepository.findAllByTeam(team);
                chatRepository.deleteAll(chatForDel);

                teamsRepository.delete(team);
                return ResponseEntity.ok().build();

            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }}


    @PostMapping("/newAdmin")
    @Transactional
    public ResponseEntity<?> newAdmin(@RequestBody AuthRequest authRequest) {
        User user = userRepository.findByUserName(authRequest.getUsername());

        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())&& user.isAccess()) {
            if (user.getTeam() != null)
            {
                Teams team = user.getTeam();
                User newAdmin = userRepository.findByUserName(authRequest.getAnfrageName());
                team.setAdmin(newAdmin);
                teamsRepository.save(team);
                return ResponseEntity.ok().build();

            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }}



    @PostMapping("/dropUser")
    @Transactional
    public ResponseEntity<?> dropUser(@RequestBody DropUserTeam authRequest) {
        User user = userRepository.findByUserName(authRequest.getUsername());
        User dropUser = userRepository.findByUserName(authRequest.getUserToDrop());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())&& user.isAccess()) {
            if (dropUser.getTeam() != null) {
                if (dropUser.getTeam() == user.getTeam() ) {
                    Teams team = dropUser.getTeam();
                    if( team.getAdmin() == user)
                    {
                    if (team.getAdmin() != dropUser) {
                        dropUser.setTeam(null);
                        userRepository.save(dropUser);
                        return ResponseEntity.ok().build();
                    } else {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Admin kann nicht entfernt werden");
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Der User ist dazu nicht berechtigt ");
                }}
                else { return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Der User ist nicht im selben Team oder der User ist nicht dazu berechtigt");}
                }

           return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Der User ist nicht in einem Team ");

            } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }}

}



