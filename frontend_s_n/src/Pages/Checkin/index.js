import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useSelector } from "react-redux";

export default function Checkin() {
  const [evento, setEvento] = useState("");
  const [mensagem, setMensagem] = useState("");
  const navigate = useNavigate();
  const nome = useSelector(state => state.auth.nome);

  async function checkinSubmit(e) {
    e.preventDefault();

    

    if (!evento) {
      setMensagem("Por favor, informe o nome do evento.");
      return;
    }

    try {
      const response = await axios.post("http://localhost:8080/checkins", {
        usuario:nome, 
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
      <h2
        className="text-center mb-4"
        style={{
          fontFamily: "'Poppins', sans-serif",
          fontWeight: "600",
          fontSize: "2rem",
          color: "#4C4C6C"
        }}
      >
        Check-in
      </h2>

      <div className="text-center mb-4">
        <h4
          style={{
            fontFamily: "'Poppins', sans-serif",
            fontWeight: "500",
            fontSize: "1.2rem",
            color: "#6C757D"
          }}
        >
          Confirme seu Checkin para Entrar no Evento!
        </h4>
      </div>

      <form onSubmit={checkinSubmit} className="border p-4 rounded shadow-sm" style={{ backgroundColor: "#f9f9f9", boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)" }}>
        <div className="mb-3">
          <label htmlFor="evento" className="form-label" style={{ fontWeight: "600", fontSize: "1.1rem" }}>
            Informe o Nome do Evento
          </label>
          <input
            type="text"
            id="evento"
            name="evento"
            value={evento}
            onChange={(e) => setEvento(e.target.value)}
            className="form-control"
            placeholder="Nome do evento"
            style={{
              borderRadius: "8px",
              borderColor: "#ced4da",
              fontSize: "1rem",
              padding: "12px",
              fontFamily: "'Poppins', sans-serif",
            }}
          />
        </div>

        <button
          type="submit"
          className="btn btn-primary w-100 py-2"
          style={{
            fontSize: "1.2rem",
            fontWeight: "600",
            borderRadius: "8px",
            backgroundColor: "#4C9A2A",
            borderColor: "#4C9A2A",
            boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)",
          }}
        >
          Realizar Check-in
        </button>
      </form>

      {mensagem && (
        <div
          className={`alert mt-3 ${mensagem.includes("sucesso") ? "alert-success" : "alert-danger"}`}
          role="alert"
          style={{ fontFamily: "'Poppins', sans-serif", fontWeight: "500" }}
        >
          {mensagem}
        </div>
      )}

      <div className="text-center mt-4">
        <button
          onClick={logout}
          className="btn btn-link"
          style={{
            fontFamily: "'Poppins', sans-serif",
            fontSize: "1rem",
            color: "#007bff",
            textDecoration: "underline",
          }}
        >
          Sair
        </button>
      </div>
    </div>
  );
}
