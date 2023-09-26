package com.Projekt.quizzbackend.User;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgotPasswordController {

    @PostMapping("/api/forgotpw")
    public ResponseEntity<String> mail(@RequestBody String mail) {
        System.out.println("PW vergessen, mail an:  " + mail);


        if (mail != null) {


            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


}
