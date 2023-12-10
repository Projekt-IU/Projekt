import React, { useState, useEffect } from 'react';
import axios from 'axios';
import User from './User';
import './styles/Quiz.css';
import { useLocation, useParams } from 'react-router-dom';
import NavigationBar from "./NavigationBar";


const Quiz = () => {
    const user = User;
    const { selectedModule } = useParams();
    const [questions, setQuestions] = useState([]);
    const [currentQuestion, setCurrentQuestion] = useState(0);
    const [selectedAnswer, setSelectedAnswer] = useState(null);
    const [feedback, setFeedback] = useState('');
    const [quizCompleted, setQuizCompleted] = useState(false);
    const [loadingQuestion, setLoadingQuestion] = useState(true);
    const [correctAnswers, setCorrectAnswers] = useState(0);
    const [nextQuestionButtonClicked, setNextQuestionButtonClicked] = useState(false);
    const [quizEnded, setQuizEnded] = useState(false);

    const fetchNextQuestion = async () => {
        try {
            if (user.username && user.password) {
                const response = await axios.post('http://16.170.229.65:8080/api/quiz/frageHolen', {
                    username: user.username,
                    password: user.password,
                    modul: selectedModule
                });

                if (response.status === 200) {
                    const question = response.data;
                    setQuestions([...questions, question]);
                    setLoadingQuestion(false);
                    setFeedback('');
                }
            }
        } catch (error) {
            console.error('Fehler beim Abrufen der Fragen', error);
        }
    };

    const startQuiz = () => {
        if (user.username && user.password) {
            fetchNextQuestion();
        }
    };

    useEffect(() => {
        startQuiz();
    }, []);

    const checkAnswer = async (answer) => {
        const currentQuestionObj = questions[currentQuestion];

        if (user.username && user.password && currentQuestionObj && answer !== null && selectedAnswer === null) {
            try {
                setSelectedAnswer(answer);
                const response = await axios.post('http://16.170.229.65:8080/api/quiz/frageBeantworten', {
                    username: user.username,
                    password: user.password,
                    fragenId: currentQuestionObj.fragenId,
                    antwort: answer,
                });

                if (response.status === 200) {
                    if (response.data === 'Richtig') {
                        setFeedback('Richtig!');
                        setCorrectAnswers(correctAnswers + 1);
                        currentQuestionObj.korrekteAntwort = true;
                    } else {
                        setFeedback('Falsch!');
                        currentQuestionObj.korrekteAntwort = false;
                    }
                } else {
                    console.error('Unbekannter Fehler', response);
                }
            } catch (error) {
                setFeedback('Falsch!');
                console.error('Fehler bei der Antwortüberprüfung', error);
            }
        }
    };

    const moveToNextQuestion = () => {
        if (user.username && user.password) {
            setNextQuestionButtonClicked(true);

            if (nextQuestionButtonClicked) {
                if (currentQuestion + 1 < questions.length) {
                    setCurrentQuestion(currentQuestion + 1);
                    setSelectedAnswer(null);
                    setFeedback('');
                } else if (currentQuestion + 1 === 10) {
                    setQuizCompleted(true);
                } else {
                    fetchNextQuestion();
                }
            }
        }
    };

    useEffect(() => {
        if (nextQuestionButtonClicked && questions.length > 0 && !loadingQuestion) {
            moveToNextQuestion();
        }
    }, [nextQuestionButtonClicked, questions, loadingQuestion]);

    if (quizCompleted) {
        return (
            <div>
                <NavigationBar/>
                <h1 className={"QuizFinished"}>Quiz beendet</h1>
                <p>{correctAnswers}/{questions.length} Fragen richtig beantwortet</p>
                {quizEnded ? (
                    <button onClick={() => window.location.href = '/'}>Quizmodus beenden</button>
                ) : (
                    <div className={"ButtonQuizContainer"}>
                        <button className={"ButtonQuizFinished"} onClick={() => window.location.href = '/QuizSelection'}>Neues Quiz starten</button>
                        <button className={"ButtonQuizFinished"} onClick={() => window.location.href = '/Profile'}>Quiz beenden</button>
                    </div>
                )}
            </div>
        );
    }

    const questionBoxClass = `question-box ${feedback === 'Falsch' ? 'incorrect' : ''}`;
    const answerButtonClass = `answer-button ${
        feedback === 'Richtig' ? 'correct' : ''
    } ${feedback === 'Falsch' ? 'incorrect' : ''} ${
        selectedAnswer !== null ? 'disabled' : ''
    }`;

    if (questions.length > 0 && questions[currentQuestion]) {
        const question = questions[currentQuestion];
        if (User.loggedIn) {
        return (
            <div>
                <NavigationBar />

                <div className="content-container">
                    <h2>Quizfrage {currentQuestion + 1}</h2>
                    <div className={questionBoxClass}>
                        <h1 className={'Frage'}>{question.frage}</h1>
                    </div>
                    <div className="answer-button-container1">
                        <button
                            className={answerButtonClass}
                            onClick={() => checkAnswer(1)}
                        >
                            {question.antwortEins}
                        </button>
                        <button
                            className={answerButtonClass}
                            onClick={() => checkAnswer(2)}
                        >
                            {question.antwortZwei}
                        </button>
                    </div>
                    <div className="answer-button-container2">
                        <button
                            className={answerButtonClass}
                            onClick={() => checkAnswer(3)}
                        >
                            {question.antwortDrei}
                        </button>
                        <button
                            className={answerButtonClass}
                            onClick={() => checkAnswer(4)}
                        >
                            {question.antwortVier}
                        </button>
                    </div>
                    {feedback && <p>{feedback}</p>}
                    <button className={'next-button'} onClick={moveToNextQuestion}>
                        {currentQuestion + 1 === 10 ? 'Quiz beenden' : 'Nächste Frage'}
                    </button>
                </div>
            </div>
        );



    } else {
        return <p>Frage wird geladen...</p>;
    }

    }  if (!User.loggedIn)  {
        // Benutzer ist nicht angemeldet, Anmeldeformular anzeigen
        return  window.location.href = '/login';
    }
        
};

export default Quiz;
