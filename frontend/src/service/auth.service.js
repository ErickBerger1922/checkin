import api from "./api";

const login = async (loginRequest) => {

    const rest = await api.post("/auth", loginRequest);
    return rest.data;
};

export const authService = {login};