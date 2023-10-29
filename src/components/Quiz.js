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
        };
    }

    handleStudyProgramSelect = (studyProgram) => {
        this.setState({
            selectedStudyProgram: studyProgram,
            selectedCategory: null,
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
                {
                    questionText: 'Frage 2: Was ist 5 * 7?',
                    answerOptions: ['12', '35', '45', '23'],
                    correctAnswer: '35',
                },
                {
                    questionText: 'Frage 3: Wie lautet die Wurzel von 16?',
                    answerOptions: ['2', '4', '8', '16'],
                    correctAnswer: '4',
                },
                {
                    questionText: 'Frage 4: Was ist 9 geteilt durch 3?',
                    answerOptions: ['2', '3', '4', '6'],
                    correctAnswer: '3',
                },
                {
                    questionText: 'Frage 5: Welche Zahl folgt auf 7, 14, 21, ...?',
                    answerOptions: ['28', '30', '35', '42'],
                    correctAnswer: '28',
                },
                {
                    questionText: 'Frage 6: Wie viele Grad hat ein rechter Winkel?',
                    answerOptions: ['45', '90', '180', '360'],
                    correctAnswer: '90',
                },
                {
                    questionText: 'Frage 7: Was ist die Summe der ersten 10 natürlichen Zahlen?',
                    answerOptions: ['20', '45', '55', '100'],
                    correctAnswer: '55',
                },
                {
                    questionText: 'Frage 8: Welche Form hat die Erde?',
                    answerOptions: ['Kugel', 'Würfel', 'Pyramide', 'Scheibe'],
                    correctAnswer: 'Kugel',
                },
                {
                    questionText: 'Frage 9: Wie viele Planeten hat unser Sonnensystem?',
                    answerOptions: ['6', '8', '9', '10'],
                    correctAnswer: '8',
                },
                {
                    questionText: 'Frage 10: Was ist die Hauptstadt von Frankreich?',
                    answerOptions: ['London', 'Berlin', 'Madrid', 'Paris'],
                    correctAnswer: 'Paris',
                },
            ],
            Programmierung: [
                {
                    questionText: 'Frage 1: Was ist ein Array?',
                    answerOptions: ['Eine Programmiersprache', 'Eine Datenstruktur', 'Ein Algorithmus'],
                    correctAnswer: 'Eine Datenstruktur',
                },
                {
                    questionText: 'Frage 2: Was ist JavaScript?',
                    answerOptions: ['Eine Kaffeemaschine', 'Eine Programmiersprache', 'Ein Film'],
                    correctAnswer: 'Eine Programmiersprache',
                },
                {
                    questionText: 'Frage 3: Welches Schlüsselwort definiert eine Variable in JavaScript?',
                    answerOptions: ['var', 'fun', 'if', 'const'],
                    correctAnswer: 'var',
                },
                {
                    questionText: 'Frage 4: Was ist HTML?',
                    answerOptions: ['Eine Programmiersprache', 'Eine Textverarbeitung', 'Eine Auszeichnungssprache'],
                    correctAnswer: 'Eine Auszeichnungssprache',
                },
                {
                    questionText: 'Frage 5: Welche der folgenden ist keine Programmiersprache?',
                    answerOptions: ['Python', 'Pizza', 'Java', 'C++'],
                    correctAnswer: 'Pizza',
                },
                {
                    questionText: 'Frage 6: Was ist ein Compiler?',
                    answerOptions: ['Ein Schraubenzieher', 'Ein Programm zur Fehlerbehebung', 'Ein Übersetzungsprogramm'],
                    correctAnswer: 'Ein Übersetzungsprogramm',
                },
                {
                    questionText: 'Frage 7: Was ist ein Loop in der Programmierung?',
                    answerOptions: ['Ein musikalisches Instrument', 'Eine Schleife im Code', 'Eine Fehlermeldung'],
                    correctAnswer: 'Eine Schleife im Code',
                },
                {
                    questionText: 'Frage 8: Welche Sprache wird in der Webentwicklung für die Gestaltung von Webseiten verwendet?',
                    answerOptions: ['Java', 'C', 'HTML', 'SQL'],
                    correctAnswer: 'HTML',
                },
                {
                    questionText: 'Frage 9: Welche Art von Fehler tritt auf, wenn der Code nicht kompiliert?',
                    answerOptions: ['Syntaxfehler', 'Logikfehler', 'Kompilierungsfehler', 'Laufzeitfehler'],
                    correctAnswer: 'Kompilierungsfehler',
                },
                {
                    questionText: 'Frage 10: Welche Programmiersprache wurde für die Entwicklung von Android-Apps verwendet?',
                    answerOptions: ['iOS', 'Java', 'Python', 'C#'],
                    correctAnswer: 'Java',
                },
            ],
            Datenbanken: [
                {
                    questionText: 'Frage 1: Was ist eine Datenbank?',
                    answerOptions: ['Eine Sammlung von Daten', 'Ein Textdokument', 'Eine Website'],
                    correctAnswer: 'Eine Sammlung von Daten',
                },
                {
                    questionText: 'Frage 2: Was ist SQL?',
                    answerOptions: ['Ein Datenbankmodell', 'Eine Abfragesprache', 'Eine Programmiersprache'],
                    correctAnswer: 'Eine Abfragesprache',
                },
                {
                    questionText: 'Frage 3: Welche Art von Datenbank speichert Daten in Tabellen?',
                    answerOptions: ['Relationale Datenbank', 'NoSQL-Datenbank', 'Graphdatenbank'],
                    correctAnswer: 'Relationale Datenbank',
                },
                {
                    questionText: 'Frage 4: Was ist ein Primärschlüssel in einer Datenbank?',
                    answerOptions: ['Ein geheimes Passwort', 'Eine eindeutige Identifikation für Datensätze', 'Ein Backup'],
                    correctAnswer: 'Eine eindeutige Identifikation für Datensätze',
                },
                {
                    questionText: 'Frage 5: Was bedeutet die Abkürzung ACID in Bezug auf Datenbanken?',
                    answerOptions: ['Säure', 'Transaktionseigenschaften', 'Ein Datenbankmanagementsystem'],
                    correctAnswer: 'Transaktionseigenschaften',
                },
                {
                    questionText: 'Frage 6: Welche Art von Datenbank ist flexibler und skalierbarer?',
                    answerOptions: ['Relationale Datenbank', 'NoSQL-Datenbank', 'Hierarchische Datenbank'],
                    correctAnswer: 'NoSQL-Datenbank',
                },
                {
                    questionText: 'Frage 7: Welche SQL-Anweisung wird verwendet, um Daten aus einer Tabelle abzurufen?',
                    answerOptions: ['SELECT', 'DELETE', 'UPDATE', 'INSERT'],
                    correctAnswer: 'SELECT',
                },
                {
                    questionText: 'Frage 8: Was ist ein JOIN in SQL?',
                    answerOptions: ['Eine Partyeinladung', 'Eine Kombination von Tabellen', 'Ein Index'],
                    correctAnswer: 'Eine Kombination von Tabellen',
                },
                {
                    questionText: 'Frage 9: Welche Art von Datenbank ist besonders geeignet für Big Data?',
                    answerOptions: ['SQLite', 'MySQL', 'Hadoop', 'Cassandra'],
                    correctAnswer: 'Hadoop',
                },
                {
                    questionText: 'Frage 10: Welche Datenbank wird oft für Echtzeit-Analytics verwendet?',
                    answerOptions: ['MongoDB', 'MariaDB', 'InfluxDB', 'PostgreSQL'],
                    correctAnswer: 'InfluxDB',
                },
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


