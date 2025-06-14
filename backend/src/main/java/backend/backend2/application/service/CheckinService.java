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
    private final EventoRepository eventoRepository;
    private final EventoService eventoService;

    public CheckinService(CheckinRepository checkinRepository, CheckinConverter checkinConverter, UsuarioService usuarioService, EventoRepository eventoRepository, EventoService eventoService) {
        this.checkinRepository = checkinRepository;
        this.checkinConverter = checkinConverter;
        this.usuarioService = usuarioService;
        this.eventoRepository = eventoRepository;
        this.eventoService = eventoService;
    }

    @Transactional(readOnly = true)
    public List<CheckinListagemDto> listaCheckins() {
        List<Checkin> checkins = checkinRepository.listarTodos();
        return checkins.stream().map(checkinConverter::checkinParaListagemDto).toList();
    }

    @Transactional
    public CheckinDto salvaCheckin(CheckinDto dto){
        Evento evento = eventoRepository.findById(dto.getEventoId());
        Usuario usuario = usuarioService.usuarioAutenticado();

        eventoService.vinculaUsuarioAoEvento(evento.getId(), usuario.getId());

        Checkin checkin = checkinConverter.dtoParaDominio(dto, usuario, evento);

        checkinRepository.salvar(checkin);

        return checkinConverter.dominioParaDto(checkin);
    }

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
