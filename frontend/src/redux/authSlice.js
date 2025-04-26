import { createSlice } from "@reduxjs/toolkit";

const authSlice = createSlice({
    name: 'auth',
    initialState: {
        token : null,
        nome : null,
        logado : false,
        administrador  : false,
    },
    reducers: {
        setToken: (state, action) => {
            state.token = action.payload.token;
            state.nome = action.payload.nome;
            state.logado = action.payload.logado;
            state.administrador = action.payload.administrador;
        },
        logout: (state) => {
            state.token = null;
            state.nome = null;
            state.logado = false;  
            state.administrador = false;    
        }
    }
});

export const { setToken, logout } = authSlice.actions;
export default authSlice.reducer;