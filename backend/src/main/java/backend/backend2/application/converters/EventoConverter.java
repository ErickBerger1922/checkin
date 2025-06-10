package backend.backend2.application.converters;

import backend.backend2.application.dto.EventoDto;
import backend.backend2.domain.model.evento.Evento;
import backend.backend2.infrastructure.entity.EventoJpa;
import org.springframework.stereotype.Component;

@Component
public class EventoConverter {

    public Evento dtoParaDominio(EventoDto dto){
        return new Evento(
            dto.getId(),
            dto.getNome(),
            dto.getLocalizacao(),
            dto.getDataInicioEvento(),
            dto.getDataFimEvento()
        );
    }

    public EventoJpa dominioParaJpa(Evento evento){
        EventoJpa eventoJpa = new EventoJpa();

        eventoJpa.setId(evento.getId());
        eventoJpa.setNome(evento.getNome());
        eventoJpa.setLocalizacao(evento.getLocalizacao());
        eventoJpa.setDataInicioEvento(evento.getDataInicioEvento());
        eventoJpa.setDataFimEvento(evento.getDataFimEvento());
        eventoJpa.setAtivo(evento.isAtivo());

        return eventoJpa;
    }

    public Evento jpaParaDominio(EventoJpa eventoJpa){
        return new Evento(
                eventoJpa.getId(),
                eventoJpa.getNome(),
                eventoJpa.getLocalizacao(),
                eventoJpa.getDataInicioEvento(),
                eventoJpa.getDataFimEvento()
        );
    }

    public EventoDto dominioParaDto(Evento evento){
        EventoDto dto = new EventoDto();

        dto.setNome(evento.getNome());
        dto.setLocalizacao(evento.getLocalizacao());
        dto.setDataInicioEvento(evento.getDataInicioEvento());
        dto.setDataFimEvento(evento.getDataFimEvento());
        dto.setAtivo(evento.isAtivo());

        return dto;
    }
}
