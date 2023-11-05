import React, { Component } from 'react';
import styles from './styles/Registrierung.css';
import axios from "axios";
import { Navigate } from "react-router-dom";
import User from "./User";

class Registrierung extends Component {
    constructor() {
        super();
        this.state = {
            userName: '',
            firstName: '',
            lastName: '',
            courseOfStudy: '',
            email: '',
            matrikelNr: '',
            password: '',
            confirmPassword: '',
            registered: false,
            error: '',
        };
    }

    handleChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    };

    handleSubmit = (e) => {
        e.preventDefault();
        const { userName, firstName, lastName, courseOfStudy, email, matrikelNr, password, confirmPassword  } = this.state;

        // Überprüfen, ob Passwort und Passwortbestätigung übereinstimmen
        if (password !== confirmPassword) {
            this.setState({ error: "Passwörter stimmen nicht überein!" });
            return;  // Beenden der Funktion, da die Passwörter nicht übereinstimmen
        }

        // Axios URL noch ändern!
        axios.post('http://localhost:8080/api/userRegistrieren', { userName, firstName, lastName, courseOfStudy, email, matrikelNr, password })
            .then(response => {
                if (response.status === 200) {
                    this.setState({ error: "Registrierung erfolgreich!", registered: true });
                } else {
                    this.setState({ error: "Registrierung fehlgeschlagen!" });
                }
            })
            .catch(error => {
                console.error("Registrierung fehlgeschlagen", error);
                this.setState({ error: "Registrierung fehlgeschlagen" });
            });
    };

    render() {
        if (this.state.registered) {
            return <Navigate to="/Profile" />
        }

        // Frontend
        return (
            <div className="registrierung centered-container">
                <div className="register-box">
                    <h2>Registrierung</h2>
                    <form onSubmit={this.handleSubmit}>
                        <div>
                            <label htmlFor="userName">Benutzername</label>
                            <input
                                type="text"
                                id="userName"
                                name="userName"
                                className="register-input"
                                value={this.state.userName}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="firstName">Vorname</label>
                            <input
                                type="text"
                                id="firstName"
                                name="firstName"
                                className="register-input"
                                value={this.state.firstName}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="lastName">Nachname</label>
                            <input
                                type="text"
                                id="lastName"
                                name="lastName"
                                className="register-input"
                                value={this.state.lastName}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="email">Email</label>
                            <input
                                type="email"
                                id="email"
                                name="email"
                                className="register-input"
                                value={this.state.email}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="matrikelNr">Matrikelnummer</label>
                            <input
                                type="text"
                                id="matrikelNr"
                                name="matrikelNr"
                                className="register-input"
                                value={this.state.matrikelNr}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="courseOfStudy">Studiengang</label>
                            <select
                                id="courseOfStudy"
                                name="courseOfStudy"
                                value={this.state.courseOfStudy}
                                onChange={this.handleChange}
                            >
                                <option value="">Bitte auswählen:</option>
                                <option value="Informatik">Informatik</option>
                                <option value="Wirtschaftsinformatik">Wirtschaftsinformatik</option>
                                <option value="Medieninformatik">Medieninformatik</option>
                            </select>
                        </div>
                        <div>
                            <label htmlFor="password">Passwort</label>
                            <input
                                type="password"
                                id="password"
                                name="password"
                                className="register-input"
                                value={this.state.password}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="confirmPassword">Passwort wiederholen</label>
                            <input
                                type="password"
                                id="confirmPassword"
                                name="confirmPassword"
                                className="register-input"
                                value={this.state.confirmPassword}
                                onChange={this.handleChange}
                            />
                        </div>
                        <button type="submit">Registrieren</button>  {/* Hinzufügen einer Schaltfläche zum Absenden des Formulars */}
                    </form>
                    {this.state.error && <p className="error-message">{this.state.error}</p>}
                </div>
            </div>
        );
    }
}

export default Registrierung;
