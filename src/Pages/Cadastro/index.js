import { useState } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";

export default function Cadastro() {
  const [usuario, setUsuario] = useState("");
  const [senha, setSenha] = useState("");
  const [cep, setCep] = useState("");
  const [logradouro, setLogradouro] = useState("");
  const [numero, setNumero] = useState("");
  const [complemento, setComplemento] = useState(""); 
  const [bairro, setBairro] = useState("");
  const [cidade, setCidade] = useState("");
  const [estado, setEstado] = useState("");
  const [erroCep, setErroCep] = useState("");
  const navigate = useNavigate();

  async function buscarEndereco() {
    try {
      debugger;

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

  async function cadastroSubmit(e) {
    e.preventDefault();
    const newUser = {
      usuario,
      senha,
      cep,
      logradouro,
      numero,
      complemento,
      bairro,
      cidade,
      estado,
    };

    try {

      const response = await axios.post('http://localhost:3001/pessoas', newUser);

      if (response.status === 201) {
        alert("Cadastro realizado com sucesso!");
        navigate("/login");
      } else {
        alert("Erro ao cadastrar. Tente novamente.");
      }
    } catch (error) {
      console.error("Erro ao realizar o cadastro", error);
      alert("Erro ao cadastrar. Tente novamente.");
    }
  }

  function navigateToLogin() {
    navigate("/login"); // Redireciona para a página de login
  }

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4">Cadastro de Usuário</h2>

      <form onSubmit={cadastroSubmit} className="border p-4 rounded shadow-sm">
        <div className="mb-3">
          <label htmlFor="usuario" className="form-label">
            Usuário
          </label>
          <input
            type="text"
            value={usuario}
            onChange={(e) => setUsuario(e.target.value)}
            className="form-control"
            id="usuario"
            
          />
        </div>

        <div className="mb-3">
          <label htmlFor="senha" className="form-label">
            Senha
          </label>
          <input
            type="password"
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
            className="form-control"
            id="senha"
            
          />
        </div>

        {/* Campo CEP */}
        <div className="mb-3">
          <label htmlFor="cep" className="form-label">
            CEP
          </label>
          <input
            type="text"
            value={cep}
            onChange={(e) => {
              setCep(e.target.value);
            }}
            onBlur={buscarEndereco}
            className="form-control"
            id="cep"
          />
          {erroCep && <p className="text-danger">{erroCep}</p>}
        </div>

        <div className="mb-3">
          <label htmlFor="logradouro" className="form-label">
            Logradouro
          </label>
          <input
            type="text"
            value={logradouro}
            onChange={(e) => setLogradouro(e.target.value)}
            className="form-control"
            id="logradouro"

          />
        </div>

        <div className="mb-3">
          <label htmlFor="numero" className="form-label">
            Número
          </label>
          <input
            type="text"
            value={numero}
            onChange={(e) => setNumero(e.target.value)}
            className="form-control"
            id="numero"
          />
        </div>

         <div className="mb-3">
          <label htmlFor="complemento" className="form-label">
            Complemento 
          </label>
          <input
            type="text"
            value={complemento}
            onChange={(e) => setComplemento(e.target.value)}
            className="form-control"
            id="complemento"
            placeholder="Opcional"
          />
        </div>

        <div className="mb-3">
          <label htmlFor="bairro" className="form-label">
            Bairro
          </label>
          <input
            type="text"
            value={bairro}
            onChange={(e) => setBairro(e.target.value)}
            className="form-control"
            id="bairro"
        
          />
        </div>

        <div className="mb-3">
          <label htmlFor="cidade" className="form-label">
            Cidade
          </label>
          <input
            type="text"
            value={cidade}
            onChange={(e) => setCidade(e.target.value)}
            className="form-control"
            id="cidade"
          />
        </div>

        <div className="mb-3">
          <label htmlFor="estado" className="form-label">
            Estado
          </label>
          <input
            type="text"
            value={estado}
            onChange={(e) => setEstado(e.target.value)}
            className="form-control"
            id="estado"
            
          />
        </div>

        <button type="submit" className="btn btn-primary w-100">
          Cadastrar
        </button>
      </form>
      <div className="text-center mt-3">
        <button onClick={navigateToLogin} className="btn btn-link">
          Já tem uma conta? Faça seu Login aqui!
        </button>
      </div>
    </div>
  );
}