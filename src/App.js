
import './App.css';
import React, {useEffect} from 'react';
import {BrowserRouter as Router,} from 'react-router-dom'; // Importieren Sie das Routing-Modul
import AppRoutes from "./routes/Router";
import User from './components/User';
import { UserProvider } from './components/UserContext';




function App() {

    useEffect(() => {
        // Laden der Benutzerdaten beim Start der Anwendung
        User.loadFromSession();
    }, []);


    return (

        <UserProvider>
        <Router>
            <div className="App">
                <AppRoutes />
            </div>
        </Router>
        </UserProvider>
    );





}


export default App;
