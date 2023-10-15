package com.Projekt.quizzbackend.User;

import com.Projekt.quizzbackend.Quiz.Fragen;
import com.Projekt.quizzbackend.Score.ScoreUser;
import com.Projekt.quizzbackend.Team.Teams;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


@SessionAttributes
@Entity
@Table(name = "benutzer" )
public class User implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "benutzer_id", nullable = false, unique=true)
    private Integer userID;
    @Basic
    @Column(name = "vorname", nullable = false, length = 30)
    private String firstName;
    @Basic
    @Column(name = "nachname", nullable = false, length = 30)
    private String lastName;
    @Basic
    @Column(name = "benutzername", nullable = false, length = 30, unique=true)
    private String userName;
    @Basic
    @Column(name = "email", nullable = true, length = 255, unique=true)
    private String email;
    @Basic
    @Column(name = "passwort", nullable = false, length = 255)
    private String password;
    @Basic
    @Column(name = "studiengang", nullable = false, length = 255)
    private String courseOfStudy;
    @Basic
    @Column(name = "matrikel_nr", nullable = true, unique=true)
    private Integer matrikelNr;

    @Basic
    @Column(name = "role", nullable = true, columnDefinition = "VARCHAR(255) DEFAULT 'User'")
    private String role;
    @Transient
    private boolean fullAccess;
    @Basic
    @Column(name = "registrierungsdatum", nullable = true)
    private Timestamp dateOfRegistration;

    @Transient //wird nicht in der datenbank benötigt
    private boolean loggedIn;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "scores_id", referencedColumnName = "scores_id")
    @JsonManagedReference
    private ScoreUser scoreUser;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Teams team;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Fragen> fragen;

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", courseOfStudy='" + courseOfStudy + '\'' +
                ", matrikelNr=" + matrikelNr +
                ", role='" + role + '\'' +
                ", fullAccess=" + fullAccess +
                ", dateOfRegistration=" + dateOfRegistration +
                ", loggedIn=" + loggedIn +
                ", scoreUser=" + scoreUser +
                ", team=" + team +
                ", fragen=" + fragen +
                '}';
    }

    @PrePersist
    public void prePersist() {
        if (dateOfRegistration == null) {
            dateOfRegistration = new Timestamp(System.currentTimeMillis());
        }

        if (this.scoreUser == null) {
            this.scoreUser = new ScoreUser();
            this.scoreUser.setPunkteGesamt(0);
            this.scoreUser.setPunkteMonat(0);
            this.scoreUser.setPunkteWoche(0);
            this.scoreUser.setFrageRichtig(0);
            this.scoreUser.setFragenGesamt(0);
            this.scoreUser.setUser(this);  // Diese Zeile setzt die Beziehung
        }

    }

    private static final String ROLE_ADMIN = "Admin";
    private static final String ROLE_USER = "User";

    // Getter und Setter
    public ScoreUser getScoreUser() {
        return scoreUser;
    }

    public void setScoreUser(ScoreUser scoreUser) {
        this.scoreUser = scoreUser;
    }

    public User(int userId, String username, String firstName, String lastName, String password, String email, int martikelnummer, String courseofstudy, String role) {
        this.userID = userId;
        this.userName = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email =email;
        this.matrikelNr = martikelnummer;
        this.courseOfStudy = courseofstudy;
        this.role = role;
        this.dateOfRegistration = getDateOfRegistration();
        this.loggedIn = false; // Ein neuer Benutzer ist zu Beginn nicht eingeloggt

    }


    public User( String username, String firstName, String lastName, String password, String email, int martikelnummer, String courseofstudy, String role) {
        this.userName = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email =email;
        this.matrikelNr = martikelnummer;
        this.courseOfStudy = courseofstudy;
        this.role = role;
        this.dateOfRegistration = getDateOfRegistration();
        this.loggedIn = false; // Ein neuer Benutzer ist zu Beginn nicht eingeloggt
    }

    public User( String username, String firstName, String lastName, String password, String email, int martikelnummer, String courseofstudy) {
        this.userName = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email =email;
        this.matrikelNr = martikelnummer;
        this.courseOfStudy = courseofstudy;
this.dateOfRegistration = getDateOfRegistration();
        this.loggedIn = false; // Ein neuer Benutzer ist zu Beginn nicht eingeloggt
    }



    public User( ) {
        this.role = "User";
        this.userName = "";
        this.firstName = "";
        this.lastName = "";
        this.loggedIn = false; // Ein neuer Benutzer ist zu Beginn nicht eingeloggt
    }

    public void login() {
        this.loggedIn = true;
    }

    public void logout( ) {

        this.loggedIn = false;
        System.out.println("Ausloggen by User.logout()");
    }

    public Teams getTeam() {
        return team;
    }

    public void setTeam(Teams team) {
        this.team = team;
    }
    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCourseOfStudy() {
        return courseOfStudy;
    }

    public void setCourseOfStudy(String courseOfStudy) {
        this.courseOfStudy = courseOfStudy;
    }

    public Integer getMatrikelNr() {
        return matrikelNr;
    }

    public void setMatrikelNr(Integer matrikelNr) {
        this.matrikelNr = matrikelNr;
    }

    public Timestamp getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Timestamp dateOfRegistration) {
        if (this.dateOfRegistration != null){

        this.dateOfRegistration = dateOfRegistration;
        }
       // else {
        //    this.setDateOfRegistration(new Timestamp(System.currentTimeMillis()));

      //  }


}
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isFullAccess() {
        setFullAccess();
        return this.fullAccess;
    }

    public void setFullAccess() {
        if(this.role.equals("User")) {
            this.fullAccess = false;
        }
        else if(this.role.equals("Admin")){
            this.fullAccess = true;
        }
    else {this.fullAccess=false;}
    }

    public void setRole(String role) {

        this.role = role;
        setFullAccess();
    }

    public String getRole() {
        return role;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(userID, that.userName) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(userName, that.userName) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(password, that.password) && Objects.equals(courseOfStudy, that.courseOfStudy) && Objects.equals(getMatrikelNr(), that.getMatrikelNr()) && Objects.equals(dateOfRegistration, that.dateOfRegistration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, firstName, lastName, userName, email, password, courseOfStudy, matrikelNr, dateOfRegistration,role);
    }

    public List<Fragen> getFragen() {
        return fragen;
    }

    public void setFragen(List<Fragen> fragen) {
        this.fragen = fragen;
    }
}
