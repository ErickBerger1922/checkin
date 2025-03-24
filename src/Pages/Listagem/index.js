import { useEffect, useState } from "react";
import axios from "axios";

export default function ListaCheckin() {
  const [checkins, setCheckins] = useState([]);
  const [erro, setErro] = useState("");

  useEffect(() => {
    async function carregarCheckins() {
      try {
        const response = await axios.get("http://localhost:3001/checkin");
        setCheckins(response.data);
      } catch (error) {
        console.error("Erro ao carregar check-ins:", error);
        setErro("Erro ao carregar os check-ins. Tente novamente.");
      }
    }

    carregarCheckins();
  }, []);

  // Função para excluir um check-in
  const excluirCheckin = async (id) => {
    try {
      await axios.delete(`http://localhost:3001/checkin/${id}`);
      setCheckins(checkins.filter((checkin) => checkin.id !== id)); // Remove o check-in da lista local
      alert("Check-in excluído com sucesso!");
    } catch (error) {
      console.error("Erro ao excluir check-in:", error);
      alert("Erro ao excluir o check-in. Tente novamente.");
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4">Lista de Check-ins</h2>

      {erro && <div className="alert alert-danger">{erro}</div>}

      {checkins.length === 0 ? (
        <p>Nenhum check-in encontrado.</p>
      ) : (
        <table className="table table-striped table-bordered shadow-sm">
          <thead className="table-dark">
            <tr>
              <th className="text-center">Nome do Usuário</th>
              <th className="text-center">Evento</th>
              <th className="text-center">Data do Check-in</th>
              <th className="text-center">Ações</th>
            </tr>
          </thead>
          <tbody>
            {checkins.map((item) => (
              <tr key={item.id}>
                <td className="text-center">{item.usuario || "Não informado"}</td>
                <td className="text-center">{item.evento || "Não informado"}</td>
                <td className="text-center">
                  {item.data
                    ? new Date(item.data).toLocaleString("pt-BR")
                    : "Data não registrada"}
                </td>
                <td className="text-center">
                  <button
                    className="btn btn-danger btn-sm"
                    onClick={() => excluirCheckin(item.id)}
                  >
                    Excluir
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}