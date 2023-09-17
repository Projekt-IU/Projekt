import React, { createContext, useState, useContext } from 'react';

const UserContext = createContext();

export const useUser = () => {
    return useContext(UserContext);
};

export const UserProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    const login = (userData) => {
        // Hier können Sie die Anmelde-Logik implementieren und den Benutzer setzen
        setUser(userData);
    };

    const logout = () => {
        // Hier können Sie die Abmelde-Logik implementieren und den Benutzer löschen
        setUser(null);
    };

    return (
        <UserContext.Provider value={{ user, login, logout }}>
            {children}
        </UserContext.Provider>
    );
};



