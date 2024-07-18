import React, { createContext, useState, useEffect } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [isAdmin, setIsAdmin] = useState(false);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [userData, setUserData] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('token');
        const role = localStorage.getItem('role');
        const storedUserData = localStorage.getItem('userData');
        if (token && role === 'ADMIN') {
            setIsAdmin(true);
            setIsLoggedIn(true);
        } else if (token) {
            setIsLoggedIn(true);
        }
        if (storedUserData) {
            setUserData(JSON.parse(storedUserData));
        }
    }, []);

    const handleLogin = (token, role, user) => {
        localStorage.setItem('token', token);
        localStorage.setItem('role', role);
        localStorage.setItem('userData', JSON.stringify(user));
        if (role === 'ADMIN') {
            setIsAdmin(true);
        }
        setIsLoggedIn(true);
        setUserData(user);
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        localStorage.removeItem('carrito');
        localStorage.removeItem('userData');
        setIsAdmin(false);
        setIsLoggedIn(false);
        setUserData(null);
    };

    return (
        <AuthContext.Provider value={{ isAdmin, isLoggedIn, userData, handleLogin, handleLogout }}>
            {children}
        </AuthContext.Provider>
    );
};