package com.Projekt.quizzbackend.Quiz;

import com.Projekt.quizzbackend.User.Login.AuthRequest;

public class FrageHolen extends AuthRequest {

        private String modul;
        private String studiengang;

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = modul;
    }

    public String getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(String studiengang) {
        this.studiengang = studiengang;
    }

    @Override
    public String toString() {
        return "FrageHolen{" +
                "modul='" + modul + '\'' +
                ", studiengang='" + studiengang + '\'' +
                ", user='" + getUsername() + '\'' +
                ", pw ='" + getPassword() + '\'' +
                '}';
    }
}
