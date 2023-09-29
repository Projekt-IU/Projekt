// Ranking.js
import React, { useState } from 'react';
import './styles/Ranking.css'; // Importiere das Stylesheet für Ranking

const Ranking = () => {
    const [activeTab, setActiveTab] = useState('total'); // Aktiver Tab

    // Beispielwerte für die Ranking-Tabellen
    const totalRankingData = Array(30).fill(null).map((_, index) => ({
        name: `Benutzer ${index + 1}`,
        major: `Studiengang ${index + 1}`,
        score: Math.floor(Math.random() * 1000), // Zufällige Punktzahl zwischen 0 und 1000
    }));

    // Sortiere die Ranking-Daten nach Punktzahl absteigend
    totalRankingData.sort((a, b) => b.score - a.score);

    // Wähle die ersten 15 Benutzer aus
    const top15TotalRankingData = totalRankingData.slice(0, 15);

    // Beispielwerte für Punkte im Monat
    const monthlyRankingData = Array(30).fill(null).map((_, index) => ({
        name: `Benutzer ${index + 1}`,
        major: `Studiengang ${index + 1}`,
        score: Math.floor(Math.random() * 500), // Zufällige Punktzahl zwischen 0 und 500
    }));

    // Sortiere die Ranking-Daten nach Punktzahl absteigend
    monthlyRankingData.sort((a, b) => b.score - a.score);

    // Wähle die ersten 15 Benutzer aus
    const top15MonthlyRankingData = monthlyRankingData.slice(0, 15);

    // Beispielwerte für Punkte in der Woche
    const weeklyRankingData = Array(30).fill(null).map((_, index) => ({
        name: `Benutzer ${index + 1}`,
        major: `Studiengang ${index + 1}`,
        score: Math.floor(Math.random() * 100), // Zufällige Punktzahl zwischen 0 und 100
    }));

    // Sortiere die Ranking-Daten nach Punktzahl absteigend
    weeklyRankingData.sort((a, b) => b.score - a.score);

    // Wähle die ersten 15 Benutzer aus
    const top15WeeklyRankingData = weeklyRankingData.slice(0, 15);

    return (
        <div className="ranking-container">
            {/* Tabs für Ranking-Tabelle auswählen */}
            <div className="ranking-tabs">
                <button onClick={() => setActiveTab('total')}>Punkte gesamt</button>
                <button onClick={() => setActiveTab('monthly')}>Punkte im Monat</button>
                <button onClick={() => setActiveTab('weekly')}>Punkte in der Woche</button>
            </div>

            {/* Anhand des ausgewählten Tabs die entsprechende Ranking-Tabelle anzeigen */}
            {activeTab === 'total' && (
                <div className="ranking-table">
                    <h2>Punkte gesamt</h2>
                    <table>
                        <thead>
                        <tr>
                            <th>Rangplatz</th>
                            <th>Name</th>
                            <th>Studiengang</th>
                            <th>Punktzahl</th>
                        </tr>
                        </thead>
                        <tbody>
                        {top15TotalRankingData.map((user, index) => (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{user.name}</td>
                                <td>{user.major}</td>
                                <td>{user.score}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            )}
            {activeTab === 'monthly' && (
                <div className="ranking-table">
                    <h2>Punkte im Monat</h2>
                    <table>
                        <thead>
                        <tr>
                            <th>Rangplatz</th>
                            <th>Name</th>
                            <th>Studiengang</th>
                            <th>Punktzahl</th>
                        </tr>
                        </thead>
                        <tbody>
                        {top15MonthlyRankingData.map((user, index) => (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{user.name}</td>
                                <td>{user.major}</td>
                                <td>{user.score}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            )}
            {activeTab === 'weekly' && (
                <div className="ranking-table">
                    <h2>Punkte in der Woche</h2>
                    <table>
                        <thead>
                        <tr>
                            <th>Rangplatz</th>
                            <th>Name</th>
                            <th>Studiengang</th>
                            <th>Punktzahl</th>
                        </tr>
                        </thead>
                        <tbody>
                        {top15WeeklyRankingData.map((user, index) => (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{user.name}</td>
                                <td>{user.major}</td>
                                <td>{user.score}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default Ranking;
