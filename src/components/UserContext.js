import React, {createContext, useState, useContext, useEffect} from 'react';
import User from "./User";

const UserContext = createContext();

export function useUser() {
    return useContext(UserContext);
}

export function UserProvider({ children }) {
    const [currentUser, setCurrentUser] = useState(User);

    useEffect(() => {
        User.loadFromSession();
        setCurrentUser({ ...User });
    }, []);

    useEffect(() => {
        User.saveToSession();
    }, [currentUser]);

    return (
        <UserContext.Provider value={{ currentUser, setCurrentUser }}>
            {children}
        </UserContext.Provider>
    );
}

