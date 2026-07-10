import axios from "axios";

const api = axios.create({

   baseURL: "http://13.127.168.164:8080"

});

api.interceptors.request.use(

    (config) => {

        const token = localStorage.getItem("accessToken");

        if (token) {

            config.headers.Authorization = `Bearer ${token}`;

        }

        return config;

    },

    (error) => Promise.reject(error)

);

api.interceptors.response.use(

    (response) => response,

    (error) => {

        if (error.response?.status === 401) {

            localStorage.clear();

            window.location.href = "/login";

        }

        return Promise.reject(error);

    }

);

export default api;
