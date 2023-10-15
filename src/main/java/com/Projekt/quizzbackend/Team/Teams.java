package com.Projekt.quizzbackend.Team;

import com.Projekt.quizzbackend.Score.ScoresTeam;
import com.Projekt.quizzbackend.User.User;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
@SessionAttributes
@Entity
public class Teams  implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "team_id", nullable = false)
    private Integer teamsId;
    @Basic
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Basic
    @Column(name = "studiengang", nullable = false, length = 100)
    private String studiengang;


    @OneToOne(cascade = CascadeType.ALL)

    @JoinColumn(name = "admin_id", referencedColumnName = "benutzer_id")
    private User admin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "scores_id", referencedColumnName = "scores_id")
    private ScoresTeam scoreTeam;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<User> members;
    @PrePersist
    public void prePersist() {

        if (this.getScoreTeam() == null) {
            this.scoreTeam = new ScoresTeam();
            this.scoreTeam.setPunkteGesamt(0);
            this.scoreTeam.setPunkteMonat(0);
            this.scoreTeam.setPunkteWoche(0);
            this.scoreTeam.setFrageRichtig(0);
            this.scoreTeam.setFragenGesamt(0);
            this.scoreTeam.setScoresTeamId(this.teamsId);
        }
    }
    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
    public Integer getTeamsId() {
        return teamsId;
    }

    public void setTeamsId(Integer teamsId) {
        this.teamsId = teamsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(String studiengang) {
        this.studiengang = studiengang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teams teams = (Teams) o;
        return Objects.equals(teamsId, teams.teamsId) && Objects.equals(name, teams.name) && Objects.equals(studiengang, teams.studiengang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamsId, name, studiengang);
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;

    }


    public ScoresTeam getScoreTeam() {
        return scoreTeam;
    }

    public void setScoreTeam(ScoresTeam scoreTeam) {
        this.scoreTeam = scoreTeam;
    }
}
