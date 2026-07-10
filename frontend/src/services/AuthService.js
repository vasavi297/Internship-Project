import axios from "axios";
import { jwtDecode } from "jwt-decode";


const API_URL = "http://13.127.168.164:8080/auth";

class AuthService {

    register(registerRequest) {

        return axios.post(
            `${API_URL}/register`,
            registerRequest
        );

    }

    confirm(confirmRequest) {

        return axios.post(
            `${API_URL}/confirm`,
            confirmRequest
        );

    }

    login(loginRequest) {

        return axios.post(
            `${API_URL}/login`,
            loginRequest
        );

    }
    forgotPassword(request) {

    return axios.post(

        `${API_URL}/forgot-password`,

        request

    );

}

resetPassword(request) {

    return axios.post(

        `${API_URL}/reset-password`,

        request

    );

}

    logout() {

        localStorage.removeItem("accessToken");
        localStorage.removeItem("idToken");
        localStorage.removeItem("refreshToken");

    }

    saveTokens(authResponse) {

        localStorage.setItem(
            "accessToken",
            authResponse.accessToken
        );

        localStorage.setItem(
            "idToken",
            authResponse.idToken
        );

        localStorage.setItem(
            "refreshToken",
            authResponse.refreshToken
        );

    }

    getAccessToken() {

        return localStorage.getItem("accessToken");

    }

    isLoggedIn() {

        return this.getAccessToken() != null;

    }
    getUser() {

    const idToken = localStorage.getItem("idToken");

    if (!idToken) {

        return null;

    }

    try {

        return jwtDecode(idToken);

    } catch (error) {

        console.error(error);

        return null;

    }

}

}

const authService = new AuthService();

export default authService;
