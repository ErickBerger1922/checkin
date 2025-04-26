import axios from "axios";
import { useStore } from "react-redux";

const api = axios.create({
    baseURL: "http://localhost:8080/"
});

api.interceptors.request.use(
    (config) => {
        const token = useStore.getState().auth.token; 
        if (token && !config.url.includes('/login')) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    });

export default api;