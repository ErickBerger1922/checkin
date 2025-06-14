import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

export default function ListaEventos() {
  const [eventos, setEventos] = useState([]);
  const token = useSelector((state) => state.auth.token);
  const roles = useSelector((state) => state.auth.roles);
  const usuarioId = useSelector((state) => state.auth.id);

  useEffect(() => {
    async function fetchEventos() {
      const url = roles.includes("ROLE_ADMIN")
        ? "http://localhost:8080/eventos"
        : "http://localhost:8080/eventos/empresa";

      try {
        const response = await fetch(url, {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          throw new Error(`Erro HTTP: ${response.status}`);
        }

        const data = await response.json();
        setEventos(data);
      } catch (error) {
        console.error("Erro ao carregar eventos:", error.message);
      }
    }

    if (token) {
      fetchEventos();
    }
  }, [token, roles]);

  function podeExcluir(evento) {
    console.log("Verificando permissão para excluir evento:");
    console.log("  roles:", roles);
    console.log("  usuarioId:", usuarioId);
    console.log("  evento.empresaResponsavel?.id:", evento.empresaResponsavel?.id);
    console.log("  roles.includes('ROLE_ENTERPRISE'):", roles.includes("ROLE_ENTERPRISE"));
    console.log("  usuarioId === evento.empresaResponsavel?.id:", usuarioId === evento.empresaResponsavel?.id);

    if (roles.includes("ROLE_ADMIN")) {
      return true;
    }

    return (
      roles.includes("ROLE_ENTERPRISE") &&
      usuarioId === evento.empresaResponsavel?.id
    );
  }

  async function handleExcluirEvento(id) {
    if (!window.confirm("Tem certeza que deseja excluir este evento?")) return;

    try {
      const response = await fetch(`http://localhost:8080/eventos/${id}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (!response.ok) throw new Error("Erro ao excluir evento.");

      setEventos((prev) => prev.filter((e) => e.id !== id));
    } catch (error) {
      alert("Erro ao excluir evento: " + error.message);
    }
  }

  return (
    <div
      className="container mt-5"
      style={{ fontFamily: "'Poppins', sans-serif", color: "#4C4C6C" }}
    >
      <h2 className="mb-4 text-center">Lista de Eventos</h2>

      <div
        className="d-flex flex-wrap gap-4 justify-content-center"
        style={{
          backgroundColor: "#f9f9f9",
          padding: "20px",
          borderRadius: "8px",
          boxShadow: "0 2px 5px rgba(0,0,0,0.1)",
        }}
      >
        {eventos.length === 0 ? (
          <p className="text-center text-muted">Nenhum evento encontrado.</p>
        ) : (
          eventos.map((evento) => (
            <div
              key={evento.id}
              className="card d-flex flex-column"
              style={{
                width: "380px",
                boxShadow: "0 4px 8px rgba(0,0,0,0.1)",
                minHeight: "100%",
              }}
            >
              <div className="card-body d-flex flex-column">
                <h5 className="card-title mb-3">{evento.nome}</h5>
                <p className="card-text mb-1">
                  <strong>Localização:</strong> {evento.localizacao}
                </p>
                <p className="card-text mb-1">
                  <strong>Data Início:</strong>{" "}
                  {new Date(evento.dataInicioEvento).toLocaleDateString()}
                </p>
                <p className="card-text mb-1">
                  <strong>Data Fim:</strong>{" "}
                  {new Date(evento.dataFimEvento).toLocaleDateString()}
                </p>
                <p className="card-text mb-1">
                  <strong>Ativo:</strong> {evento.ativo ? "Sim" : "Não"}
                </p>
                <p className="card-text mb-3">
                  <strong>Empresa Responsável:</strong>{" "}
                  {evento.empresaResponsavel?.nome ?? "N/A"}
                </p>

                {podeExcluir(evento) && (
                  <div className="d-flex justify-content-center mt-auto pt-3">
                    <button
                      className="btn btn-danger px-4 py-2"
                      style={{
                        fontSize: "1rem",
                        fontWeight: "500",
                        borderRadius: "6px",
                      }}
                      onClick={() => handleExcluirEvento(evento.id)}
                    >
                      Excluir
                    </button>
                  </div>
                )}
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
}
