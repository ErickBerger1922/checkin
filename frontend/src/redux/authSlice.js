import { createSlice } from "@reduxjs/toolkit";

const authSlice = createSlice({
  name: 'auth',
  initialState: {
    token: null,
    id: null,
    nome: null,
    logado: false,
    roles: [],
  },
  reducers: {
    setToken: (state, action) => {
      state.token = action.payload.token;
      state.id = action.payload.id || null;
      state.nome = action.payload.nome || null;
      state.logado = action.payload.logado;
      state.roles = action.payload.roles || [];
    },
    logout: (state) => {
      state.token = null;
      state.id = null;
      state.nome = null;
      state.logado = false;
      state.roles = [];
    }
  }
});

export const { setToken, logout } = authSlice.actions;
export default authSlice.reducer;
