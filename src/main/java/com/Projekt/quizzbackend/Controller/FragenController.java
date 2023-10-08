package com.Projekt.quizzbackend.Controller;

import com.Projekt.quizzbackend.Dao.TeamsRepository;
import com.Projekt.quizzbackend.Dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class FragenController {


    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final TeamsRepository teamsRepository;

    public FragenController(UserRepository userRepository, TeamsRepository teamsRepository) {
        this.userRepository = userRepository;
        this.teamsRepository = teamsRepository;
    }
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


}
