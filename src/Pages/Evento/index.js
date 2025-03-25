import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useUsuarioContext } from "../../contexts/Usuario";

export default function CadastroEvento() {
  const { usuario } = useUsuarioContext();
  const navigate = useNavigate();

  const [nomeEvento, setNomeEvento] = useState("");
  const [cep, setCep] = useState("");
  const [logradouro, setLogradouro] = useState("");
  const [numero, setNumero] = useState("");
  const [complemento, setComplemento] = useState("");
  const [bairro, setBairro] = useState("");
  const [cidade, setCidade] = useState("");
  const [estado, setEstado] = useState("");
  const [erroCep, setErroCep] = useState("");

  // Função para buscar o endereço pelo CEP
  async function buscarEndereco() {
    try {
      const response = await axios.get(`https://viacep.com.br/ws/${cep}/json/`);
      if (response.data.erro) {
        setErroCep("CEP não encontrado.");
        setLogradouro("");
        setBairro("");
        setCidade("");
        setEstado("");
      } else {
        setErroCep("");
        setLogradouro(response.data.logradouro);
        setBairro(response.data.bairro);
        setCidade(response.data.localidade);
        setEstado(response.data.uf);
      }
    } catch (error) {
      setErroCep("Erro ao buscar o CEP.");
      setLogradouro("");
      setBairro("");
      setCidade("");
      setEstado("");
    }
  }

  // Função para cadastrar o evento
  async function handleSubmit(e) {
    e.preventDefault();

    const novoEvento = {
      nomeEvento,
      endereco: {
        cep,
        logradouro,
        numero,
        complemento,
        bairro,
        cidade,
        estado,
      },
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

          <div className="col-md-6">
            <label htmlFor="cep" className="form-label" style={{ fontWeight: "600", fontSize: "1.1rem" }}>
              CEP
            </label>
            <input
              type="text"
              className="form-control"
              id="cep"
              value={cep}
              onChange={(e) => setCep(e.target.value)}
              onBlur={buscarEndereco}
              required
              style={{ borderRadius: "10px", borderColor: "#ced4da" }}
            />
            {erroCep && <small className="text-danger">{erroCep}</small>}
          </div>
        </div>

        <div className="row mb-4">
          <div className="col-md-6">
            <label htmlFor="logradouro" className="form-label" style={{ fontWeight: "600", fontSize: "1.1rem" }}>
              Logradouro
            </label>
            <input
              type="text"
              className="form-control"
              id="logradouro"
              value={logradouro}
              onChange={(e) => setLogradouro(e.target.value)}
              style={{ borderRadius: "10px", borderColor: "#ced4da" }}
            />
          </div>

          <div className="col-md-6">
            <label htmlFor="numero" className="form-label" style={{ fontWeight: "600", fontSize: "1.1rem" }}>
              Número
            </label>
            <input
              type="text"
              className="form-control"
              id="numero"
              value={numero}
              onChange={(e) => setNumero(e.target.value)}
              style={{ borderRadius: "10px", borderColor: "#ced4da" }}
            />
          </div>
        </div>

        <div className="row mb-4">
          <div className="col-md-6">
            <label htmlFor="complemento" className="form-label" style={{ fontWeight: "600", fontSize: "1.1rem" }}>
              Complemento
            </label>
            <input
              type="text"
              className="form-control"
              id="complemento"
              value={complemento}
              onChange={(e) => setComplemento(e.target.value)}
              style={{ borderRadius: "10px", borderColor: "#ced4da" }}
            />
          </div>

          <div className="col-md-6">
            <label htmlFor="bairro" className="form-label" style={{ fontWeight: "600", fontSize: "1.1rem" }}>
              Bairro
            </label>
            <input
              type="text"
              className="form-control"
              id="bairro"
              value={bairro}
              onChange={(e) => setBairro(e.target.value)}
              style={{ borderRadius: "10px", borderColor: "#ced4da" }}
            />
          </div>
        </div>

        <div className="row mb-4">
          <div className="col-md-6">
            <label htmlFor="cidade" className="form-label" style={{ fontWeight: "600", fontSize: "1.1rem" }}>
              Cidade
            </label>
            <input
              type="text"
              className="form-control"
              id="cidade"
              value={cidade}
              onChange={(e) => setCidade(e.target.value)}
              style={{ borderRadius: "10px", borderColor: "#ced4da" }}
            />
          </div>

          <div className="col-md-6">
            <label htmlFor="estado" className="form-label" style={{ fontWeight: "600", fontSize: "1.1rem" }}>
              Estado
            </label>
            <input
              type="text"
              className="form-control"
              id="estado"
              value={estado}
              onChange={(e) => setEstado(e.target.value)}
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
