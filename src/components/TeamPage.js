import React, { useState, useEffect } from 'react';
import axios from 'axios';
import User from './User';
import './styles/TeamPage.css';
import ChatComponent from './ChatComponent';

const TeamPage = () => {
    const [teamInfo, setTeamInfo] = useState({
        teamsId: 0,
        name: '',
        studiengang: '',
        adminUsername: '',
        admin_team: false,
        scoreTeam: {
            punkteGesamt: 0,
            punkteMonat: 0,
            punkteWoche: 0,
            frageRichtig: 0,
            fragenGesamt: 0,
        },
        members: [],
    });
    const [isLoading, setIsLoading] = useState(true);
    const [selectedMember, setSelectedMember] = useState('');
    const [newMemberUsername, setNewMemberUsername] = useState('');
    const [adminSelectedMember, setAdminSelectedMember] = useState('');
    const [teamName, setTeamName] = useState(User.teamName); // Zustand für den Teamnamen

    // Function to fetch team information
    const fetchTeamInfo = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/Team/getTeam', {
                username: User.username,
                password: User.password,
                anfrageName: teamName
            });
            console.log(response.data);
            setTeamInfo(response.data);
            console.log(teamName);
            setIsLoading(false);
        } catch (error) {
            console.error('Error fetching team information:', error);
        }
    };

    useEffect(() => {
        fetchTeamInfo();
        // Aktualisiere den Teamnamen, wenn sich User.teamName ändert
        setTeamName(User.teamName);
    }, [teamName]); // Überwache teamName auf Änderungen

    const handleTeamDissolve = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/Team/dropTeam', {
                username: User.username,
                password: User.password
            });

            if (response.status === 200) {
                alert('Das Team wurde erfolgreich gelöscht.');
                window.location.href = '/Profile';
            } else {
                alert('Team konnte nicht gelöscht werden.');
            }
        } catch (error) {
            console.error('Error dissolving the team:', error);
        }
    };

    const addTeamMember = async () => {
        const username = prompt('Geben Sie den Benutzernamen des Mitglieds ein:');

        if (username) {
            setNewMemberUsername(username);

            try {
                const response = await axios.post('http://localhost:8080/api/Team/addUser', {
                    username: User.username,
                    password: User.password,
                    teamName: User.teamName,
                    newMember: username,
                    anfrageName: User.teamName
                });

                if (response.status === 200) {
                    alert('Mitglied wurde erfolgreich in das Team aufgenommen.');
                    fetchTeamInfo();
                } else {
                    alert('Mitglied konnte nicht hinzugefügt werden.');
                }
            } catch (error) {
                alert('Mitglied konnte nicht hinzugefügt werden.');
                console.error('Error adding team member:', error);
            }
        }
    };

    const removeTeamMember = async () => {
        try {
            if (selectedMember) {
                const response = await axios.post('http://localhost:8080/api/Team/dropUser', {
                    username: User.username,
                    password: User.password,
                    teamName: User.teamName,
                    userToDrop: selectedMember
                });

                if (response.status === 200) {
                    alert('Mitglied wurde erfolgreich aus dem Team entfernt.');
                    setSelectedMember('');
                    fetchTeamInfo();
                } else {
                    alert('Ein Fehler ist aufgetreten.');
                }
            } else {
                alert('Bitte wählen Sie ein Mitglied aus, um es zu entfernen.');
            }
        } catch (error) {
            console.error('Error removing team member:', error);
        }
    };

    const setAdminRole = async () => {
        if (adminSelectedMember) {
            try {
                const response = await axios.post('http://localhost:8080/api/Team/newAdmin', {
                    username: User.username,
                    password: User.password,
                    anfrageName: adminSelectedMember
                });

                if (response.status === 200) {
                    alert('Mitglied hat nun Adminrechte.');
                    fetchTeamInfo();
                } else {
                    alert('Mitglied kann kein Admin werden.');
                }
            } catch (error) {
                console.error('Error setting admin role:', error);
            }
        }
    };

    const isCurrentUserAdmin = teamInfo.members.some((member) => member.userName === User.username && member.admin_team == true);
    return (
        <div className="team-page-container">
            <div className="team-info-box">
                <h1>{teamInfo.name}</h1>
                <p>Punktestand gesamt: {teamInfo.scoreTeam.punkteGesamt}</p>
                <p>Punktestand Monat: {teamInfo.scoreTeam.punkteMonat}</p>
                <p>Punktestand Woche: {teamInfo.scoreTeam.punkteWoche}</p>
                <a href="/RankingTeam">Zeige das TeamRanking</a>
            </div>
            <div className="team-members-list">
                <h2>Mitglieder:</h2>
                {isLoading ? (
                    <p>Loading team members...</p>
                ) : (
                    <table className="team-members-table">
                        <thead>
                        <tr className="team-members-th">
                            <th>ID</th>
                            <th>Name</th>
                            <th>Team-Rolle</th>
                            <th>Studiengang</th>
                        </tr>
                        </thead>
                        <tbody>
                        {teamInfo.members.map((member, index) => (
                            <tr key={index} className="team-members-td">
                                <td>{index + 1}</td>
                                <td>{member.userName === User.username ? <strong>{member.userName}</strong> : member.userName}</td>
                                <td>{member.admin_team ? 'Admin' : 'Mitglied'}</td>
                                <td>{member.courseOfStudy}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
            <div className="admin-controls" style={{ display: isCurrentUserAdmin ? 'block' : 'none' }}>
                <div className="admin-controls-header"><b>Team-Verwaltung</b></div>
                <button onClick={handleTeamDissolve}>Team auflösen</button>
                <button onClick={addTeamMember}>Mitglied hinzufügen</button>
                <div>
                    <select
                        id="removeDropdown"
                        value={selectedMember}
                        onChange={(e) => setSelectedMember(e.target.value)}
                    >
                        <option value="">Mitglied auswählen</option>
                        {teamInfo.members.map((member, index) => (
                            <option key={index} value={member.userName}>
                                {member.userName}
                            </option>
                        ))}
                    </select>
                    <button onClick={removeTeamMember}>Mitglied entfernen</button>
                </div>
                <div>
                    <select
                        id="adminDropdown"
                        value={adminSelectedMember}
                        onChange={(e) => setAdminSelectedMember(e.target.value)}
                    >
                        <option value="">Admin auswählen</option>
                        {teamInfo.members.map((member, index) => (
                            <option key={index} value={member.userName}>
                                {member.userName}
                            </option>
                        ))}
                    </select>
                    <button onClick={setAdminRole}>Admin Rolle vergeben</button>
                </div>
            </div>
            <div className="chat-component">
                <div className="chat-header"><b>TeamChat</b></div>
                <ChatComponent teamName={teamInfo.name} />
            </div>
        </div>
    );
};

export default TeamPage;
