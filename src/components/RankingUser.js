import React, { useState, useEffect } from 'react';
import './styles/Ranking.css';
import axios from 'axios';
import User from './User';
import NavigationBar from "./NavigationBar";

const RankingUser = () => {
    const [activeTab, setActiveTab] = useState('total');
    const [rankingData, setRankingData] = useState([]);
    const [rangplatz, setRangplatz] = useState(null);
    const [scoreGesamt, setScoreGesamt] = useState(0);
    const [scoreMonat, setScoreMonat] = useState(0);
    const [scoreWoche, setScoreWoche] = useState(0);

    const fetchRankingData = async (anfrageName) => {
        try {
            const response = await axios.post('http://16.170.229.65:8080/api/score/getScoreUserList', {
                username: User.username,
                password: User.password,
                anfrageName: anfrageName,
            });
            setRankingData(response.data);
        } catch (error) {
            console.error('Fehler beim Abrufen der Daten:', error);
        }
    };

    useEffect(() => {
        let anfrageName = '';

        if (activeTab === 'total') {
            anfrageName = 'Gesamt';
        } else if (activeTab === 'monthly') {
            anfrageName = 'Monat';
        } else if (activeTab === 'weekly') {
            anfrageName = 'Woche';
        }

        fetchRankingData(anfrageName);
    }, [activeTab]);

    useEffect(() => {
        // Finde den Rangplatz des Benutzers
        if (rankingData.length > 0) {
            const userIndex = rankingData.findIndex((user) => user.username === User.username);
            if (userIndex > -1) {
                setRangplatz(userIndex + 1);
                setScoreGesamt(rankingData[userIndex].scoreGesamt);
                setScoreMonat(rankingData[userIndex].scoreMonat);
                setScoreWoche(rankingData[userIndex].scoreWoche);
            }
        }
    }, [rankingData]);

    return (

        <div className="ranking-container">
            <NavigationBar/>

            <div className="ranking-tabs">

                <button onClick={() => setActiveTab('total')}>Punkte gesamt</button>
                <button onClick={() => setActiveTab('monthly')}>Punkte im Monat</button>
                <button onClick={() => setActiveTab('weekly')}>Punkte in der Woche</button>
            </div>

            <div className="ranking-table">
                <h2>{`Punkte ${activeTab === 'total' ? 'gesamt' : activeTab === 'monthly' ? 'im Monat' : 'in der Woche'}`}</h2>
                <table>
                    <thead>
                    <tr>
                        <th>Rangplatz</th>
                        <th>Student</th>
                        <th>{activeTab === 'total' ? 'Punktzahl' : activeTab === 'monthly' ? 'Punktzahl' : 'Punktzahl'}</th>
                    </tr>
                    </thead>
                    <tbody>
                    {rankingData.slice(0, 15).map((user, index) => (
                        <tr key={index}>
                            <td>{index + 1}</td>
                            <td>{user.username === User.username ? <strong>{user.username}</strong> : user.username}</td>
                            <td>
                                {activeTab === 'total'
                                    ? user.scoreGesamt
                                    : activeTab === 'monthly'
                                        ? user.scoreMonat
                                        : user.scoreWoche
                                }
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>

            {rangplatz !== null && (
                <div className="user-rank-info">
                    <p>{`Dein Rangplatz: ${rangplatz}`}</p>
                    <p>{`Deine Punktzahl: ${
                        activeTab === 'total'
                            ? scoreGesamt
                            : activeTab === 'monthly'
                                ? scoreMonat
                                : scoreWoche
                    }`}</p>
                </div>
            )}
        </div>
    );
};

export default RankingUser;
