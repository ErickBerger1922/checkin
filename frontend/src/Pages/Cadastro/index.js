import { useState } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";

export default function Cadastro() {
  const [tipoPessoa, setTipoPessoa] = useState("fisica");
  const [nome, setNome] = useState("");
  const [razaoSocial, setRazaoSocial] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [cpf, setCpf] = useState("");
  const [cnpj, setCnpj] = useState("");
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
		tipoPessoa,
		nome,
		razaoSocial: tipoPessoa === "empresa" ? razaoSocial : undefined,
		email,
		senha,
		cpf: tipoPessoa === "fisica" ? cpf : null,
		cnpj: tipoPessoa === "empresa" ? cnpj : null,
		cep,
		logradouro,
		numero,
		complemento,
		bairro,
		cidade,
		estado,
		tipoUsuario: tipoPessoa === "empresa" ? "EMPRESA" : "PESSOA_FISICA",
		funcoes: tipoPessoa === "empresa" ? ["ROLE_ENTERPRISE"] : ["ROLE_USER"]
    };

    try {
      const response = await axios.post('http://localhost:8080/usuarios', newUser);
      if (response.status === 200 || response.status === 201) {
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
    navigate("/login");
  }

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4" style={{ fontFamily: "'Poppins', sans-serif", color: "#4C4C6C" }}>Cadastro de Usuário</h2>

      <form onSubmit={cadastroSubmit} className="border p-5 rounded-lg shadow-lg" style={{ backgroundColor: "#f9f9f9" }}>
        <div className="mb-4">
          <label style={{ fontWeight: "600", fontSize: "1.2rem", marginRight: "20px" }}>
            <input
              type="radio"
              value="fisica"
              checked={tipoPessoa === "fisica"}
              onChange={() => setTipoPessoa("fisica")}
              style={{ marginRight: "8px" }}
            />
            Pessoa física
          </label>

          <label style={{ fontWeight: "600", fontSize: "1.2rem" }}>
            <input
              type="radio"
              value="empresa"
              checked={tipoPessoa === "empresa"}
              onChange={() => setTipoPessoa("empresa")}
              style={{ marginRight: "8px" }}
            />
            Empresa
          </label>
        </div>

        <div className="row">

          <div className="col-md-6 mb-3">
            <label htmlFor="email" className="form-label" style={{ fontWeight: "500", fontSize: "1.1rem" }}>Email</label>
            <input
              type="text"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="form-control"
              id="email"
              style={{ borderRadius: "8px", borderColor: "#ccc" }}
            />
          </div>

          <div className="col-md-6 mb-3">
            <label htmlFor="senha" className="form-label" style={{ fontWeight: "500", fontSize: "1.1rem" }}>Senha</label>
            <input
              type="password"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              className="form-control"
              id="senha"
              style={{ borderRadius: "8px", borderColor: "#ccc" }}
            />
          </div>

          <div className="col-md-6 mb-3">
            <label htmlFor="nome" className="form-label" style={{ fontWeight: "500", fontSize: "1.1rem" }}>
              {tipoPessoa === "fisica" ? "Nome" : "Nome Fantasia"}
            </label>
            <input
              type="text"
              value={nome}
              onChange={(e) => setNome(e.target.value)}
              className="form-control"
              id="nome"
              style={{ borderRadius: "8px", borderColor: "#ccc" }}
            />
          </div>

          {tipoPessoa === "empresa" && (
            <div className="col-md-6 mb-3">
              <label htmlFor="razaoSocial" className="form-label" style={{ fontWeight: "500", fontSize: "1.1rem" }}>
                Razão Social
              </label>
              <input
                type="text"
                value={razaoSocial}
                onChange={(e) => setRazaoSocial(e.target.value)}
                className="form-control"
                id="razaoSocial"
                style={{ borderRadius: "8px", borderColor: "#ccc" }}
              />
            </div>
          )}

          {tipoPessoa === "fisica" && (
            <div className="col-md-6 mb-3">
              <label htmlFor="cpf" className="form-label" style={{ fontWeight: "500", fontSize: "1.1rem" }}>
                CPF
              </label>
              <input
                type="text"
                value={cpf}
                onChange={(e) => setCpf(e.target.value)}
                className="form-control"
                id="cpf"
                style={{ borderRadius: "8px", borderColor: "#ccc" }}
              />
            </div>
          )}

          {tipoPessoa === "empresa" && (
            <div className="col-md-6 mb-3">
              <label htmlFor="cnpj" className="form-label" style={{ fontWeight: "500", fontSize: "1.1rem" }}>
                CNPJ
              </label>
              <input
                type="text"
                value={cnpj}
                onChange={(e) => setCnpj(e.target.value)}
                className="form-control"
                id="cnpj"
                style={{ borderRadius: "8px", borderColor: "#ccc" }}
              />
            </div>
          )}

          <div className="col-md-6 mb-3">
            <label htmlFor="cep" className="form-label" style={{ fontWeight: "500", fontSize: "1.1rem" }}>CEP</label>
            <input
              type="text"
              value={cep}
              onChange={(e) => setCep(e.target.value)}
              onBlur={buscarEndereco}
              className="form-control"
              id="cep"
              style={{ borderRadius: "8px", borderColor: "#ccc" }}
            />
            {erroCep && <p className="text-danger">{erroCep}</p>}
          </div>

          <div className="col-md-6 mb-3">
            <label htmlFor="logradouro" className="form-label" style={{ fontWeight: "500", fontSize: "1.1rem" }}>Logradouro</label>
            <input
              type="text"
              value={logradouro}
              onChange={(e) => setLogradouro(e.target.value)}
              className="form-control"
              id="logradouro"
              style={{ borderRadius: "8px", borderColor: "#ccc" }}
            />
          </div>

          <div className="col-md-6 mb-3">
            <label htmlFor="numero" className="form-label" style={{ fontWeight: "500", fontSize: "1.1rem" }}>Número</label>
            <input
              type="text"
              value={numero}
              onChange={(e) => setNumero(e.target.value)}
              className="form-control"
              id="numero"
              style={{ borderRadius: "8px", borderColor: "#ccc" }}
            />
          </div>

          <div className="col-md-6 mb-3">
            <label htmlFor="complemento" className="form-label" style={{ fontWeight: "500", fontSize: "1.1rem" }}>Complemento</label>
            <input
              type="text"
              value={complemento}
              onChange={(e) => setComplemento(e.target.value)}
              className="form-control"
              id="complemento"
              placeholder="Opcional"
              style={{ borderRadius: "8px", borderColor: "#ccc" }}
            />
          </div>

          <div className="col-md-6 mb-3">
            <label htmlFor="bairro" className="form-label" style={{ fontWeight: "500", fontSize: "1.1rem" }}>Bairro</label>
            <input
              type="text"
              value={bairro}
              onChange={(e) => setBairro(e.target.value)}
              className="form-control"
              id="bairro"
              style={{ borderRadius: "8px", borderColor: "#ccc" }}
            />
          </div>

          <div className="col-md-6 mb-3">
            <label htmlFor="cidade" className="form-label" style={{ fontWeight: "500", fontSize: "1.1rem" }}>Cidade</label>
            <input
              type="text"
              value={cidade}
              onChange={(e) => setCidade(e.target.value)}
              className="form-control"
              id="cidade"
              style={{ borderRadius: "8px", borderColor: "#ccc" }}
            />
          </div>

          <div className="col-md-6 mb-3">
            <label htmlFor="estado" className="form-label" style={{ fontWeight: "500", fontSize: "1.1rem" }}>Estado</label>
            <input
              type="text"
              value={estado}
              onChange={(e) => setEstado(e.target.value)}
              className="form-control"
              id="estado"
              style={{ borderRadius: "8px", borderColor: "#ccc" }}
            />
          </div>
        </div>

        <button type="submit" className="btn btn-primary w-100 py-2" style={{ borderRadius: "50px", fontSize: "1.2rem", fontWeight: "600" }}>
          Cadastrar
        </button>
      </form>

      <div className="text-center mt-3">
        <button onClick={navigateToLogin} className="btn btn-link" style={{ fontSize: "1.1rem", textDecoration: "underline" }}>
          Já tem uma conta? Faça seu Login aqui!
        </button>
      </div>
    </div>
  );
}
