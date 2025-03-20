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

function PrivateRoute({ children }) {

    const {usuario} = useContext(UsuarioContext);
    
    if (!usuario.logado) {
        return <Navigate to="/login" replace />
    }
    return children;
}

export default function AppRoutes() {
    
    return (
        <BrowserRouter>
            <UsuarioLogadoProvider>

                <Routes>
                    <Route path="/login" element={<Login />} ></Route>
                    <Route path="/cadastro" element={<Cadastro />} ></Route>
                    <Route path="/*"
                        element={
                            <PrivateRoute>
                                <ProtectedLayout>
                                    <Routes>
                                        <Route path="/" element={<Home />} ></Route>
                                        <Route path="/cadastro" element={<Cadastro />} ></Route>
                                    </Routes>
                                </ProtectedLayout>
                            </PrivateRoute>
                        }
                    />
                </Routes>
            </UsuarioLogadoProvider>
        </BrowserRouter>
    )

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


    // <Header />
    // <div className="d-flex">
    //     <Sidebar />
    //     <div className="flex-grow-1 p-4">
    // </div>
    //             </div>
    //             <Footer />

}