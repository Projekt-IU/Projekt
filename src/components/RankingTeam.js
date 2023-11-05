import React, { useState, useEffect } from 'react';
import axios from 'axios';
import User from './User';
import NavigationBar from "./NavigationBar";

const RankingTeam = () => {
    const [activeTab, setActiveTab] = useState('total');
    const [rankingData, setRankingData] = useState([]);
    const [rangplatz, setRangplatz] = useState(null);
    const [scoreGesamt, setScoreGesamt] = useState(0);
    const [scoreMonat, setScoreMonat] = useState(0);
    const [scoreWoche, setScoreWoche] = useState(0);
    const [teamName, setTeamName] = useState('');
    const [userTeamIndex, setUserTeamIndex] = useState(null);

    const fetchRankingData = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/score/getScoreTeamList', {
                username: User.username,
                password: User.password,
                anfrageName: 'all',
            });
            setRankingData(response.data);
        } catch (error) {
            console.error('Fehler beim Abrufen der Daten:', error);
        }
    };

    useEffect(() => {
        fetchRankingData();
    }, [activeTab]);

    useEffect(() => {
        // Find the index of the user's team and set the user's team index
        if (rankingData.length > 0) {
            const userTeam = rankingData.find((user) => user.username === User.username);
            if (userTeam) {
                setTeamName(userTeam.name);
                setUserTeamIndex(rankingData.indexOf(userTeam));
            }
        }
    }, [rankingData]);

    useEffect(() => {
        // Find the position of the user's team
        if (userTeamIndex !== null) {
            setRangplatz(userTeamIndex + 1);
            setScoreGesamt(rankingData[userTeamIndex].punkteGesamt);
            setScoreMonat(rankingData[userTeamIndex].punkteMonat);
            setScoreWoche(rankingData[userTeamIndex].punkteWoche);
        }
    }, [userTeamIndex, rankingData]);

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
                        <th>Team</th>
                        <th>Studiengang</th>
                        <th>{activeTab === 'total' ? 'Punktzahl' : activeTab === 'monthly' ? 'Punktzahl' : 'Punktzahl'}</th>
                    </tr>
                    </thead>
                    <tbody>
                    {rankingData.slice(0, 15).map((user, index) => (
                        <tr key={index} className={user.name === User.teamName ? 'user-team' : ''}>
                            <td>{index + 1}</td>
                            <td>{user.name === User.teamName ? <strong>{user.name}</strong> : user.name}</td>
                            <td>{user.studiengang}</td>
                            <td>
                                {activeTab === 'total'
                                    ? user.punkteGesamt
                                    : activeTab === 'monthly'
                                        ? user.punkteMonat
                                        : user.punkteWoche
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
                    <p>{`Dein Punktzahl: ${
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

export default RankingTeam;
