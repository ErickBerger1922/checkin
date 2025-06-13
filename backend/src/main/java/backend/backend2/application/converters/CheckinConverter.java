package backend.backend2.application.converters;

import backend.backend2.application.dto.CheckinDto;
import backend.backend2.application.dto.CheckinListagemDto;
import backend.backend2.domain.model.checkin.Checkin;
import backend.backend2.domain.model.evento.Evento;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.infrastructure.entity.CheckinJpa;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CheckinConverter {

    private final UsuarioConverter usuarioConverter;
    private final EventoConverter eventoConverter;

    public CheckinConverter(UsuarioConverter usuarioConverter, EventoConverter eventoConverter) {
        this.usuarioConverter = usuarioConverter;
        this.eventoConverter = eventoConverter;
    }

    public Checkin dtoParaDominio(CheckinDto dto, Usuario usuario, Evento evento){
        return new Checkin(
                dto.getId(),
                LocalDateTime.now(),
                usuario,
                evento
        );
    }

    public CheckinJpa dominioParaJpa(Checkin checkin){
        CheckinJpa checkinJpa = new CheckinJpa();

        checkinJpa.setId(checkin.getId());
        checkinJpa.setDataHora(checkin.getDataHora());
        checkinJpa.setUsuario(usuarioConverter.dominioParaJpa(checkin.getUsuario()));
        checkinJpa.setEvento(eventoConverter.dominioParaJpa(checkin.getEvento()));

        return checkinJpa;
    }

    public Checkin jpaParaDominio(CheckinJpa checkinJpa){
        return new Checkin(
                checkinJpa.getId(),
                checkinJpa.getDataHora(),
                usuarioConverter.jpaParaDominio(checkinJpa.getUsuario()),
                eventoConverter.jpaParaDominio(checkinJpa.getEvento())
        );
    }

    public CheckinDto dominioParaDto(Checkin checkin){
        CheckinDto dto = new CheckinDto();

        dto.setId(checkin.getId());
        dto.setDataHora(checkin.getDataHora());
        dto.setUsuarioId(checkin.getUsuario().getId());
        dto.setEventoId(checkin.getEvento().getId());

        return dto;
    }

    public CheckinListagemDto checkinParaListagemDto(Checkin checkin){
        CheckinListagemDto listagemDto = new CheckinListagemDto();

        listagemDto.setDataHora(checkin.getDataHora());
        listagemDto.setNomeEvento(checkin.getEvento().getNome());
        listagemDto.setNomeUsuario(checkin.getUsuario().getNome());

        return listagemDto;
    }
}
