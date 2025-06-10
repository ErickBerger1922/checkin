package backend.backend2.infrastructure.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_checkin")
public class CheckinJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioJpa usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private EventoJpa evento;

    public CheckinJpa() {
    }

    public CheckinJpa(Long id, LocalDateTime dataHora, UsuarioJpa usuario, EventoJpa evento) {
        this.id = id;
        this.dataHora = dataHora;
        this.usuario = usuario;
        this.evento = evento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public UsuarioJpa getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioJpa usuario) {
        this.usuario = usuario;
    }

    public EventoJpa getEvento() {
        return evento;
    }

    public void setEvento(EventoJpa evento) {
        this.evento = evento;
    }
}
