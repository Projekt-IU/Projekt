package com.Projekt.quizzbackend.Dao.DTO.Templates;

import com.Projekt.quizzbackend.User.Login.AuthRequest;
import com.Projekt.quizzbackend.User.User;

public class FrageErstellen extends AuthRequest {


    private String frage;
    private String modul;
    private String antwortEins;
    private String antwortZwei;
    private String antwortDrei;
    private String antwortVier;
    private String richtigeAntwort;
    private User user;

    public String getFrage() {
        return frage;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = modul;
    }

    public String getAntwortEins() {
        return antwortEins;
    }

    public void setAntwortEins(String antwortEins) {
        this.antwortEins = antwortEins;
    }

    public String getAntwortZwei() {
        return antwortZwei;
    }

    public void setAntwortZwei(String antwortZwei) {
        this.antwortZwei = antwortZwei;
    }

    public String getAntwortDrei() {
        return antwortDrei;
    }

    public void setAntwortDrei(String antwortDrei) {
        this.antwortDrei = antwortDrei;
    }

    public String getAntwortVier() {
        return antwortVier;
    }

    public void setAntwortVier(String antwortVier) {
        this.antwortVier = antwortVier;
    }

    public String getRichtigeAntwort() {
        return richtigeAntwort;
    }

    public void setRichtigeAntwort(String richtigeAntwort) {
        this.richtigeAntwort = richtigeAntwort;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
