package com.Projekt.quizzbackend.Quiz;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Fragen {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "fragen_id", nullable = false)
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
    private String richtigeAntwort;

    public Integer getFragenId() {
        return fragenId;
    }

    public void setFragenId(Integer fragenId) {
        this.fragenId = fragenId;
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

    public String getRichtigeAntwort() {
        return richtigeAntwort;
    }

    public void setRichtigeAntwort(String richtigeAntwort) {
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
}
