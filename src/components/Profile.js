import React from 'react';
import { Link } from 'react-router-dom'; // Importiere Link für die Navigation
import './styles/Profile.css'; // Importiere das Stylesheet

const Profile = () => {
    // Beispielwerte für das Profil
    const profileData = {
        firstName: 'Max',
        lastName: 'Mustermann',
        username: 'maxmuster',
        email: 'max@example.com',
        registrationDate: '2023-09-28',
        totalScore: 1000,
        monthlyScore: 250,
        weeklyScore: 50,
        role: 'Benutzer',
        major: 'Informatik',
        teamName: 'Team A', // Beispiel für Teamname
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
                        <strong>Registrierungsdatum:</strong> {profileData.registrationDate}
                    </p>
                </div>
                <div className="profile-stats">
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
                        <strong>Teamname:</strong> <Link to="/team">{profileData.teamName}</Link>
                    </p>
                    <p>
                        <strong>Studiengang:</strong> {profileData.major}
                    </p>
                </div>
            </div>

            {/* Link zu den Rankings */}
            <div className="profile-rankings">
                <Link to="/Ranking">Zu den Rankings</Link>
            </div>
        </div>
    );
};

export default Profile;
