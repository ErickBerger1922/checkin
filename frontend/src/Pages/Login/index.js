import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { authService } from "../../service/auth.service";
import { useDispatch } from "react-redux";
import { setToken } from "../../redux/authSlice";
import { jwtDecode } from "jwt-decode"; // Corrigido import (sem chaves)

export default function Login() {
  const [usuarioInformado, setUsuario] = useState("");
  const [senha, setSenha] = useState("");
  const navigate = useNavigate();
  const dispatch = useDispatch();

  async function loginSubmit(e) {
    e.preventDefault();

    try {
      const response = await authService.login({
        grant_type: "password",
        username: usuarioInformado,
        password: senha,
      });

      const accessToken = response.access_token;

      if (accessToken) {
        const decodedToken = jwtDecode(accessToken);
        const authorities = decodedToken.authorities || [];

        // Busca detalhada do usuário (getMe)
        const meResponse = await fetch("http://localhost:8080/usuarios/me", {
          headers: {
            Authorization: `Bearer ${accessToken}`,
            "Content-Type": "application/json",
          },
        });

        if (!meResponse.ok) {
          throw new Error("Erro ao buscar dados do usuário logado");
        }

        const meData = await meResponse.json();

        dispatch(
          setToken({
            nome: meData.nome,
            id: meData.id, // ← id real do usuário (empresa)
            token: accessToken,
            logado: true,
            roles: authorities,
          })
        );

        navigate("/");
      } else {
        alert("Usuário ou senha inválidos");
      }
    } catch (error) {
      console.error("Erro no login:", error);
      alert("Erro ao fazer login. Tente novamente.");
    }
  }

  function navigateToCadastro() {
    navigate("/cadastro");
  }

  return (
    <div
      className="container d-flex justify-content-center align-items-center min-vh-100"
      style={{ backgroundColor: "white" }}
    >
      <div
        className="card shadow-lg p-4 rounded"
        style={{ maxWidth: "400px", width: "100%", backgroundColor: "#ffffff" }}
      >
        <h2
          className="text-center mb-4"
          style={{
            fontFamily: "'Poppins', sans-serif",
            color: "#4C4C6C",
            fontSize: "2rem",
            fontWeight: "600",
          }}
        >
          Login
        </h2>

        <form onSubmit={loginSubmit}>
          <div className="mb-3">
            <label
              htmlFor="usuario"
              className="form-label"
              style={{ fontWeight: "600", fontSize: "1.1rem" }}
            >
              Usuário
            </label>
            <input
              type="text"
              value={usuarioInformado}
              onChange={(e) => setUsuario(e.target.value)}
              className="form-control"
              id="usuario"
              placeholder="Digite seu nome"
              required
              style={{ borderRadius: "10px", borderColor: "#ced4da" }}
            />
          </div>

          <div className="mb-3">
            <label
              htmlFor="senha"
              className="form-label"
              style={{ fontWeight: "600", fontSize: "1.1rem" }}
            >
              Senha
            </label>
            <input
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              type="password"
              className="form-control"
              id="senha"
              placeholder="Digite a senha"
              required
              style={{ borderRadius: "10px", borderColor: "#ced4da" }}
            />
          </div>

          <button
            type="submit"
            className="btn btn-primary w-100 py-2"
            style={{
              fontSize: "1.2rem",
              fontWeight: "600",
              borderRadius: "8px",
            }}
          >
            Entrar
          </button>
        </form>

        <hr className="my-3" style={{ borderTop: "1px solid #e1e4e8" }} />

        <div className="text-center">
          <p
            className="mb-2"
            style={{ fontSize: "1rem", fontWeight: "500", color: "#6c757d" }}
          >
            Não tem uma conta?
          </p>
          <button
            onClick={navigateToCadastro}
            className="btn btn-outline-secondary btn-sm"
            style={{ borderRadius: "8px", fontWeight: "600" }}
          >
            Cadastre-se aqui
          </button>
        </div>
      </div>
    </div>
  );
}
