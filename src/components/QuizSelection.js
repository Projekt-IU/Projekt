import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './styles/QuizSelection.css';
import NavigationBar from "./NavigationBar";

const QuizSelection = () => {
    const navigate = useNavigate();
    const [selectedModule, setSelectedModule] = useState('');

    const handleQuizStart = (moduleName) => {
        setSelectedModule(moduleName);
        navigate(`/quiz/${moduleName}`);
    };

    return (
        <div>
            <NavigationBar/>
        <div className="quiz-selection-container">

            <h1 className="quiz-selection-title">Wähle ein Quiz-Modul</h1>
            <button className="quiz-selection-button" onClick={() => handleQuizStart('Mathematik')}>Mathematik</button>
            <button className="quiz-selection-button" onClick={() => handleQuizStart('Statistik')}>Statistik</button>
            <button className="quiz-selection-button" onClick={() => handleQuizStart('Datenbanken')}>Datenbanken</button>
        </div>
        </div>
    );
};

export default QuizSelection;
