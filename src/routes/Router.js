import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Home from '../components/Home';
import Login from '../components/Login';
import UserComponents from '../components/UserComponents'
import Profile from "../components/Profile";
import Ranking from "../components/Ranking";


const AppRoutes = () => {
    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/UserComponents" element={<UserComponents />}/>
            <Route path="/Profile" element={<Profile />}/>
            <Route path="/Ranking" element={<Ranking />}/>
            {/* Hier können Sie Routen für andere Seiten hinzufügen */}
        </Routes>
    );
};

export default AppRoutes;