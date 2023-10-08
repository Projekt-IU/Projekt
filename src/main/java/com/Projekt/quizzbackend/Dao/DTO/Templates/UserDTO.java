package com.Projekt.quizzbackend.Dao.DTO.Templates;

import java.sql.Timestamp;

public class UserDTO {


        private Integer userID;
        private String firstName;
        private String lastName;
        private String userName;
        private String email;
        private String courseOfStudy;
        private Integer matrikelNr;
        private String role;
        private String teamName;
        private boolean fullAccess;
        private Timestamp dateOfRegistration;
        private boolean loggedIn;



        private static final String ROLE_ADMIN = "Admin";
        private static final String ROLE_USER = "User";

        // Getter und Setter

        public UserDTO(int userId, String username, String firstName, String lastName, String email, int martikelnummer, String courseofstudy, String role) {
            this.setUserID(userId);
            this.userName = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email =email;
            this.matrikelNr = martikelnummer;
            this.courseOfStudy = courseofstudy;
            this.role = role;
            this.setDateOfRegistration(getDateOfRegistration());
            this.loggedIn = false; // Ein neuer Benutzer ist zu Beginn nicht eingeloggt

        }


        public UserDTO(String username, String firstName, String lastName, String email, int martikelnummer, String courseofstudy, String role) {
            this.userName = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email =email;
            this.matrikelNr = martikelnummer;
            this.courseOfStudy = courseofstudy;
            this.role = role;
            this.setDateOfRegistration(getDateOfRegistration());
            this.loggedIn = false; // Ein neuer Benutzer ist zu Beginn nicht eingeloggt
        }



        public UserDTO( ) {
            this.role = "User";
            this.userName = "";
            this.firstName = "";
            this.lastName = "";
            this.loggedIn = false; // Ein neuer Benutzer ist zu Beginn nicht eingeloggt
        }


        public Integer getUserID() {
            return userID;
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


    public void setDateOfRegistration(Timestamp dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
