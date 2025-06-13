package backend.backend2.application.service;

import backend.backend2.application.converters.CheckinConverter;
import backend.backend2.application.dto.CheckinDto;
import backend.backend2.application.dto.CheckinListagemDto;
import backend.backend2.application.exception.RecursoNaoEncontradoException;
import backend.backend2.domain.exception.ConflitoException;
import backend.backend2.domain.model.checkin.Checkin;
import backend.backend2.domain.model.evento.Evento;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.domain.repository.CheckinRepository;
import backend.backend2.domain.repository.EventoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CheckinService {

    private final CheckinRepository checkinRepository;
    private final CheckinConverter checkinConverter;
    private final UsuarioService usuarioService;
    private final EventoService eventoService;

    public CheckinService(CheckinRepository checkinRepository, CheckinConverter checkinConverter, UsuarioService usuarioService, EventoService eventoService) {
        this.checkinRepository = checkinRepository;
        this.checkinConverter = checkinConverter;
        this.usuarioService = usuarioService;
        this.eventoService = eventoService;
    }

    @Transactional(readOnly = true)
    public List<CheckinListagemDto> listaCheckins() {
        List<Checkin> checkins = checkinRepository.listarTodos();
        return checkins.stream().map(checkinConverter::checkinParaListagemDto).toList();
    }

    @Transactional
    public CheckinDto salvaCheckin(CheckinDto dto){
        Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId());
        Evento evento = eventoService.buscarPorId(dto.getEventoId());
        Checkin checkin = checkinConverter.dtoParaDominio(dto, usuario, evento);

        checkinRepository.salvar(checkin);
        eventoService.vinculaUsuarioAoEvento(evento, usuario);

        return checkinConverter.dominioParaDto(checkin);
    }

    /*@Transactional
    public List<CheckinDto> buscarCheckinsPorEvento(Long eventoId) {
        List<Checkin> checkins = checkinRepository.buscarCheckinsPorEvento(eventoId);
        return checkins.stream()
                .map(CheckinDto::)
                .collect(Collectors.toList());
    }*/


    @Transactional(propagation = Propagation.REQUIRED)
    public void deletaCheckin(Long id){
        Checkin checkin = checkinRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Checkin não encontrado com o id "+ id));

        try{
            checkinRepository.deletar(checkin);
        }catch(DataIntegrityViolationException ex){
            throw new ConflitoException("Checkin está associado a outras entidades e não pode ser removido");
        }
    }
}
