package com.Projekt.quizzbackend.Dao.DTO.Templates;

import com.Projekt.quizzbackend.User.Login.AuthRequest;

public class ChatMessageInDTO extends AuthRequest {

    private String nachricht;

    public String getNachricht() {
        return nachricht;
    }

    public void setNachricht(String nachricht) {
        this.nachricht = nachricht;
    }
}
