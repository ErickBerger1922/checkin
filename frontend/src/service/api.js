import axios from "axios";
import store from "../redux/store";

const api = axios.create({
    baseURL: "http://localhost:8080"
});

api.interceptors.request.use(
    (config) => {
        const token = store.getState().auth.token; 
        if (token && !config.url.includes('/oauth2/token')) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
	(error) => Promise.reject(error)
);

export default api;