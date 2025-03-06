import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./Pages/Home";
import Cadastro from "./Pages/Cadastro";
import Footer from "./components/Footer";
import Header from "./components/Header";
import Login from "./Pages/Login";

export default function AppRoutes() {

    return (
        <BrowserRouter>
        <Header/>
            <Routes>
                <Route path="/" element={<Home />} ></Route>
                <Route path="/cadastro" element={<Cadastro />} ></Route>
                <Route path="/login" element={<Login />} ></Route>
            </Routes>
            <Footer/>
        </BrowserRouter>
    )

}