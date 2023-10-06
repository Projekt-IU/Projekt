import React, { useState, useEffect } from 'react';
import './styles/Ranking.css'; // Import the RankingUser stylesheet
import axios from 'axios'; // Import Axios for making API requests
import User from "./User";

const RankingUser = () => {
    const [activeTab, setActiveTab] = useState('total'); // Active tab
    const [rankingData, setRankingData] = useState([]); // State to store ranking data

    // Function to fetch ranking data from the API
    const fetchRankingData = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/score/getScoreUser', {
                username: User.username,
                password: User.password,
            });
            setRankingData(response.data); // Set ranking data to the response data
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    // Use useEffect to fetch data when the component mounts
    useEffect(() => {
        fetchRankingData();
    }, []); // Empty dependency array to fetch data only once when the component mounts

    // Filter data based on the active tab
    let filteredData;
    if (activeTab === 'total') {
        filteredData = rankingData.totalRankingData;
    } else if (activeTab === 'monthly') {
        filteredData = rankingData.monthlyRankingData;
    } else if (activeTab === 'weekly') {
        filteredData = rankingData.weeklyRankingData;
    }

    return (
        <div className="ranking-container">
            {/* Tabs for selecting RankingUser table */}
            <div className="ranking-tabs">
                <button onClick={() => setActiveTab('total')}>Punkte gesamt</button>
                <button onClick={() => setActiveTab('monthly')}>Punkte im Monat</button>
                <button onClick={() => setActiveTab('weekly')}>Punkte in der Woche</button>
            </div>

            {/* Display the corresponding RankingUser table based on the active tab */}
            {filteredData && (
                <div className="ranking-table">
                    <h2>{`Punkte ${activeTab === 'total' ? 'gesamt' : activeTab === 'monthly' ? 'im Monat' : 'in der Woche'}`}</h2>
                    <table>
                        <thead>
                        <tr>
                            <th>Rangplatz</th>
                            <th>Student</th>
                            <th>Studiengang</th>
                            <th>Punktzahl</th>
                        </tr>
                        </thead>
                        <tbody>
                        {filteredData.map((user, index) => (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{user.name}</td>
                                <td>{user.courseOfStudy}</td>
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

export default RankingUser;
