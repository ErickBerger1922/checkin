import { useState } from "react";
import { useUsuarioContext } from "../../contexts/Usuario";
import { useNavigate } from "react-router-dom";
import axios from "axios";  

export default function Login() {
  const [usuarioInformado, setUsuario] = useState("");
  const [senha, setSenha] = useState("");
  const { login } = useUsuarioContext();
  const navigate = useNavigate();

  async function loginSubmit(e) {
    e.preventDefault();
    try {
      const responseAxios = await axios.get(`http://localhost:3001/pessoas?usuario=${usuarioInformado}&senha=${senha}`);
      if (responseAxios.data.length > 0) {
        const usuarioLogado = responseAxios.data[0];

        login({
          nome: usuarioInformado,
          usuarioInformado,
          logado: true,
          administrador: usuarioLogado.administrador,
        });

        navigate("/"); 
      } else {
        alert("Usuário ou senha inválidos");
      }
    } catch (error) {
      console.error("Erro no login", error);
      alert("Erro ao fazer login. Tente novamente.");
    }
  }

  function navigateToCadastro() {
    navigate("/cadastro");
  }

  return (
    <div className="container d-flex justify-content-center align-items-center min-vh-100" style={{ backgroundColor: "white" }}>
      <div className="card shadow-lg p-4 rounded" style={{ maxWidth: '400px', width: '100%', backgroundColor: "#ffffff" }}>
        <h2 className="text-center mb-4" style={{ fontFamily: "'Poppins', sans-serif", color: "#4C4C6C", fontSize: "2rem", fontWeight: "600" }}>
          Login
        </h2>

        <form onSubmit={loginSubmit}>
          <div className="mb-3">
            <label htmlFor="usuario" className="form-label" style={{ fontWeight: "600", fontSize: "1.1rem" }}>Usuário</label>
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
            <label htmlFor="senha" className="form-label" style={{ fontWeight: "600", fontSize: "1.1rem" }}>Senha</label>
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

          <button type="submit" className="btn btn-primary w-100 py-2" style={{ fontSize: "1.2rem", fontWeight: "600", borderRadius: "8px" }}>
            Entrar
          </button>
        </form>

        <hr className="my-3" style={{ borderTop: "1px solid #e1e4e8" }} />

        <div className="text-center">
          <p className="mb-2" style={{ fontSize: "1rem", fontWeight: "500", color: "#6c757d" }}>Não tem uma conta?</p>
          <button onClick={navigateToCadastro} className="btn btn-outline-secondary btn-sm" style={{ borderRadius: "8px", fontWeight: "600" }}>
            Cadastre-se aqui
          </button>
        </div>
      </div>
    </div>
  );
}
