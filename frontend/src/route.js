
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import Home from "./Pages/Home";
import Header from './components/Header';
import Footer from './components/Footer';
import Cadastro from "./Pages/Cadastro";
import Sidebar from "./components/Sidebar";
import Login from "./Pages/Login";
import Checkin from "./Pages/Checkin";
import CadastroEvento from "./Pages/Evento";
import ListaCheckin from "./Pages/Listagem";
import { Provider, useSelector } from "react-redux";
import store from "./redux/store";


function PrivateRoute({ children, isAdminRoute }) {

    const token = useSelector(state => state.auth.token);
    const administrador = useSelector(state => state.auth.administrador);

    // Se for uma rota de admin e o usuário não for administrador, redireciona para a home
    if (isAdminRoute && !administrador) {
        return <Navigate to="/" replace />;
    }

    return token? children: <Navigate to="/login" replace />;
}

function ProtectedLayout({ children }) {
   

    return (
        <>
            <Header />
            <div className="d-flex">
                <Sidebar />
                <div className="flex-grow-1 p-4">
                    <main>
                        {children}
                    </main>
                </div>
            </div>
            <Footer />
        </>
    );
}

export default function AppRoutes() {
    return (
        <BrowserRouter>
            <Provider store={store}>
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
                                        <Route path="/checkin" element={<Checkin />} />
                                    </Routes>
                                </ProtectedLayout>  
                            </PrivateRoute>
                        }
                    />
                </Routes>
            </Provider>
        </BrowserRouter>
    );
}