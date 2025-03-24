import { useState } from "react";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import Home from "./Pages/Home";
import Header from './components/Header';
import Footer from './components/Footer';
import Cadastro from "./Pages/Cadastro";
import Sidebar from "./components/Sidebar";
import Login from "./Pages/Login";
import UsuarioLogadoProvider, { UsuarioContext } from "./contexts/Usuario";
import { useContext } from "react";
import Checkin from "./Pages/Checkin";
import CadastroEvento from "./Pages/Evento";
import ListaCheckin from "./Pages/Listagem";

function PrivateRoute({ children, isAdminRoute }) {
    const { usuario } = useContext(UsuarioContext);
    
    // Verifica se o usuário está logado
    if (!usuario.logado) {
        return <Navigate to="/login" replace />
    }

    // Se for uma rota de admin e o usuário não for administrador, redireciona para a home
    if (isAdminRoute && !usuario.administrador) {
        return <Navigate to="/" replace />
    }

    return children;
}

export default function AppRoutes() {
    return (
        <BrowserRouter>
            <UsuarioLogadoProvider>
                <Routes>
                    <Route path="/login" element={<Login />} />
                    <Route path="/cadastro" element={<Cadastro />} />
                    <Route
                        path="/*"
                        element={
                            <PrivateRoute>
                                <ProtectedLayout>
                                    <Routes>
                                        <Route path="/" element={<Home />} />
                                        {/* Acesso restrito aos administradores */}
                                        <Route path="/cadastroevento" element={
                                            <PrivateRoute isAdminRoute={true}>
                                                <CadastroEvento />
                                            </PrivateRoute>
                                        } />
                                        <Route path="/listacheckin" element={
                                            <PrivateRoute isAdminRoute={true}>
                                                <ListaCheckin />
                                            </PrivateRoute>
                                        } />
                                        {/* Acesso livre para todos os usuários logados */}
                                        <Route path="/checkin" element={<Checkin />} />
                                    </Routes>
                                </ProtectedLayout>  
                            </PrivateRoute>
                        }
                    />
                </Routes>
            </UsuarioLogadoProvider>
        </BrowserRouter>
    )
}

function ProtectedLayout({ children }) {
    return (
        <>
            <Header />
            <div className="d-flex">
                <Sidebar />
                <div className="flex-grow-1 p-4">
                    {children}
                </div>
            </div>
            <Footer />
        </>
    );
}