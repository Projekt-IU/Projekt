
import './App.css';
import React from 'react';
import {BrowserRouter as Router,} from 'react-router-dom'; // Importieren Sie das Routing-Modul
import AppRoutes from "./routes";





function App() {
    return (
        <Router>
            <div className="App">
                {/* Hier globale Komponenten hinzufügen */}
                <AppRoutes />
            </div>
        </Router>
    )



}





export default App;
