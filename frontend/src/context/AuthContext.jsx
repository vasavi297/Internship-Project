import { createContext, useContext, useEffect, useState } from "react";

import AuthService from "../services/AuthService";

const AuthContext = createContext();

export function AuthProvider({ children }) {

    const [authenticated, setAuthenticated] = useState(false);

    useEffect(() => {

        if (AuthService.isLoggedIn()) {

            setAuthenticated(true);

        }

    }, []);

    const login = (authResponse) => {

        AuthService.saveTokens(authResponse);

        setAuthenticated(true);

    };

    const logout = () => {

        AuthService.logout();

        setAuthenticated(false);

    };

    return (

        <AuthContext.Provider
            value={{
                authenticated,
                login,
                logout
            }}
        >

            {children}

        </AuthContext.Provider>

    );

}

export function useAuth() {

    return useContext(AuthContext);

}