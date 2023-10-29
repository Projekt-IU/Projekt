package com.Projekt.quizzbackend.Controller;

import com.Projekt.quizzbackend.Dao.DTO.ChatMapper;
import com.Projekt.quizzbackend.Dao.DTO.Templates.ChatDTO;
import com.Projekt.quizzbackend.Dao.DTO.Templates.ChatMessageInDTO;
import com.Projekt.quizzbackend.Dao.TeamsRepository;
import com.Projekt.quizzbackend.Dao.UserRepository;
import com.Projekt.quizzbackend.Team.Chat;
import com.Projekt.quizzbackend.Team.ChatService;
import com.Projekt.quizzbackend.Team.Teams;
import com.Projekt.quizzbackend.User.Login.AuthRequest;
import com.Projekt.quizzbackend.User.Login.FilterLogin;
import com.Projekt.quizzbackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Adress Pfad Chat Anbindung
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private final UserRepository repository;
    @Autowired
    private final TeamsRepository teamsRepository;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ChatController(UserRepository repository, TeamsRepository teamsRepository) {
        this.repository = repository;
        this.teamsRepository = teamsRepository;
    }

    //senden von Nachrichten
    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody ChatMessageInDTO chatMessageInDTO) {
        System.out.println("Anfrage für Chat für user : " + chatMessageInDTO.getUsername());

        AuthRequest authRequest = FilterLogin.filterLogin(chatMessageInDTO);

        User user = repository.findByUserName(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword()) && user.isAccess()) {


            Chat chat = new Chat();
            chat.setNachricht(chatMessageInDTO.getNachricht());
            chat.setUser(user);
            chat.setTeam(user.getTeam());
            chatService.addMessage(chat);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    //Nachrichten nAbrufen
    @PostMapping("/messages/{teamName}")
    public ResponseEntity<List<ChatDTO>> getMessages(@PathVariable String teamName, @RequestBody AuthRequest authRequest) {
        User user = repository.findByUserName(authRequest.getUsername());
        if (user == null || !passwordEncoder.matches(authRequest.getPassword(), user.getPassword())&& user.isAccess()) {
            System.out.println("Chat nicht autorisiert ");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);


        }

        Teams team = teamsRepository.findByName(teamName);  // Ihre Logik, um das Team zu finden
        if (team == null) {
            System.out.println("Chat nicht autorisiert, da Team nicht existent: " + teamName);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }

        // Optional: Überprüfen, ob der Benutzer Mitglied des Teams ist
        if (!team.getMembers().contains(user)) {
            System.out.println("Chat nicht autorisiert, da nicht im team: " + teamName);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        List<Chat> chats = chatService.getMessages(team);
        List<ChatDTO> chatDTOs = chatMapper.entitiesToDTOs(chats);
        System.out.println("Chat : " + chatDTOs.toString());
        return ResponseEntity.ok(chatDTOs);
    }

}
