import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import Home from "./Pages/Home";
import Header from './components/Header';
import Footer from './components/Footer';
import Cadastro from "./Pages/Cadastro";
import Sidebar from "./components/Sidebar";
import Login from "./Pages/Login";
import Checkin from "./Pages/Checkin";
import CadastroEvento from "./Pages/Evento";
import ListaEventos from "./Pages/ListagemEventos"
import ListaCheckins from "./Pages/ListagemCheckins";
import { Provider, useSelector } from "react-redux";
import store from "./redux/store";

function PrivateRoute({ children, allowedRoles = [] }) {
	const token = useSelector(state => state.auth.token);
	const roles = useSelector(state => state.auth.roles || []);

	if (!token) {
		return <Navigate to="/login" replace />;
	}

	if (allowedRoles.length === 0) {
		return children;
	}

	const hasRole = roles.some(role => allowedRoles.includes(role));

	if (!hasRole) {
		return <Navigate to="/" replace />;
	}

	return children;
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

									<Route 
										path="/cadastroevento" 
										element={
											<PrivateRoute allowedRoles={["ROLE_ADMIN", "ROLE_ENTERPRISE"]}>
												<CadastroEvento />
											</PrivateRoute>
										} 
									/>

									<Route 
										path="/listaeventos" 
										element={
											<PrivateRoute allowedRoles={["ROLE_ADMIN", "ROLE_ENTERPRISE", "ROLE_USER"]}>
												<ListaEventos />
											</PrivateRoute>
										} 
									/>

									<Route 
										path="/listacheckins" 
										element={
											<PrivateRoute allowedRoles={["ROLE_ADMIN", "ROLE_ENTERPRISE"]}>
												<ListaCheckins />
											</PrivateRoute>
										} 
									/>

									<Route 
										path="/checkin" 
										element={
											<PrivateRoute allowedRoles={["ROLE_ADMIN", "ROLE_ENTERPRISE", "ROLE_USER"]}>
												<Checkin />
											</PrivateRoute>
										} 
									/>
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
