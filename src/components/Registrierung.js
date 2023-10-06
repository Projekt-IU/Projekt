import React, {Component,} from 'react';
import './styles/Login.css';
import axios from "axios";
import {Navigate} from "react-router-dom";
import User from "./User";
import user from "./User";

class Registrierung extends Component {
    constructor() {
        super();
        this.state = {
            firstName: '',
            lastName: '',
            username: '',
            courseOfStudy: '',
            email: '',
            matrikelNr: '',
            password: '',
            confirmPassword: '',
            registered: false,
            error: '',

        };
    }

    handleChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    };

    handleSubmit = (e) => {
        e.preventDefault();
        const { firstName, lastName, userName, courseOfStudy, email, matrikelNr, password, confirmPassword } = this.state;

        // Axios URL noch ändern!
        axios.post('http://localhost:8080/api/login',
            { firstName, lastName, userName, courseOfStudy, email, matrikelNr, password, confirmPassword })
            .then(response => {
                if (response.data) {
                    const { userName, firstName, lastName, courseOfStudy,
                    email, matrikelNr, password, confirmPassword} = response.data;
                    // Benutzer registrieren!
                    User.register(userName, firstName, lastName, courseOfStudy, email, matrikelNr,
                        password, confirmPassword);
                    console.log(response);
                    this.setState({ error: 'Registrierung erfolgreich!' });
                    this.setState({ registered: true, user: userName });

                console.log(this.state);
                console.log(User)
                } else {
                    this.setState({ error: 'Registrierung fehlgeschlagen!' })
                }
            })

            .catch(error => {
                console.error('Registrierung fehlgeschlagen', error);
                this.setState({ error: '' });
            });

        // console.log('Anmeldung mit:', userName, password);
    };

    render() {
        if (User.registered) {
            return <Navigate to= "/UserComponents" />
        }

        // Frontend
        return (
            <div className= "centered-container">
                <div className= "register-box">
                    <h2>Registrierung</h2>
                    <form onSubmit={this.handleSubmit}>
                        <div>
                            <label htmlFor="userName">Benutzername</label>
                            <input
                                type="text"
                                id="username"
                                name="username"
                                className="register-input"
                                value={this.state.username}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="firstName">Vorname</label>
                            <input
                                type="text"
                                id="firstName"
                                name="firstName"
                                className="register-input"
                                value={this.state.firstName}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="lastName">Nachname</label>
                            <input
                                type="text"
                                id="lastName"
                                name="lastName"
                                className="register-input"
                                value={this.state.lastName}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="email">Email</label>
                            <input
                                type="email"
                                id="email"
                                name="email"
                                className="register-input"
                                value={this.state.email}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="matrikelNr">Matrikelnummer</label>
                            <input
                                type="matrikelNr"
                                id="matrikelNr"
                                name="matrikelNr"
                                className="register-input"
                                value={this.state.matrikelNr}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="courseOfStudy">Studiengang</label>
                            <select>
                                id="courseOfStudy"
                                name="courseOfStudy"
                                value={this.state.courseOfStudy}
                                onChange={this.handleChange}

                                <option value="">Bitte auswählen:</option>
                                <option value="course1">Informatik</option>
                                <option value="course2">Wirtschaftsinformatik</option>
                                <option value="course3">Medieninformatik</option>
                            </select>
                        </div>
                        <div>
                            <label htmlFor="password">Passwort</label>
                            <input
                                type="password"
                                id="password"
                                name="password"
                                className="register-input"
                                value={this.state.password}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="confirmPassword">Passwort wiederholen</label>
                            <input
                                type="password"
                                id="confirmPassword"
                                name="confirmPassword"
                                className="register-input"
                                value={this.state.confirmPassword}
                                onChange={this.handleChange}
                            />
                        </div>
                    </form>
                </div>
            </div>
        )
    }
}

export default Registrierung;