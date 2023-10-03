package com.Projekt.quizzbackend.Team;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "badges_team", schema = "quizsystema", catalog = "")
public class BadgesTeam {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "badges_team_id", nullable = false)
    private Integer badgesTeamId;
    @Basic
    @Column(name = "erster_badge", nullable = true)
    private Byte ersterBadge;
    @Basic
    @Column(name = "zweiter_badge", nullable = true)
    private Byte zweiterBadge;
    @Basic
    @Column(name = "dritter_badge", nullable = true)
    private Byte dritterBadge;
    @Basic
    @Column(name = "vierter_badge", nullable = true)
    private Byte vierterBadge;
    @Basic
    @Column(name = "fünfter_badge", nullable = true)
    private Byte fünfterBadge;
    @Basic
    @Column(name = "sechster_badge", nullable = true)
    private Byte sechsterBadge;
    @Basic
    @Column(name = "siebter_badge", nullable = true)
    private Byte siebterBadge;
    @Basic
    @Column(name = "achter_badge", nullable = true)
    private Byte achterBadge;

    public Integer getBadgesTeamId() {
        return badgesTeamId;
    }

    public void setBadgesTeamId(Integer badgesTeamId) {
        this.badgesTeamId = badgesTeamId;
    }

    public Byte getErsterBadge() {
        return ersterBadge;
    }

    public void setErsterBadge(Byte ersterBadge) {
        this.ersterBadge = ersterBadge;
    }

    public Byte getZweiterBadge() {
        return zweiterBadge;
    }

    public void setZweiterBadge(Byte zweiterBadge) {
        this.zweiterBadge = zweiterBadge;
    }

    public Byte getDritterBadge() {
        return dritterBadge;
    }

    public void setDritterBadge(Byte dritterBadge) {
        this.dritterBadge = dritterBadge;
    }

    public Byte getVierterBadge() {
        return vierterBadge;
    }

    public void setVierterBadge(Byte vierterBadge) {
        this.vierterBadge = vierterBadge;
    }

    public Byte getFünfterBadge() {
        return fünfterBadge;
    }

    public void setFünfterBadge(Byte fünfterBadge) {
        this.fünfterBadge = fünfterBadge;
    }

    public Byte getSechsterBadge() {
        return sechsterBadge;
    }

    public void setSechsterBadge(Byte sechsterBadge) {
        this.sechsterBadge = sechsterBadge;
    }

    public Byte getSiebterBadge() {
        return siebterBadge;
    }

    public void setSiebterBadge(Byte siebterBadge) {
        this.siebterBadge = siebterBadge;
    }

    public Byte getAchterBadge() {
        return achterBadge;
    }

    public void setAchterBadge(Byte achterBadge) {
        this.achterBadge = achterBadge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BadgesTeam that = (BadgesTeam) o;
        return Objects.equals(badgesTeamId, that.badgesTeamId) && Objects.equals(ersterBadge, that.ersterBadge) && Objects.equals(zweiterBadge, that.zweiterBadge) && Objects.equals(dritterBadge, that.dritterBadge) && Objects.equals(vierterBadge, that.vierterBadge) && Objects.equals(fünfterBadge, that.fünfterBadge) && Objects.equals(sechsterBadge, that.sechsterBadge) && Objects.equals(siebterBadge, that.siebterBadge) && Objects.equals(achterBadge, that.achterBadge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(badgesTeamId, ersterBadge, zweiterBadge, dritterBadge, vierterBadge, fünfterBadge, sechsterBadge, siebterBadge, achterBadge);
    }
}
