package backend.backend2.domain.model.checkin;

import backend.backend2.domain.model.evento.Evento;
import backend.backend2.infrastructure.entity.UsuarioJpa;

import java.time.LocalDateTime;

public class Checkin {

    private Long id;
    private LocalDateTime dataHora;

    private UsuarioJpa usuario;

    private Evento evento;
}
