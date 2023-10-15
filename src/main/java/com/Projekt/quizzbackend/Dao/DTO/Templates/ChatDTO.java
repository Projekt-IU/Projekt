package com.Projekt.quizzbackend.Dao.DTO.Templates;

import java.sql.Timestamp;

public class ChatDTO {

        private String nachricht;
        private String username;  // Benutzername des Absenders
        private Timestamp created;


    public String getNachricht() {
        return nachricht;
    }

    public void setNachricht(String nachricht) {
        this.nachricht = nachricht;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
