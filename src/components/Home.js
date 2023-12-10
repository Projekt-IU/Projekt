import React from 'react';
import { Link } from 'react-router-dom';
import './styles/Home.css';

function Home() {
    return (
        <div className="home-container">
            <h1 className="home-title">Willkommen bei dem Quizsystem für IU Studenten!</h1>
            <p className="home-description">Von Studenten für Studenten</p>
            <div className="action-buttons">
                <Link to="/login">
                    <button className="action-button">Login</button>
                </Link>
                &nbsp;&nbsp;&nbsp;
                <Link to="/Registrierung">
                    <button className="action-button">Registrieren</button>
                </Link>
            </div>
        </div>
    );
}

export default Home;