import { useState } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";

export default function Cadastro() {
  const [usuario, setUsuario] = useState("");
  const [senha, setSenha] = useState("");
  const navigate = useNavigate();

  async function cadastroSubmit(e) {
    e.preventDefault();
    const newUser = {
      usuario,
      senha,
    };

    try {
      // Enviar os dados do cadastro para a API
      const response = await axios.post('http://localhost:3001/cadastro', newUser);

      if (response.status === 201) {
        alert("Cadastro realizado com sucesso!");
        navigate("/login"); // Redireciona para a página de login após cadastro
      } else {
        alert("Erro ao cadastrar. Tente novamente.");
      }
    } catch (error) {
      console.error("Erro ao realizar o cadastro", error);
      alert("Erro ao cadastrar. Tente novamente.");
    }
  }

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4">Cadastro de Usuário</h2>

      <form onSubmit={cadastroSubmit} className="border p-4 rounded shadow-sm">
        <div className="mb-3">
          <label htmlFor="usuario" className="form-label">Usuário</label>
          <input
            type="text"
            value={usuario}
            onChange={(e) => setUsuario(e.target.value)}
            className="form-control"
            id="usuario"
            placeholder="Escolha um nome de usuário"
          />
        </div>

        <div className="mb-3">
          <label htmlFor="senha" className="form-label">Senha</label>
          <input
            type="password"
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
            className="form-control"
            id="senha"
            placeholder="Escolha uma senha"
          />
        </div>

        <button type="submit" className="btn btn-primary w-100">
          Cadastrar
        </button>
      </form>
    </div>
  );
}