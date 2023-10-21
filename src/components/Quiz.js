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
        };
    }

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

    render() {
        const { currentQuestionIndex, userAnswers, showResult } = this.state;
        const questions = [
            {
                questionText: 'Frage 1: Was ist 2 + 2?',
                answerOptions: ['3', '4', '5', '6'],
                correctAnswer: '4',
            },
            {
                questionText: 'Frage 2: Welche Farbe hat der Himmel?',
                answerOptions: ['Rot', 'Grün', 'Blau', 'Gelb'],
                correctAnswer: 'Blau',
            },
            // Weitere Fragen hier hinzufügen
        ];

        if (showResult) {
            return (
                <div>
                    <h2>Quiz Ergebnisse</h2>
                    <p>
                        Richtig beantwortete Fragen: {userAnswers.filter(
                        (answer, index) => answer === questions[index].correctAnswer
                    ).length}
                    </p>
                    <p>
                        Falsch beantwortete Fragen: {questions.length - userAnswers.length}
                    </p>
                </div>
            );
        } else if (currentQuestionIndex < questions.length) {
            const question = questions[currentQuestionIndex];

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
                                    checked={
                                        this.state.userAnswers[currentQuestionIndex] === option
                                    }
                                    onChange={() => this.handleAnswerSubmit(option)}
                                />
                                <label htmlFor={`option-${index}`}>{option}</label>
                            </li>
                        ))}
                    </ul>
                    <button onClick={this.showResult}>
                        {currentQuestionIndex === questions.length - 1
                            ? 'Ergebnisse anzeigen'
                            : 'Weiter'}
                    </button>
                </div>
            );
        } else {
            return <div>Quiz abgeschlossen.</div>;
        }
    }
}

ReactDOM.render(<Quiz />, document.getElementById('root'));

export default Quiz;