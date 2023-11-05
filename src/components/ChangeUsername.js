import React, { useState } from 'react';
import axios from 'axios';
import './styles/ChangeUsername.css'; // Importiere die CSS-Datei
import User from "./User";
import NavigationBar from "./NavigationBar";

const ChangeUsername = () => {
    User.loadFromSession();
    const [newUsername, setNewUsername] = useState('');
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const handleChange = (e) => {
        setNewUsername(e.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!newUsername) {
            setError('Bitte geben Sie einen neuen Benutzernamen ein.');
            return;
        }

        const userData = {
            username: User.username,
            password: User.password,
        };

        // Ersetze 'YOUR_API_ENDPOINT' durch den tatsächlichen API-Endpunkt auf deinem Backend.
        const apiEndpoint = 'http://localhost:8080/api/changeUserName';

        try {
            const response = await axios.post(apiEndpoint, {
                username: userData.username,
                password: userData.password,
                anfrageName: newUsername,
            });

            if (response.status === 200) {
                setMessage('Benutzername wurde erfolgreich geändert.');
                setError('');
                // Aktualisiere den Benutzernamen im aktuellen Benutzerobjekt
                alert("Benutzername wurde erfolgreich geändert");
                window.location.href = '/login';
                User.username = newUsername;

            } else {
                setError('Der Benutzername existiert bereits.');
            }
        } catch (error) {
            console.error('Fehler beim Ändern des Benutzernamens:', error);
            setError('Es kam zu einem unerwarteten Fehler. Bitte versuchen Sie es später erneut.');
        }
    };

    return (
        <div className="container">
            <NavigationBar/>
            <h1>Benutzernamen ändern</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Aktueller Benutzername: {User.username}</label>
                </div>
                <div className="form-group">
                    <label>Neuer Benutzername</label>
                    <input
                        type="text"
                        name="newUsername"
                        value={newUsername}
                        onChange={handleChange}
                        required
                    />
                    {message && <p className="success-message">{message}</p>}
                    {error && <p className="error-message">{error}</p>}
                </div>
                <button type="submit">Benutzernamen ändern</button>
            </form>
        </div>
    );
};

export default ChangeUsername;
