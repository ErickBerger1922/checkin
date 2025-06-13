package backend.backend2.domain.model.checkin;

import backend.backend2.domain.model.evento.Evento;
import backend.backend2.domain.model.usuario.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;

public class Checkin {

    private Long id;
    private LocalDateTime dataHora;

    private Usuario usuario;

    private Evento evento;

    public Checkin(Long id, LocalDateTime dataHora, Usuario usuario, Evento evento) {
        this.id = id;
        this.dataHora = dataHora;
        this.usuario = usuario;
        this.evento = evento;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Evento getEvento() {
        return evento;
    }
}
