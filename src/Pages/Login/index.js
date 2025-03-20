import { useState } from "react";
import axios from 'axios';
import { useUsuarioContext } from "../../contexts/Usuario";
import {  useNavigate } from "react-router-dom";

export default function Login() {

  const [usuarioInformado, setUsuario] = useState("");
  const [senha, setSenha] = useState("");
  const {login} = useUsuarioContext(useUsuarioContext);
  const navigate = useNavigate();

  async function loginSubmit(e){
    e.preventDefault();
    debugger;
    const responseAxios = await axios.get(`http://localhost:3001/pessoas?usuario=${usuarioInformado}&senha=${senha}`)
    if(responseAxios.data.length > 0){
      login({nome: usuarioInformado, usuarioInformado, logado: true,administrador:responseAxios.data[0].administrador});
      navigate("/");
    } else {
      alert("Usuário invalido")
    }
  }

  function navigateToCadastro() {
    navigate("/cadastro");
  }


  return(
    <div className="container mt-5">
      <h2 className="text-center mb-4">Faça seu Login para poder realizar o seu Checkin!</h2>
      
      <form onSubmit={loginSubmit} className="border p-4 rounded shadow-sm">
        <div className="mb-3">
          <label htmlFor="usuario" className="form-label">
            Usuário
          </label>
          <input
            type="text"
            value={usuarioInformado}
            onChange={(e)=>setUsuario(e.target.value)}
            className="form-control"
            id="usuario"
            name="usuario"
            placeholder="Digite seu nome"
          />
        </div>

        <div className="mb-3">
          <label htmlFor="evento" className="form-label">
            Senha
          </label>
          <input
          value={senha}
          onChange={(e)=>setSenha(e.target.value)}
            type="password"
            className="form-control"
            id="evento"
            name="evento"
            placeholder="Digite o evento"
          />
        </div>

        <button type="submit" className="btn btn-primary w-100">
          Realizar Login
        </button>
      </form>

      <div className="text-center mt-3">
        <button onClick={navigateToCadastro} className="btn btn-link">
          Não tem uma conta? Cadastre-se aqui!
        </button>
      </div>
    </div>
  )
}