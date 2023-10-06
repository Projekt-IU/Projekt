// TeamPage.js
import React from 'react';
import './styles/TeamPage.css'; // Importiere das Stylesheet für die TeamPage
import TeamMembers from './TeamMembers'; // Importiere die TeamMembers-Komponente
import TeamDetails from './TeamDetails'; // Importiere die TeamDetails-Komponente
import TeamRanking from "./TeamRankings"; // Importiere die TeamRanking-Komponente

const TeamPage = () => {
    return (
        <div className="team-container">
            <div className="team-chat">
                {/* Hier kommt dein Chat-Fenster hin */}
                <h2>Chat-Fenster</h2>
                {/* Beispielinhalt für das Chat-Fenster */}
                <div className="chat-messages">
                    <div className="chat-message">
                        <strong>Benutzer 1:</strong> Hallo!
                    </div>
                    <div className="chat-message">
                        <strong>Benutzer 2:</strong> Hi!
                    </div>
                    {/* Weitere Chat-Nachrichten hier */}
                </div>
            </div>
            <div className="team-details">
                {/* Hier kommt die TeamDetails-Komponente hin */}
                <TeamDetails />
            </div>
            <div className="team-members">
                {/* Hier kommt die TeamMembers-Komponente hin */}
                <TeamMembers />
            </div>
            <div className="team-ranking">
                {/* Hier kommt die TeamRanking-Komponente hin */}
                <TeamRanking />
            </div>
        </div>
    );
};

export default TeamPage;
