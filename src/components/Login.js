import React, { Component } from 'react';
import './styles/Login.css';
import axios from "axios";
import { Navigate } from "react-router-dom";
import User from "./User";

class Login extends Component {
    constructor() {
        super();
        this.state = {
            username: '',
            password: '',
            email: '',  // Für Passwort vergessen
            showForgotPasswordDialog: false,  // Für Passwort vergessen Dialog
            loggedIn: false,
            error: ''
        };
    }

    handleChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    };

    handleSubmit = (e) => {
        e.preventDefault();
        // Hier können Sie die Anmeldelogik implementieren und Daten an den Server senden
        const { username, password } = this.state;

        axios.post('http://localhost:8080/api/login', { username, password })
            .then(response => {
                // Wenn die Anmeldung erfolgreich ist, können Sie den Benutzer weiterleiten oder andere Aktionen ausführen
                if (response.data) {
                    const { userID, userName, firstName, lastName } = response.data;
                    User.login(userID, userName, firstName, lastName, password); // Benutzer einloggen
                    User.saveToSession();
                    console.log(User);
                    this.setState({ error: 'Login erfolgreich' });
                    this.setState({ loggedIn: true, user: username }); //Status auf eingeloggt


                    console.log( this.state);
                    console.log(  User);


                }
                else {
                    this.setState({ error: 'Falscher Nutzername oder Passwort' });
                }
            })

            .catch(error => {
                // Wenn die Anmeldung fehlschlägt, können Sie einen Fehler anzeigen oder andere Aktionen ausführen
                console.error('Anmeldung fehlgeschlagen:', error);
                this.setState({ error: 'Falscher Nutzername oder Passwort' }); // Fehlermeldung setzen

            });


        console.log('Anmeldung mit:', username, password);

    };


    handleForgotPassword = () => {
        this.setState({ showForgotPasswordDialog: true });
    };

    handleSendEmail = () => {
        axios.post('http://localhost:8080/api/forgotpw', { email: this.state.email })
            .then(response => {
                alert('E-Mail gesendet');
                this.setState({ showForgotPasswordDialog: false });
            })
            .catch(error => {
                alert('Fehler beim Senden der E-Mail');
            });
    };

    render() {
        if (this.state.loggedIn) {
            return <Navigate to="/UserComponents" />;
        }

        return (
            <div className="centered-container">
                <div className="login-box">
                    <h2>Anmelden</h2>
                    <form onSubmit={this.handleSubmit}>
                        <div>
                            <label htmlFor="username">Benutzername</label>
                            <input
                                type="text"
                                id="username"
                                name="username"
                                className="login-input"
                                value={this.state.username}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="password">Passwort</label>
                            <input
                                type="password"
                                id="password"
                                name="password"
                                className="login-input"
                                value={this.state.password}
                                onChange={this.handleChange}
                            />
                        </div>
                        {this.state.error && <p className="error">{this.state.error}</p>} {/* Fehlermeldung anzeigen */}
                        <button type="submit" className="login-button">Anmelden</button>
                    </form>
                    <button onClick={this.handleForgotPassword} className="forgot-password-button">Passwort vergessen</button>

                    {/* Passwort vergessen Dialog */}
                    {this.state.showForgotPasswordDialog && (
                        <div className="overlay">
                        <div className="forgot-password-dialog">
                            <h3>Passwort vergessen</h3>
                            <label htmlFor="email">E-Mail-Adresse: </label>
                            <input
                                type="email"
                                id="email"
                                name="email"
                                className="PWforgot-input"
                                value={this.state.email}
                                onChange={this.handleChange}
                            />
                            <button onClick={this.handleSendEmail} className="forgot-password-button">Senden</button>
                        </div>
                        </div>
                    )}

                </div>
            </div>
        );
    }
}

export default Login;