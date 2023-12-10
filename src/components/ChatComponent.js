import React, { useState, useEffect } from 'react';
import axios from 'axios';
import User from './User';
import './styles/ChatComponent.css';

const formatDateTime = (datetimeStr) => {
    const options = { weekday: 'long', year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
    const datetime = new Date(datetimeStr);
    return datetime.toLocaleString('de-DE', options) + ' Uhr';
};

const ChatComponent = ({ teamName }) => {
    const user = User; // Änderung hier
    const [message, setMessage] = useState('');
    const [chatHistory, setChatHistory] = useState([]);

    const fetchMessages = () => {
        if (teamName) { // Stellen Sie sicher, dass teamName nicht null oder undefined ist
            const authRequest = {
                username: user.username,
                password: user.password,
            };

            axios.post(`http://16.170.229.65:8080/api/chat/messages/${teamName}`, authRequest)
                .then(response => {
                    setChatHistory(response.data);
                })
                .catch(error => {
                    console.error('Fehler beim Abrufen der Nachrichten:', error);
                });
        }
    };

    const sendMessage = () => {
        const chatMessageInDTO = {
            username: user.username,
            password: user.password,
            nachricht: message,
        };

        axios.post('http://16.170.229.65:8080/api/chat/send', chatMessageInDTO)
            .then(response => {
                if (response.status === 200) {
                    console.log('Nachricht gesendet');
                    fetchMessages();
                }
            })
            .catch(error => {
                console.error('Fehler beim Senden der Nachricht:', error);
            });
    };

    useEffect(() => {
        fetchMessages();
    }, [teamName]); // Überwachen von teamName auf Änderungen

    return (
        <div className="chat-container">
            <div className="chat-box">
                {chatHistory.map((chat, index) => (
                    <div key={index} className="chat-message">
                        <strong>{chat.username}</strong>: {chat.nachricht} <small>({formatDateTime(chat.created)})</small>
                    </div>
                ))}
            </div>
            <div className="chat-input">
                <input
                    type="text"
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                    placeholder="Schreibe eine Nachricht"
                />
                <button onClick={sendMessage}>Senden</button>
            </div>
        </div>
    );
};

export default ChatComponent;
