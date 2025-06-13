import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

export default function ListaCheckins() {
    const [eventos, setEventos] = useState([]);
    const [checkinsPorEvento, setCheckinsPorEvento] = useState({});
    const [visiveis, setVisiveis] = useState({});
    const token = useSelector((state) => state.auth.token);

    useEffect(() => {
        async function fetchEventos() {
            try {
                const response = await fetch("http://localhost:8080/eventos", {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "application/json",
                    },
                });

                if (!response.ok) throw new Error("Erro HTTP: " + response.status);
                const data = await response.json();
                setEventos(data);
            } catch (error) {
                console.error("Erro ao carregar eventos:", error.message);
            }
        }

        if (token) {
            fetchEventos();
        }
    }, [token]);

    async function fetchCheckins(eventoId) {
        try {
            const response = await fetch(`http://localhost:8080/eventos/${eventoId}/checkins`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json",
                },
            });

            if (!response.ok) throw new Error("Erro ao buscar check-ins.");
            const checkins = await response.json();
            setCheckinsPorEvento(prev => ({ ...prev, [eventoId]: checkins }));
        } catch (error) {
            console.error("Erro ao buscar check-ins:", error.message);
        }
    }

    function toggleCheckins(eventoId) {
        const isVisivel = visiveis[eventoId];
        if (!isVisivel) {
            fetchCheckins(eventoId);
        }
        setVisiveis(prev => ({ ...prev, [eventoId]: !prev[eventoId] }));
    }

    return (
        <div
            className="container mt-5"
            style={{ fontFamily: "'Poppins', sans-serif", color: "#4C4C6C" }}
        >
            <h2 className="mb-4 text-center">Eventos e Check-ins</h2>

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
                            className="card"
                            style={{ width: "400px", boxShadow: "0 4px 8px rgba(0,0,0,0.1)" }}
                        >
                            <div className="card-body">
                                <h5 className="card-title mb-3">{evento.nome}</h5>
                                <p className="card-text"><strong>Local:</strong> {evento.localizacao}</p>
                                <p className="card-text"><strong>Data Início:</strong> {new Date(evento.dataInicioEvento).toLocaleDateString()}</p>
                                <p className="card-text"><strong>Data Fim:</strong> {new Date(evento.dataFimEvento).toLocaleDateString()}</p>
                                <p className="card-text"><strong>Ativo:</strong> {evento.ativo ? "Sim" : "Não"}</p>
                                <p className="card-text"><strong>Empresa:</strong> {evento.empresaResponsavel?.nome ?? "N/A"}</p>

                                <div className="d-flex justify-content-center mt-3">
                                    <button
                                        className="btn btn-info px-4 py-2"
                                        style={{ fontSize: "1rem", fontWeight: "500" }}
                                        onClick={() => toggleCheckins(evento.id)}
                                    >
                                        {visiveis[evento.id] ? "Ocultar Check-ins" : "Mostrar Check-ins"}
                                    </button>
                                </div>

                                {visiveis[evento.id] && (
                                    <div className="mt-3">
                                        <h6>Check-ins:</h6>
                                        {checkinsPorEvento[evento.id]?.length > 0 ? (
                                            <ul className="list-group">
                                                {checkinsPorEvento[evento.id].map((checkin) => (
                                                    <li key={checkin.id} className="list-group-item">
                                                        <strong>Usuário:</strong> {checkin.usuario?.nome ?? "N/A"} <br />
                                                        <strong>Data/Hora:</strong>{" "}
                                                        {new Date(checkin.dataHoraCheckin).toLocaleString()}
                                                    </li>
                                                ))}
                                            </ul>
                                        ) : (
                                            <p className="text-muted">Nenhum check-in encontrado.</p>
                                        )}
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
