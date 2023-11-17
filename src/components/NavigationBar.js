import React, { useState } from 'react';
import './styles/NavigationBar.css';
import User from './User'

const NavigationBar = () => {
    //Inhalte der NavigationsLeiste
    const [links] = useState([
        { text: 'Team', url: '/TeamErstellung' },
        { text: 'Quiz starten', url: '/QuizSelection' },
        { text: 'Quizfrage erstellen', url: '/FragenErstellen' },
        { text: 'Ranking Tabelle', url: '/RankingUser' },
        { text: 'Profil', url: '/Profile' },
    ]);

    const handleNavigation = (url) => {
        window.location.href = url;
    };

    const handleLogout = () => {
        User.logout();
       
        // Weiterleitung auf Startseite nach Logout
        window.location.href = '/';
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
