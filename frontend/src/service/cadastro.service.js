import api from "./api";

const cadastrar = async (cadastroRequest) => {
    const rest = await api.post("usuario", cadastroRequest);
    return rest.data;
};

const consultarPorID = async (id) => {
    const rest = await api.get("usuario/" + id);
    return rest.data;
};

const consultar = async () => {
    const rest = await api.get("usuario");
    return rest.data;
};

export const cadastroService = {cadastrar, consultar, consultarPorID};