import React, { useState } from 'react';
import './styles/NavigationBar.css';
import User from './User'

const NavigationBar = () => {
    const [links] = useState([
        { text: 'Home', url: '/' },
        { text: 'Quiz starten', url: '/QuizSelection' },
        { text: 'Quizfrage erstellen', url: '/FragenErstellen' },
        { text: 'Ranking Tabelle', url: '/RankingUser' },
        { text: 'Profil', url: '/Profile' },
        // Weitere Links hinzufügen, wenn nötig
    ]);

    const handleNavigation = (url) => {
        window.location.href = url;
    };

    const handleLogout = () => {
        // Führe hier die User.logout() Funktion aus, um den Benutzer auszuloggen
        User.logout();
        // Nach dem Ausloggen kannst du zur Login-Seite oder zur Startseite weiterleiten
        window.location.href = '/login'; // Beispiel für eine Login-Seite
    };

    return (
        <div className="navbar">
            {links.map((link, index) => (
                <div
                    key={index}
                    className="navbar-item"
                    onClick={() => handleNavigation(link.url)}
                >
                    {link.text}
                </div>
            ))}
            <div
                className="navbar-item"
                onClick={handleLogout}
            >
                Logout
            </div>
        </div>
    );
};

export default NavigationBar;
