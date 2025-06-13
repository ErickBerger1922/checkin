package backend.backend2.application.converters;

import backend.backend2.application.dto.EmpresaResponsavelDto;
import backend.backend2.application.dto.EventoDto;
import backend.backend2.domain.model.evento.Evento;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.infrastructure.entity.EventoJpa;
import backend.backend2.infrastructure.entity.UsuarioJpa;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EventoConverter {

    private final UsuarioConverter usuarioConverter;

    public EventoConverter(UsuarioConverter usuarioConverter) {
        this.usuarioConverter = usuarioConverter;
    }

    public Evento dtoParaDominio(EventoDto dto, Usuario usuario){
        return new Evento(
                null,
                dto.getNome(),
                dto.getLocalizacao(),
                dto.getDataInicioEvento(),
                dto.getDataFimEvento(),
                usuario
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
        eventoJpa.setEmpresaResponsavel(usuarioConverter.dominioParaJpa(evento.getEmpresaResponsavel()));

        return eventoJpa;
    }

    public Evento jpaParaDominio(EventoJpa eventoJpa){
        return new Evento(
                eventoJpa.getId(),
                eventoJpa.getNome(),
                eventoJpa.getLocalizacao(),
                eventoJpa.getDataInicioEvento(),
                eventoJpa.getDataFimEvento(),
                usuarioConverter.jpaParaDominio(eventoJpa.getEmpresaResponsavel())
        );
    }

    public EventoDto dominioParaDto(Evento evento){
        EventoDto dto = new EventoDto();

        dto.setNome(evento.getNome());
        dto.setLocalizacao(evento.getLocalizacao());
        dto.setDataInicioEvento(evento.getDataInicioEvento());
        dto.setDataFimEvento(evento.getDataFimEvento());
        dto.setAtivo(evento.isAtivo());
        dto.setEmpresaResponsavel(new EmpresaResponsavelDto(
                evento.getEmpresaResponsavel().getId(),
                evento.getEmpresaResponsavel().getNome()
        ));

        return dto;
    }
}
