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
            selectedStudyProgram: null, // Neuer State für den ausgewählten Studiengang
        };
    }

    handleStudyProgramSelect = (studyProgram) => {
        this.setState({
            selectedStudyProgram: studyProgram,
            selectedCategory: null, // Zurücksetzen der ausgewählten Kategorie
        });
    };

    handleCategorySelect = (category) => {
        this.setState({ selectedCategory: category });
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
        const { currentQuestionIndex, userAnswers, showResult, selectedCategory, selectedStudyProgram } = this.state;

        const questions = {
            Mathematik: [
                {
                    questionText: 'Frage 1: Was ist 2 + 2?',
                    answerOptions: ['3', '4', '5', '6'],
                    correctAnswer: '4',
                },
                // Weitere Fragen für Mathematik hinzufügen
            ],
            Programmierung: [
                {
                    questionText: 'Frage 1: Was ist ein Array?',
                    answerOptions: ['Eine Programmiersprache', 'Eine Datenstruktur', 'Ein Algorithmus'],
                    correctAnswer: 'Eine Datenstruktur',
                },
                // Weitere Fragen für Programmierung hinzufügen
            ],
            // Weitere Kategorien mit Fragen hier hinzufügen
        };

        if (!selectedStudyProgram) {
            return this.renderStudyProgramSelection();
        }

        if (!selectedCategory) {
            return this.renderCategorySelection();
        }

        if (showResult) {
            return (
                <div>
                    <h2>Quiz Ergebnisse</h2>
                    <p>
                        Richtig beantwortete Fragen: {userAnswers.filter(
                        (answer, index) => answer === questions[selectedCategory][index].correctAnswer
                    ).length}
                    </p>
                    <p>
                        Falsch beantwortete Fragen: {questions[selectedCategory].length - userAnswers.length}
                    </p>
                </div>
            );
        } else if (currentQuestionIndex < questions[selectedCategory].length) {
            const question = questions[selectedCategory][currentQuestionIndex];

            return (
                <div>
                    <h2>{question.questionText}</h2>
                    <ul>
                        {question.answerOptions.map((option, index) => (
                            <li key={index}>
                                <input
                                    type="radio"
                                    id={`option-${index}`}
                                    value={option}
                                    checked={userAnswers[currentQuestionIndex] === option}
                                    onChange={() => this.handleAnswerSubmit(option)}
                                />
                                <label htmlFor={`option-${index}`}>{option}</label>
                            </li>
                        ))}
                    </ul>
                    <button onClick={this.showResult}>
                        {currentQuestionIndex === questions[selectedCategory].length - 1
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

