import React, {Component} from 'react';
import './styles/FragenErstellen.css';
import axios from "axios";
import { Navigate } from "react-router-dom";
import User from "./User";
import NavigationBar from "./NavigationBar";

class FragenErstellen extends Component {
    constructor() {
        super();
        // Authentifizierungsinformationen aus der Anwendung holen
        this.authRequest = {
            username: User.username,
            password: User.password,
        };
        this.state = {
            frage: '',
            antwortEins: '',
            antwortZwei: '',
            antwortDrei: '',
            antwortVier: '',
            richtigeAntwort: '',
            frageErstellt: false,
            modul: '',
            error: '',
        };
    }
    handleCheckboxChange = (e) => {
        this.setState({ richtigeAntwort: e.target.value });
    };

    handleChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    };

    handleSubmit = (e) => {
        e.preventDefault();
        const {frage, antwortEins, antwortZwei, antwortDrei, antwortVier, richtigeAntwort, modul  } = this.state;

        axios.post('http://localhost:8080/api/quiz/frageErstellen', { ...this.authRequest, frage, antwortEins, antwortZwei, antwortDrei, antwortVier, richtigeAntwort, modul})
            .then(response => {
                if (response.status === 200) {
                    this.setState({ error: "Frage erstellt" , frageErstellt : true});
                } else {
                    this.setState({ error: "fehlgeschlagen!" });
                }
            })
            .catch(error => {
                console.error("erstellen fehlgeschlagen", error);
                this.setState({ error: " fehlgeschlagen" });
            });
    };

    render() {
        if (this.state.frageErstellt) {
            return <Navigate to="/FragenErstellen" />
        }

        return (
            <div> <NavigationBar/>
            <div className="erstellen-centered-container">

                <div className="erstellen-container">
                <div className="fragen-box">
                    <h2>Frage erstellen</h2>
                    <form onSubmit={this.handleSubmit}>
                        <div>
                            <label htmlFor="Frage">Frage</label>
                            <input
                                type="text"
                                id="Frage"
                                name="frage"
                                className="fragen-input"
                                value={this.state.frage}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="Antwort1">Antwort1</label>
                            <input
                                type="text"
                                id="Antwort1"
                                name="antwortEins"
                                className="fragen-input"
                                value={this.state.antwortEins}
                                onChange={this.handleChange}
                            />
                            <input
                                type="checkbox"
                                value="1"
                                checked={this.state.richtigeAntwort === "1"}
                                onChange={this.handleCheckboxChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="Antwort2">Antwort2</label>
                            <input
                                type="text"
                                id="Antwort2"
                                name="antwortZwei"
                                className="fragen-input"
                                value={this.state.antwortZwei}
                                onChange={this.handleChange}
                            />
                            <input
                                type="checkbox"
                                value="2"
                                checked={this.state.richtigeAntwort === "2"}
                                onChange={this.handleCheckboxChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="Antwort3">Antwort3</label>
                            <input
                                type="text"
                                id="Antwort3"
                                name="antwortDrei"
                                className="fragen-input"
                                value={this.state.antwortDrei}
                                onChange={this.handleChange}
                            />
                            <input
                                type="checkbox"
                                value="3"
                                checked={this.state.richtigeAntwort === "3"}
                                onChange={this.handleCheckboxChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="Antwort4">Antwort4</label>
                            <input
                                type="text"
                                id="Antwort4"
                                name="antwortVier"
                                className="fragen-input"
                                value={this.state.antwortVier}
                                onChange={this.handleChange}
                            />
                            <input
                                type="checkbox"
                                value="4"
                                checked={this.state.richtigeAntwort === "4"}
                                onChange={this.handleCheckboxChange}
                            />
                        </div>
                        <div >
                            <label htmlFor="modul">Modul</label>
                             <select
                                id="modul"
                                name="modul"
                                className="fragen-input"
                                value={this.state.modul}
                                onChange={this.handleChange}
                                >
                            <option value="">Bitte auswählen:</option>
                            <option value="Datenbanken">Datenbanken</option>
                            <option value="Mathematik">Mathematik</option>
                            <option value="Statistik">Statistik</option>
                        </select>

                        </div>

                        <button type="submit">Frage erstellen</button>  {/* Hinzufügen einer Schaltfläche zum Absenden des Formulars */}
                    </form>
                    {this.state.error && <p className="error-message">{this.state.error}</p>}
                </div>
            </div>
            </div>
            </div>
        );
    }
}

export default FragenErstellen;
