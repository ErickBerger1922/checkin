import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

export default function FazerCheckin() {
    const [eventos, setEventos] = useState([]);
    const [mensagem, setMensagem] = useState("");
    const token = useSelector((state) => state.auth.token);

    useEffect(() => {
        async function fetchEventos() {
            try {
                const response = await fetch("http://localhost:8080/eventos", {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });

                if (!response.ok) throw new Error("Erro ao buscar eventos");

                const data = await response.json();
                setEventos(data);
            } catch (error) {
                console.error("Erro:", error);
                setMensagem("Erro ao carregar eventos.");
            }
        }

        if (token) {
            fetchEventos();
        }
    }, [token]);

    async function realizarCheckin(eventoId) {
        try {
            const response = await fetch("http://localhost:8080/checkins", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify({ eventoId }),
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || "Erro ao realizar check-in");
            }

            setMensagem("✅ Check-in realizado com sucesso!");
        } catch (error) {
            console.error("Erro no check-in:", error);
            setMensagem(`❌ ${error.message}`);
        }
    }

    return (
        <div
            className="container mt-5"
            style={{
                fontFamily: "'Poppins', sans-serif",
                color: "#4C4C6C"
            }}
        >
            <h2 className="mb-4 text-center">Escolha o evento e faça seu Check-in</h2>

            {mensagem && (
                <div className="alert alert-info text-center fw-semibold">
                    {mensagem}
                </div>
            )}

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
                    <p className="text-center text-muted">
                        Nenhum evento disponível no momento.
                    </p>
                ) : (
                    eventos.map((evento) => (
                        <div
                            key={evento.id}
                            className="card d-flex flex-column"
                            style={{
                                width: "400px",
                                boxShadow: "0 4px 8px rgba(0,0,0,0.1)",
                                minHeight: "100%",
                            }}
                        >
                            <div className="card-body d-flex flex-column">
                                <h5 className="card-title mb-3">{evento.nome}</h5>
                                <p className="card-text">
                                    <strong>Local:</strong> {evento.localizacao}
                                </p>
                                <p className="card-text">
                                    <strong>Data Início:</strong>{" "}
                                    {new Date(evento.dataInicioEvento).toLocaleDateString()}
                                </p>
                                <p className="card-text">
                                    <strong>Data Fim:</strong>{" "}
                                    {new Date(evento.dataFimEvento).toLocaleDateString()}
                                </p>

                                <div className="d-flex justify-content-center mt-auto pt-3">
                                    <button
                                        className="btn btn-info px-4 py-2"
                                        style={{
                                            fontSize: "1rem",
                                            fontWeight: "500",
                                            backgroundColor: "#3DB490",
                                        }}
                                        onClick={() => realizarCheckin(evento.id)}
                                    >
                                        Realizar check-in
                                    </button>
                                </div>
                            </div>
                        </div>
                    ))
                )}
            </div>
        </div>
    );
}
