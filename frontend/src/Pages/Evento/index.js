import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function CadastroEvento() {
  const navigate = useNavigate();

  const [nomeEvento, setNomeEvento] = useState("");


  async function handleSubmit(e) {
    e.preventDefault();

    const novoEvento = {
      nomeEvento,
    };

    try {
      const response = await axios.post("http://localhost:3001/eventos", novoEvento);
      if (response.status === 201) {
        alert("Evento cadastrado com sucesso!");
        navigate("/");
      } else {
        alert("Erro ao cadastrar o evento.");
      }
    } catch (error) {
      console.error("Erro ao cadastrar evento:", error);
      alert("Erro ao cadastrar o evento.");
    }
  }

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4" style={{ fontFamily: "'Poppins', sans-serif", color: "#4C4C6C", fontSize: "2rem", fontWeight: "600" }}>
        Cadastro de Evento
      </h2>

      <form onSubmit={handleSubmit} className="border p-5 rounded shadow-sm" style={{ backgroundColor: "#f9f9f9", boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)" }}>
        <div className="row mb-4">
          <div className="col-md-6">
            <label htmlFor="nomeEvento" className="form-label" style={{ fontWeight: "600", fontSize: "1.1rem" }}>
              Nome do Evento
            </label>
            <input
              type="text"
              className="form-control"
              id="nomeEvento"
              value={nomeEvento}
              onChange={(e) => setNomeEvento(e.target.value)}
              required
              style={{ borderRadius: "10px", borderColor: "#ced4da" }}
            />
          </div>
        </div>

        <button type="submit" className="btn btn-success w-100 py-2" style={{ fontSize: "1.2rem", fontWeight: "600", borderRadius: "8px" }}>
          Cadastrar Evento
        </button>
      </form>
    </div>
  );
}
