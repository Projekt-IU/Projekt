import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import './styles/Profile.css';
import axios from "axios";
import User from "./User";

const Profile = () => {
    const [profileData, setProfileData] = useState({
        firstName: '',
        lastName: '',
        username: '',
        email: '',
        registrationDate: '',
        totalScore: 0,
        monthlyScore: 0,
        weeklyScore: 0,
        role: '',
        teamName: '',
        courseOfStudy: '',
    });

    useEffect(() => {
        // Hole Benutzername und Passwort aus User.js
        const userData = {
            username: User.username,
            password: User.password
        };

        axios.post('http://localhost:8080/api/getProfil', userData)
            .then(response => {
                if (response.status === 200) {
                    const {
                        firstName,
                        lastName,
                        userName, // Aktualisierte Schlüsselname
                        email,
                        dateOfRegistration, // Aktualisierte Schlüsselname
                        scoreUser,
                        matrikelNr,
                        role,
                        teamName,
                        courseOfStudy, // Hinzugefügter Schlüssel
                    } = response.data;

                    setProfileData({
                        firstName,
                        lastName,
                        username: userName,
                        matrikelNr,
                        email,
                        registrationDate: formatDate(dateOfRegistration),
                        totalScore: scoreUser.punkteGesamt,
                        monthlyScore: scoreUser.punkteMonat,
                        weeklyScore: scoreUser.punkteWoche,
                        frageRichtig: scoreUser.frageRichtig,
                        fragenGesamt: scoreUser.fragenGesamt,
                        role,
                        teamName,
                        courseOfStudy,
                    });
                }
            })
            .catch(error => {
                console.log("Error Response:", error.response);
            });
    }, []);

    // Function to format the date as "Tag.Monat.Jahr"
    const formatDate = (dateString) => {
        const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
        const date = new Date(dateString);
        return date.toLocaleDateString('de-DE', options);
    };

    // Function to handle account deletion
    const handleDeleteAccount = async () => {
        const confirmDelete = window.confirm("Möchten Sie Ihren Account wirklich löschen?");

        if (confirmDelete) {
            try {
                // Ersetze 'YOUR_API_ENDPOINT' durch den tatsächlichen API-Endpunkt auf deinem Backend.
                const apiEndpoint = 'http://localhost:8080/api/dropUser';

                const response = await axios.post(apiEndpoint, {
                    username: User.username, // Verwende den aktuellen Benutzernamen
                    password: User.password,
                    //anfrageID: 0,
                    //anfrageName: ""
                });

                if (response.status === 200) {
                    // Hier kannst du den Benutzer ausloggen oder eine entsprechende Aktion durchführen.
                    // Zum Beispiel: window.location.href = '/logout';
                    alert("Account wurde erfolgreich gelöscht.");
                    window.location.href = '/';
                } else {
                    alert("Fehler beim Löschen des Kontos. Bitte versuchen Sie es später erneut.");
                }
            } catch (error) {
                console.error('Fehler beim Löschen des Kontos:', error);
                alert("Fehler beim Löschen des Kontos. Bitte versuchen Sie es später erneut.");
            }
        }
    };

    return (
        <div className="container emp-profile">
            <h1>Dein Profil</h1>
            <div className="profile-details">
                <div className="profile-info">
                    <p>
                        <strong>Vorname:</strong> {profileData.firstName}
                    </p>
                    <p>
                        <strong>Nachname:</strong> {profileData.lastName}
                    </p>
                    <p>
                        <strong>Benutzername:</strong> {profileData.username}
                    </p>
                    <p>
                        <strong>Email:</strong> {profileData.email}
                    </p>
                    <p>
                        <strong>Matrikel Nummer:</strong> {profileData.matrikelNr}
                    </p>
                    <p>
                        <strong>Studiengang:</strong> {profileData.courseOfStudy}
                    </p>
                    <p>
                        <strong>Registrierungsdatum:</strong> {profileData.registrationDate}
                    </p>

                </div>
                <div className="profile-stats">
                    {/* Center contents of profile-stats */}
                    <div className="centered-content">
                        <p>
                            <strong>Punktestand gesamt:</strong> {profileData.totalScore}
                        </p>
                        <p>
                            <strong>Punktestand Monat:</strong> {profileData.monthlyScore}
                        </p>
                        <p>
                            <strong>Punktestand Woche:</strong> {profileData.weeklyScore}
                        </p>
                        <p>
                            <strong>Fragen richtig:</strong> {profileData.frageRichtig}
                        </p>
                        <p>
                            <strong>Fragen Gesamt:</strong> {profileData.fragenGesamt}
                        </p>
                        <p>
                            <strong>Teamname:</strong> <Link to="/team">{profileData.teamName}</Link>
                        </p>
                        <p>
                            <Link to="/RankingUser">Zu den Rankings User</Link>
                        </p>
                        <p>
                            <Link to="/RankingTeam">Zu den Rankings Team</Link>
                        </p>
                    </div>
                </div>

            </div>
            <div className="centered-content">
                {/* Knopf zum Ändern des Passworts */}
                <button onClick={() => window.location.href = '/changePassword'}>
                    Passwort ändern
                </button>
                {/* Knopf zum Löschen des Kontos */}
                <button onClick={handleDeleteAccount}>
                    Account löschen
                </button>
                {/* Knopf zum Ändern des Benutzernamens */}
                <button onClick={() => window.location.href = '/changeUsername'}>
                    Benutzernamen ändern
                </button>
            </div>
        </div>
    );
};

export default Profile;
