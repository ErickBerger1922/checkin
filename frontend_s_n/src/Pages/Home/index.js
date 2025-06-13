import { Link } from "react-router-dom";  // Importando o Link do React Router

export default function Home() {
  return (
    <div className="d-flex justify-content-center align-items-center vh-100" style={{ backgroundColor: "#ffffff" }}>
      <div
        className="text-center py-5 px-4"
        style={{
          backgroundColor: "rgba(255, 255, 255, 0.85)", 
          borderRadius: "10px", 
          boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)", 
          border: "2px solid #ccc", 
          position: "relative",
          top: "-20%", 
        }}
      >
        <h1 className="fw-bold text-dark mb-3" style={{ fontFamily: "'Poppins', sans-serif", fontSize: "2.5rem" }}>
          Bem-vindo ao site Checkout Online!
        </h1>
        <p className="fs-4 text-muted mb-4" style={{ fontFamily: "'Roboto', sans-serif" }}>
          "Facilitando o futuro dos eventos. Com nossa plataforma, o check-in é rápido, simples e sem complicação, para que você possa focar no que realmente importa: a experiência do evento."
        </p>
      </div>
    </div>
  );
}
