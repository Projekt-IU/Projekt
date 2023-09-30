package com.Projekt.quizzbackend.User.UserScore;

import com.Projekt.quizzbackend.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "scores_user")
public class ScoreUser implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "scores_id", nullable = false )
    private Integer scoresId;
    @Basic
    @Column(name = "punkte_gesamt", nullable = true)
    private Integer punkteGesamt;
    @Basic
    @Column(name = "punkte_monat", nullable = true)
    private Integer punkteMonat;
    @Basic
    @Column(name = "punkte_woche", nullable = true)
    private Integer punkteWoche;
    @Basic
    @Column(name = "frage_richtig", nullable = true)
    private Integer frageRichtig;
    @Basic
    @Column(name = "fragen_gesamt", nullable = true)
    private Integer fragenGesamt;

    @OneToOne(mappedBy = "scoreUser")
    @JsonBackReference
    private User user;
    public Integer getScoresId() {
        return scoresId;
    }

    public void setScoresId(Integer scoresId) {
        this.scoresId = scoresId;
    }

    public Integer getPunkteGesamt() {
        return punkteGesamt;
    }

    public void setPunkteGesamt(Integer punkteGesamt) {
        this.punkteGesamt = punkteGesamt;
    }

    public Integer getPunkteMonat() {
        return punkteMonat;
    }

    public void setPunkteMonat(Integer punkteMonat) {
        this.punkteMonat = punkteMonat;
    }

    public Integer getPunkteTag() {
        return punkteWoche;
    }

    public void setPunkteWoche(Integer punkteTag) {
        this.punkteWoche = punkteTag;
    }

    public Integer getFrageRichtig() {
        return frageRichtig;
    }

    public void setFrageRichtig(Integer frageRichtig) {
        this.frageRichtig = frageRichtig;
    }

    public Integer getFragenGesamt() {
        return fragenGesamt;
    }

    public void setFragenGesamt(Integer fragenGesamt) {
        this.fragenGesamt = fragenGesamt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreUser that = (ScoreUser) o;
        return Objects.equals(scoresId, that.scoresId) && Objects.equals(punkteGesamt, that.punkteGesamt) && Objects.equals(punkteMonat, that.punkteMonat) && Objects.equals(punkteWoche, that.punkteWoche) && Objects.equals(frageRichtig, that.frageRichtig) && Objects.equals(fragenGesamt, that.fragenGesamt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoresId, punkteGesamt, punkteMonat, punkteWoche, frageRichtig, fragenGesamt);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
