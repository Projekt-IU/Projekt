import React from 'react';
import './styles/TeamDetails.css';
const TeamDetails = () => {
    const teamName = 'Team A'; // Replace with actual team data

    return (
        <div className="team-details">
            <h2>Team Name: {teamName}</h2>
            {/* Add other team details here */}
        </div>
    );
};

export default TeamDetails;
