import React from 'react';
import './styles/TeamMembers.css';
const TeamMembers = () => {
    const members = ['Member 1', 'Member 2', 'Member 3']; // Replace with actual member data

    return (
        <div className="team-members">
            <h2>Team Members:</h2>
            <ul>
                {members.map((member, index) => (
                    <li key={index}>{member}</li>
                ))}
            </ul>
            {/* Add other member details here */}
        </div>
    );
};

export default TeamMembers;
