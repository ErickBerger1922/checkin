import { useState, useEffect } from "react";
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
      <h2 className="text-center mb-4">Cadastro de Evento</h2>

      <form onSubmit={handleSubmit} className="border p-4 rounded shadow-sm">
        <div className="mb-3">
          <label htmlFor="nomeEvento" className="form-label">Nome do Evento</label>
          <input
            type="text"
            className="form-control"
            id="nomeEvento"
            value={nomeEvento}
            onChange={(e) => setNomeEvento(e.target.value)}
            required
          />
        </div>

        <div className="mb-3">
          <label htmlFor="cep" className="form-label">CEP</label>
          <input
            type="text"
            className="form-control"
            id="cep"
            value={cep}
            onChange={(e) => setCep(e.target.value)}
            onBlur={buscarEndereco}
            required
          />
          {erroCep && <small className="text-danger">{erroCep}</small>}
        </div>

        <div className="mb-3">
          <label htmlFor="logradouro" className="form-label">Logradouro</label>
          <input
            type="text"
            className="form-control"
            id="logradouro"
            value={logradouro}
            onChange={(e) => setLogradouro(e.target.value)}
          />
        </div>

        <div className="mb-3">
          <label htmlFor="numero" className="form-label">Número</label>
          <input
            type="text"
            className="form-control"
            id="numero"
            value={numero}
            onChange={(e) => setNumero(e.target.value)}
          />
        </div>

        <div className="mb-3">
          <label htmlFor="complemento" className="form-label">Complemento</label>
          <input
            type="text"
            className="form-control"
            id="complemento"
            value={complemento}
            onChange={(e) => setComplemento(e.target.value)}
          />
        </div>

        <div className="mb-3">
          <label htmlFor="bairro" className="form-label">Bairro</label>
          <input
            type="text"
            className="form-control"
            id="bairro"
            value={bairro}
            onChange={(e) => setBairro(e.target.value)}
          />
        </div>

        <div className="mb-3">
          <label htmlFor="cidade" className="form-label">Cidade</label>
          <input
            type="text"
            className="form-control"
            id="cidade"
            value={cidade}
            onChange={(e) => setCidade(e.target.value)}
          />
        </div>

        <div className="mb-3">
          <label htmlFor="estado" className="form-label">Estado</label>
          <input
            type="text"
            className="form-control"
            id="estado"
            value={estado}
            onChange={(e) => setEstado(e.target.value)}
          />
        </div>

        <button type="submit" className="btn btn-success w-100">
          Cadastrar Evento
        </button>
      </form>
    </div>
  );
}