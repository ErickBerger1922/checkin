import { useState } from "react";
import { useUsuarioContext } from "../../contexts/Usuario"; // Verifique se o contexto está correto
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function Checkin() {
  const { usuario } = useUsuarioContext(); // Pegando o estado do contexto corretamente
  const [evento, setEvento] = useState("");
  const [mensagem, setMensagem] = useState("");
  const navigate = useNavigate();

  async function checkinSubmit(e) {
    e.preventDefault();

    if (!evento) {
      setMensagem("Por favor, informe o nome do evento.");
      return;
    }

    try {
      const response = await axios.post("http://localhost:3001/checkin", {
        usuario: usuario.nome, // Usando o nome do usuário do contexto
        evento,
        data: new Date().toISOString(),
      });

      if (response.status === 201) {
        setMensagem("Check-in realizado com sucesso!");
      } else {
        setMensagem("Erro ao realizar o check-in. Tente novamente.");
      }
    } catch (error) {
      console.error("Erro ao realizar o check-in", error);
      setMensagem("Erro ao realizar o check-in. Tente novamente.");
    }
  }

  function logout() {
    navigate("/login");
  }

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4">Check-in</h2>

      <div>
        <h4>Bem-vindo, Confirme seu Checkin para Entrar no Evento!</h4>
        <form onSubmit={checkinSubmit} className="border p-4 rounded shadow-sm">
          <div className="mb-3">
            <label htmlFor="evento" className="form-label">
              Informe o Nome do Evento
            </label>
            <input
              type="text"
              id="evento"
              name="evento"
              value={evento}
              onChange={(e) => setEvento(e.target.value)}
              className="form-control"
              placeholder=""
            />
          </div>

          <button type="submit" className="btn btn-primary w-100">
            Realizar Check-in
          </button>
        </form>

        {mensagem && (
          <div className="alert mt-3" role="alert">
            {mensagem}
          </div>
        )}

        <div className="text-center mt-3">
          <button onClick={logout} className="btn btn-link">
            Sair
          </button>
        </div>
      </div>
    </div>
  );
}