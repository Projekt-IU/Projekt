package com.Projekt.quizzbackend.Quiz;

import com.Projekt.quizzbackend.User.User;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Fragen  implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "fragen_id", nullable = false , unique=true)
    private Integer fragenId;
    @Basic
    @Column(name = "frage", nullable = false, length = 255)
    private String frage;
    @Basic
    @Column(name = "modul", nullable = false, length = 200)
    private String modul;
    @Basic
    @Column(name = "antwort_eins", nullable = false, length = 255)
    private String antwortEins;
    @Basic
    @Column(name = "antwort_zwei", nullable = false, length = 255)
    private String antwortZwei;
    @Basic
    @Column(name = "antwort_drei", nullable = false, length = 255)
    private String antwortDrei;
    @Basic
    @Column(name = "antwort_vier", nullable = false, length = 255)
    private String antwortVier;



    @Basic
    @Column(name = "richtige_antwort", nullable = false, length = 12)
    private Integer richtigeAntwort;



    @ManyToOne
    @JoinColumn(name = "benutzer_id")
    private User user;









    public Integer getFragenId() {
        return fragenId;
    }


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

    public Integer getRichtigeAntwort() {
        return richtigeAntwort;
    }

    public void setRichtigeAntwort(Integer richtigeAntwort) {
        this.richtigeAntwort = richtigeAntwort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fragen fragen = (Fragen) o;
        return Objects.equals(fragenId, fragen.fragenId) && Objects.equals(frage, fragen.frage) && Objects.equals(modul, fragen.modul) && Objects.equals(antwortEins, fragen.antwortEins) && Objects.equals(antwortZwei, fragen.antwortZwei) && Objects.equals(antwortDrei, fragen.antwortDrei) && Objects.equals(antwortVier, fragen.antwortVier) && Objects.equals(richtigeAntwort, fragen.richtigeAntwort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fragenId, frage, modul, antwortEins, antwortZwei, antwortDrei, antwortVier, richtigeAntwort);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Fragen{" +
                "fragenId=" + fragenId +
                ", frage='" + frage + '\'' +
                ", modul='" + modul + '\'' +
                ", antwortEins='" + antwortEins + '\'' +
                ", antwortZwei='" + antwortZwei + '\'' +
                ", antwortDrei='" + antwortDrei + '\'' +
                ", antwortVier='" + antwortVier + '\'' +
                ", richtigeAntwort='" + richtigeAntwort + '\'' +
                ", user=" + user +
                '}';
    }
}



