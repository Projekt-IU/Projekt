import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import './styles/Quiz.css';

class Quiz extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentQuestionIndex: 0,
            userAnswers: [],
            showResult: false,
            selectedCategory: null,
            selectedStudyProgram: null,
            currentQuestion: null,
        };
    }

    handleStudyProgramSelect = (studyProgram) => {
        this.setState({
            selectedStudyProgram: studyProgram,
            selectedCategory: null,
        });
    };

    handleCategorySelect = (category) => {
        this.setState({ selectedCategory: category }, () => {
            this.fetchQuestionFromBackend(); // Fetch-Funktion aufrufen, nachdem die Kategorie ausgewählt wurde
        });
    };

    handleAnswerSubmit = (selectedAnswer) => {
        const { currentQuestionIndex, userAnswers } = this.state;
        userAnswers[currentQuestionIndex] = selectedAnswer;
        this.setState({
            userAnswers,
            currentQuestionIndex: currentQuestionIndex + 1,
        });
    };

    showResult = () => {
        this.setState({ showResult: true });
    };

    async fetchQuestionFromBackend() {
        const { selectedCategory } = this.state;

        try {
            const response = await fetch(`/api/quiz/frageHolen`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    username: 'Benutzername', // Benutzername ersetzen
                    password: 'Passwort', // Passwort ersetzen
                    modul: selectedCategory,
                    studiengang: 'Informatik', // Studiengang ersetzen
                }),
            });

            if (response.ok) {
                const questionData = await response.json();
                this.setState({ currentQuestion: questionData });
            } else if (response.status === 401) {
                // Benutzer nicht autorisiert, hier kannst du entsprechende Aktionen durchführen
            } else if (response.status === 400) {
                // Ungültige Anfrage, hier kannst du entsprechende Aktionen durchführen
            }
        } catch (error) {
            // Fehler beim Abrufen der Frage, hier kannst du Fehlerbehandlung hinzufügen
            console.error('Fehler beim Abrufen der Frage:', error);
        }
    }

    renderStudyProgramSelection() {
        const studyPrograms = ['Informatik', 'Wirtschaftsinformatik', 'Medieninformatik'];
        return (
            <div>
                <h2>Wähle deinen Studiengang:</h2>
                <ul>
                    {studyPrograms.map((program, index) => (
                        <li key={index}>
                            <button onClick={() => this.handleStudyProgramSelect(program)}>
                                {program}
                            </button>
                        </li>
                    ))}
                </ul>
            </div>
        );
    }

    renderCategorySelection() {
        const { selectedStudyProgram } = this.state;
        const categories = {
            Informatik: ['Mathematik', 'Programmierung', 'Datenbanken'],
            Wirtschaftsinformatik: ['Wirtschaft', 'Informatik', 'Management'],
            Medieninformatik: ['Design', 'Medientechnik', 'Kommunikation'],
        };

        const availableCategories = categories[selectedStudyProgram];

        return (
            <div>
                <h2>Wähle eine Kategorie:</h2>
                <ul>
                    {availableCategories.map((category, index) => (
                        <li key={index}>
                            <button onClick={() => this.handleCategorySelect(category)}>
                                {category}
                            </button>
                        </li>
                    ))}
                </ul>
            </div>
        );
    }

    renderQuiz() {
        const { currentQuestion, showResult, currentQuestionIndex } = this.state;

        if (showResult) {
            return (
                <div>
                    <h2>Quiz Ergebnisse</h2>
                    <p>
                        Richtig beantwortete Fragen: {this.state.userAnswers.filter(
                        (answer, index) => answer === this.state.questions[this.state.selectedCategory][index].correctAnswer
                    ).length}
                    </p>
                    <p>
                        Falsch beantwortete Fragen: {this.state.questions[this.state.selectedCategory].length - this.state.userAnswers.length}
                    </p>
                </div>
            );
        } else if (currentQuestion) {
            return (
                <div>
                    <h2>{currentQuestion.frage}</h2>
                    <ul>
                        {['antwortEins', 'antwortZwei', 'antwortDrei', 'antwortVier'].map((answer, index) => (
                            <li key={index}>
                                <input
                                    type="radio"
                                    id={`option-${index}`}
                                    value={currentQuestion[answer]}
                                    checked={this.state.userAnswers[currentQuestionIndex] === currentQuestion[answer]}
                                    onChange={() => this.handleAnswerSubmit(currentQuestion[answer])}
                                />
                                <label htmlFor={`option-${index}`}>{currentQuestion[answer]}</label>
                            </li>
                        ))}
                    </ul>
                    <button onClick={this.showResult}>
                        {currentQuestionIndex === this.state.questions[this.state.selectedCategory].length - 1
                            ? 'Ergebnisse anzeigen'
                            : 'Weiter'}
                    </button>
                </div>
            );
        } else {
            return <div>Quiz abgeschlossen.</div>;
        }
    }

    render() {
        return (
            <div>
                {this.renderQuiz()}
            </div>
        );
    }
}

ReactDOM.render(<Quiz />, document.getElementById('root'));

export default Quiz;