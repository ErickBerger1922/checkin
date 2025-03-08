export default function Home() {
  return(
    <div className="container mt-5">
      <h2 className="text-center mb-4">Faça seu checkin para ter acesso ao evento!</h2>
      
      <form className="border p-4 rounded shadow-sm">
        <div className="mb-3">
          <label htmlFor="usuario" className="form-label">
            Usuário
          </label>
          <input
            type="text"
            className="form-control"
            id="usuario"
            name="usuario"
            placeholder="Digite seu nome"
          />
        </div>

        <div className="mb-3">
          <label htmlFor="evento" className="form-label">
            Evento
          </label>
          <input
            type="text"
            className="form-control"
            id="evento"
            name="evento"
            placeholder="Digite o evento"
          />
        </div>

        <button type="submit" className="btn btn-primary w-100">
          Fazer Check-in
        </button>
      </form>
    </div>
  )
}