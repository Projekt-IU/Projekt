import React, { useState, useEffect } from 'react';
import axios from 'axios';
import User from './User'; // Ihr User-Objekt
import ChatComponent from "../components/ChatComponent";
const ChatEinbindungExample = () => {
    const [teamName, setTeamName] = useState(null);

    useEffect(() => {
        // Authentifizierungsinformationen aus der Anwendung holen
        const authRequest = {
            username: User.username,
            password: User.password,
        };

        axios.post('http://localhost:8080/api/getProfil', authRequest)
            .then(response => {
                const teamNameFromResponse = response.data.teamName;
                setTeamName(teamNameFromResponse);
            })
            .catch(error => {
                console.error('Fehler beim Abrufen des Profils:', error);
            });
    }, []);  // Leeres Abhängigkeitsarray bedeutet, dass dieser Effekt nur einmal ausgeführt wird

    // Logout-Funktion
    const handleLogout = () => {
        // Logout-Logik hier
        // Zum Beispiel: User.username = null; User.password = null;
        window.location.href = '/'; // Oder wohin auch immer Sie den Benutzer nach dem Logout umleiten möchten
    };

    return (
        <div>
            <h1>Willkommen zum Team-Chat</h1>
            <button onClick={handleLogout}>Logout</button>
            {teamName && <ChatComponent teamName={teamName} username={User.username} password={User.password} />}
        </div>
    );
};

export default ChatEinbindungExample;