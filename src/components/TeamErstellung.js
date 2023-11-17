import React, { useState, useEffect } from 'react';
import axios from 'axios';
import User from "./User";
import './styles/TeamErstellung.css';

const TeamErstellung = () => {
    const [teamName, setTeamName] = useState('');
    const [studiengang, setStudiengang] = useState('Informatik');
    const [isLoaded, setIsLoaded] = useState(false); // Zustand für das Laden

    // Überprüft beim Laden der Komponente
    useEffect(() => {
        User.loadFromSession();
        console.log(User.teamName);
        if (User.teamName !== null) {
            window.location.href = '/TeamPage'; // Weiterleitung zur TeamPage
        } else {
            setIsLoaded(true); // Setzt den Ladezustand auf "geladen"
        }
    }, []);

    const handleTeamErstellen = async () => {
        if (!teamName) {
            alert('Teamname darf nicht leer sein!');
            return;
        }

        try {
            const response = await axios.post('http://localhost:8080/api/Team/newTeam', {
                name: teamName,
                studiengang: studiengang,
                username: User.username,
                password: User.password
            });

            if (response.status === 200) {
                alert('Neues Team erfolgreich erstellt');
                User.updateTeamname(teamName);
                window.location.href = '/TeamPage'; // Weiterleitung zur TeamPage nach erfolgreicher Erstellung
            }
        } catch (error) {
            alert('Team bereits vorhanden. Bitte wähle einen neuen Namen aus.');
        }
    };

    const handleAbbrechen = () => {
        window.location.href = '/Profile'; // Weiterleitung zum Profil
    };

    if (!isLoaded) {
        return <div>Lädt...</div>; // Anzeige während des Ladens
    }

    return (
        <div className="team-erstellung-container">
            <div className="team-erstellung-header">Team erstellen</div>
            <input
                className="team-erstellung-input"
                type="text"
                value={teamName}
                onChange={(e) => setTeamName(e.target.value)}
                placeholder="Teamname"
            />
            <select
                className="team-erstellung-select"
                value={studiengang}
                onChange={(e) => setStudiengang(e.target.value)}
            >
                <option value="Informatik">Informatik</option>
                <option value="Wirtschaftsinformatik">Wirtschaftsinformatik</option>
                <option value="Medieninformatik">Medieninformatik</option>
            </select>
            <button
                className="team-erstellung-button team-erstellung-create-button"
                onClick={handleTeamErstellen}
            >
                Team erstellen
            </button>
            <button
                className="team-erstellung-button team-erstellung-cancel-button"
                onClick={handleAbbrechen}
            >
                Abbrechen
            </button>
        </div>
    );
};

export default TeamErstellung;
