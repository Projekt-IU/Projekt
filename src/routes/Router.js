import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Home from '../components/Home';
import Login from '../components/Login';
import UserComponents from '../components/UserComponents';
import Profile from "../components/Profile";
import RankingUser from "../components/RankingUser";
import RankingTeam from "../components/RankingTeam";
import TeamPage from "../components/TeamPage";
import ChangePassword from "../components/ChangePassword";
import ChangeUsername from "../components/ChangeUsername";
import Registrierung from "../components/Registrierung";
import ChatEinbindungExample from "../components/ChatEinbindungExample";
import Quiz from "../components/Quiz";
import QuizSelection from "../components/QuizSelection";
import FragenErstellen from "../components/FragenErstellen";

const AppRoutes = () => {
    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/UserComponents" element={<UserComponents />} />
            <Route path="/Profile" element={<Profile />} />
            <Route path="/RankingUser" element={<RankingUser />} />
            <Route path="/RankingTeam" element={<RankingTeam />} />
            <Route path="/TeamPage" element={<TeamPage />} />
            <Route path="/ChangePassword" element={<ChangePassword />} />
            <Route path="/ChangeUsername" element={<ChangeUsername />} />
            <Route path="/Registrierung" element={<Registrierung />} />
            <Route path="/ChatEinbindungExample" element={<ChatEinbindungExample />} />
            <Route path="/QuizSelection" element={<QuizSelection />} /> {/* Neue Route für QuizSelection hinzufügen */}
            <Route path="/Quiz/:selectedModule" element={<Quiz />} /> {/* Dynamische Route für Quiz mit selectedModule */}
                <Route path="/FragenErstellen" element={< FragenErstellen />}/>
            {/* Hier können Sie Routen für andere Seiten hinzufügen */}
        </Routes>
    );
};

export default AppRoutes;
