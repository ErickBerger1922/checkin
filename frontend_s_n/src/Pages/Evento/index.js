import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

export default function CadastroEvento() {
	const navigate = useNavigate();

	const token = useSelector(state => state.auth.token);

	const [nome, setNome] = useState("");
	const [localizacao, setLocalizacao] = useState("");
	const [dataInicioEvento, setDataInicioEvento] = useState("");
	const [dataFimEvento, setDataFimEvento] = useState("");

	function formatarDataParaEnvio(dataStr) {	
	if (dataStr.length === 10) {
		return dataStr + "T00:00:00";
	}
	
	if (dataStr.length === 16) {
		return dataStr + ":00";
	}
		return dataStr;
	}

	async function handleSubmit(e) {
		e.preventDefault();

		const agora = new Date();
		const inicio = new Date(dataInicioEvento);
		const fim = new Date(dataFimEvento);

		if (inicio < agora) {
			alert("A data e hora de início não podem ser no passado.");
			return;
		}

		if (fim < inicio) {
			alert("A data e hora de fim deve ser igual ou maior que a data e hora de início.");
			return;
		}

		const novoEvento = {
			nome,
			localizacao,
			dataInicioEvento: formatarDataParaEnvio(dataInicioEvento),
			dataFimEvento: formatarDataParaEnvio(dataFimEvento),
		};

		try {
			const response = await axios.post(
				"http://localhost:8080/eventos",
				novoEvento,
				{
					headers: {
						Authorization: `Bearer ${token}`
					}
				}
			);
			if (response.status === 201 || response.status === 200) {
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
		<h2
			className="text-center mb-4"
			style={{
				fontFamily: "'Poppins', sans-serif",
				color: "#4C4C6C",
				fontSize: "2rem",
				fontWeight: "600",
			}}
		>
		Cadastro de Evento
		</h2>

		<form
			onSubmit={handleSubmit}
			className="border p-5 rounded shadow-sm"
			style={{
			backgroundColor: "#f9f9f9",
			boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
		}}
		>
			<div className="row mb-4">
				<div className="col-md-6">
					<label
						htmlFor="nomeEvento"
						className="form-label"
						style={{ fontWeight: "600", fontSize: "1.1rem" }}
					>Nome do Evento</label>
					<input
						type="text"
						className="form-control"
						id="nomeEvento"
						value={nome}
						onChange={(e) => setNome(e.target.value)}
						required
						style={{ borderRadius: "10px", borderColor: "#ced4da" }}
					/>
				</div>

				<div className="col-md-6">
					<label
					htmlFor="localizacao"
					className="form-label"
					style={{ fontWeight: "600", fontSize: "1.1rem" }}
					>
					Localização
					</label>
					<input
					type="text"
					className="form-control"
					id="localizacao"
					value={localizacao}
					onChange={(e) => setLocalizacao(e.target.value)}
					required
					style={{ borderRadius: "10px", borderColor: "#ced4da" }}
					/>
				</div>
			</div>

			<div className="row mb-4">
				<div className="col-md-6">
					<label
						htmlFor="dataInicioEvento"
						className="form-label"
						style={{ fontWeight: "600", fontSize: "1.1rem" }}
					>Data e Hora Início do Evento</label>
					<input
						type="datetime-local"
						className="form-control"
						id="dataInicioEvento"
						value={dataInicioEvento}
						onChange={(e) => setDataInicioEvento(e.target.value)}
						required
						style={{ borderRadius: "10px", borderColor: "#ced4da" }}
					/>
				</div>

				<div className="col-md-6">
					<label
						htmlFor="dataFimEvento"
						className="form-label"
						style={{ fontWeight: "600", fontSize: "1.1rem" }}
					>Data e Hora Fim do Evento</label>
					<input
						type="datetime-local"
						className="form-control"
						id="dataFimEvento"
						value={dataFimEvento}
						onChange={(e) => setDataFimEvento(e.target.value)}
						required
						style={{ borderRadius: "10px", borderColor: "#ced4da" }}
					/>
				</div>
			</div>

			<button
				type="submit"
				className="btn btn-success w-100 py-2"
				style={{ fontSize: "1.2rem", fontWeight: "600", borderRadius: "8px" }}
			>Cadastrar Evento</button>
		</form>
		</div>
	);
}
