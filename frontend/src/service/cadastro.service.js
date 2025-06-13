import api from "./api";

const cadastrar = async (cadastroRequest) => {
    const response = await api.post("/usuarios", cadastroRequest);
    return response.data;
};

const consultarPorID = async (id) => {
    const response = await api.get("/usuarios/" + id); // Corrigido
    return response.data;
};

const consultar = async () => {
    const response = await api.get("/usuarios"); // Corrigido
    return response.data;
};

export const cadastroService = { cadastrar, consultar, consultarPorID };
