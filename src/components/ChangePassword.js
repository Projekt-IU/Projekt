import React, { useState } from 'react';
import axios from 'axios';
import './styles/ChangePassword.css'; // Importiere die CSS-Datei
import User from "./User";
import NavigationBar from "./NavigationBar";

const ChangePassword = () => {
    User.loadFromSession();
    const userData = {
        username: User.username,
    };

    const [formData, setFormData] = useState({
        oldPassword: '',
        newPassword: '',
        confirmNewPassword: '',
    });

    const [message, setMessage] = useState('');
    const [error, setError] = useState({});

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (formData.newPassword !== formData.confirmNewPassword) {
            setError({ confirmNewPassword: 'Die neuen Passwörter stimmen nicht überein.' });
            return;
        }

        // Ersetze 'YOUR_API_ENDPOINT' durch den tatsächlichen API-Endpunkt auf deinem Backend.
        const apiEndpoint = 'http://localhost:8080/api/newPw';

        try {
            const response = await axios.post(apiEndpoint, {
                username: userData.username,
                password: formData.oldPassword,
                anfrageName: formData.newPassword,
            });
            if (response.status === 200) {
                setMessage('Passwort wurde erfolgreich geändert.');
                User.password = formData.newPassword;
                alert('Passwort wurde erfolgreich geändert');
                window.location.href = '/login';
                }
            else {
                setError({ message: 'Es kam zu einem unerwarteten Fehler. Bitte versuchen Sie es später erneut.' });
            }
        } catch (error) {
            console.error('Fehler bei der Passwortänderung:', error);
            setError({ message: 'Es kam zu einem unerwarteten Fehler. Bitte versuchen Sie es später erneut.' });
        }
    };

    return (
        <div>
            <NavigationBar/>

        <div className="changepw-container">
           
            <h1>Passwort ändern</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Altes Passwort</label>
                    <input
                        type="password"
                        name="oldPassword"
                        value={formData.oldPassword}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Neues Passwort</label>
                    <input
                        type="password"
                        name="newPassword"
                        value={formData.newPassword}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Neues Passwort bestätigen</label>
                    <input
                        type="password"
                        name="confirmNewPassword"
                        value={formData.confirmNewPassword}
                        onChange={handleChange}
                        required
                    />
                    {message && <p className="success-message">{message}</p>}
                    {error.message && <p className="error-message">{error.message}</p>}
                    {error.confirmNewPassword && <p className="error-message">{error.confirmNewPassword}</p>}
                </div>
                <button type="submit">Passwort ändern</button>
            </form>
        </div>
        </div>
    );
};

export default ChangePassword;
