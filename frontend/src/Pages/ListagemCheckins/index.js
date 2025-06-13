import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

export default function ListaCheckins() {
    const [eventos, setEventos] = useState([]);
    const [eventoSelecionado, setEventoSelecionado] = useState(null);
    const [checkinsSelecionados, setCheckinsSelecionados] = useState([]);
    const token = useSelector((state) => state.auth.token);

    useEffect(() => {
        async function fetchEventos() {
            try {
                const response = await fetch("http://localhost:8080/eventos/empresa", {
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

    async function abrirModalCheckins(evento) {
        if (!evento?.id) {
            console.error("Evento inválido:", evento);
            return;
        }

        setEventoSelecionado(evento);

        try {
            const response = await fetch(`http://localhost:8080/eventos/empresa/${evento.id}/checkins`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json",
                },
            });

            if (!response.ok) throw new Error("Erro ao buscar check-ins.");
            const checkins = await response.json();
            setCheckinsSelecionados(checkins);
        } catch (error) {
            console.error("Erro ao buscar check-ins:", error.message);
            setCheckinsSelecionados([]);
        }
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
							key={evento.id ?? Math.random()}
							className="card d-flex flex-column"
							style={{ width: "400px", boxShadow: "0 4px 8px rgba(0,0,0,0.1)", minHeight: "100%" }}
						>
							<div className="card-body d-flex flex-column">
								<h5 className="card-title mb-3">{evento.nome}</h5>
								<p className="card-text"><strong>Local:</strong> {evento.localizacao}</p>
								<p className="card-text"><strong>Data Início:</strong> {new Date(evento.dataInicioEvento).toLocaleDateString()}</p>
								<p className="card-text"><strong>Data Fim:</strong> {new Date(evento.dataFimEvento).toLocaleDateString()}</p>
								<p className="card-text"><strong>Ativo:</strong> {evento.ativo ? "Sim" : "Não"}</p>
								<p className="card-text"><strong>Empresa:</strong> {evento.empresaResponsavel?.nome ?? "N/A"}</p>

								<div className="d-flex justify-content-center mt-auto pt-3">
									<button
										className="btn btn-info px-4 py-2"
										style={{ fontSize: "1rem", fontWeight: "500", backgroundColor: "#3DB490" }}
										data-bs-toggle="modal"
										data-bs-target="#checkinsModal"
										onClick={() => abrirModalCheckins(evento)}
									>
										Mostrar Check-ins
									</button>
								</div>
							</div>
						</div>
                    ))
                )}
            </div>

            {/* Modal */}
            <div
                className="modal fade"
                id="checkinsModal"
                tabIndex="-1"
                aria-labelledby="checkinsModalLabel"
                aria-hidden="true"
            >
                <div className="modal-dialog modal-lg modal-dialog-centered">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="checkinsModalLabel">
                                Check-ins do Evento: {eventoSelecionado?.nome}
                            </h5>
                            <button
                                type="button"
                                className="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Fechar"
                            ></button>
                        </div>
                        <div className="modal-body">
                            {checkinsSelecionados.length > 0 ? (
                                <ul className="list-group">
                                    {checkinsSelecionados.map((checkin) => (
                                        <li key={checkin.id} className="list-group-item">
                                            <strong>ID:</strong> {checkin.id} <br />
                                            <strong>Nome:</strong> {checkin.nome} <br />
                                            <strong>Email:</strong> {checkin.email}
                                        </li>
                                    ))}
                                </ul>
                            ) : (
                                <p className="text-muted">Nenhum check-in encontrado.</p>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
