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

        navigate("/"); // Redireciona para a Home
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
    <div className="container d-flex justify-content-center align-items-center min-vh-100">
      <div className="card shadow p-4" style={{ maxWidth: '400px', width: '100%' }}>
        <h2 className="text-center mb-4">Login</h2>

        <form onSubmit={loginSubmit}>
          <div className="mb-3">
            <label htmlFor="usuario" className="form-label">Usuário</label>
            <input
              type="text"
              value={usuarioInformado}
              onChange={(e) => setUsuario(e.target.value)}
              className="form-control"
              id="usuario"
              placeholder="Digite seu nome"
              required
            />
          </div>

          <div className="mb-3">
            <label htmlFor="senha" className="form-label">Senha</label>
            <input
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              type="password"
              className="form-control"
              id="senha"
              placeholder="Digite a senha"
              required
            />
          </div>

          <button type="submit" className="btn btn-primary w-100">
            Entrar
          </button>
        </form>

        <hr className="my-3" />

        <div className="text-center">
          <p className="mb-2">Não tem uma conta?</p>
          <button onClick={navigateToCadastro} className="btn btn-outline-secondary btn-sm">
            Cadastre-se aqui
          </button>
        </div>
      </div>
    </div>
  );
}