import React, { Component } from 'react';
import { Navigate } from "react-router-dom";
import User from "./User";
import axios from "axios"; // Importieren Sie die useUser-Hook
import ChatComponent from './ChatComponent';
class UserComponents extends Component {
    
    constructor() {
        super();
        User.loadFromSession();
        this.state = {
            loggedOut: false,
            
        };
    }

    logout = () => {


        console.log("Logge aus");
        const logoutRequest = {
            username: User.username, // Nehmen Sie den Benutzernamen aus Ihrer Anwendung
            userId: User.userId // Nehmen Sie die Benutzer-ID aus Ihrer Anwendung

        };

        console.log(JSON.stringify(logoutRequest));
        axios.post('http://localhost:8080/api/logout', logoutRequest)
            .then(response => {
                if (response.status === 200) {
                    User.logout();
                    console.log(User.loggedIn);
                    User.saveToSession(User);
                    this.setState({ loggedOut: true });
                }
                console.log('User', User.password);
            })
            .catch(error => {
                console.error("Logout-Fehler:", error);
            });
    }
    render() {
        if (User.loggedIn) {
            // Benutzer ist angemeldet, personalisierte Inhalte anzeigen
            return (
                <div>
                    <p>Willkommen, {User.firstName + " " + User.lastName}!</p>
                    <button onClick={this.logout}>Abmelden</button>
                </div>
            );
        } else if (this.state.loggedOut) {
            // Benutzer wurde ausgeloggt und wird zur Startseite weitergeleitet
            return <Navigate to="/" />;
        } else {
            // Benutzer ist nicht angemeldet, Anmeldeformular anzeigen
            return <Navigate to={'/'} />;
        }
    }
}

export default UserComponents;