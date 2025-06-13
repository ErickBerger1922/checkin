package backend.backend2.application.service;

import backend.backend2.application.converters.EventoConverter;
import backend.backend2.application.dto.EventoDto;
import backend.backend2.application.exception.RecursoNaoEncontradoException;
import backend.backend2.domain.exception.ConflitoException;
import backend.backend2.domain.model.evento.Evento;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.domain.repository.EventoRepository;
import backend.backend2.domain.repository.UsuarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EventoConverter eventoConverter;
    private final UsuarioService usuarioService;

    public EventoService(EventoRepository eventoRepository, UsuarioRepository usuarioRepository, EventoConverter eventoConverter, UsuarioService usuarioService) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
        this.eventoConverter = eventoConverter;
        this.usuarioService = usuarioService;
    }

    @Transactional(readOnly = true)
    public Evento buscarPorId(Long id){
        return eventoRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Evento não encontrado com o id "+ id));
    }

    @Transactional(readOnly = true)
    public List<EventoDto> listaEventos() {
        return eventoRepository.listarTodos().stream().map(eventoConverter::dominioParaDto).toList();
    }

    @Transactional
    public EventoDto salvaEvento(EventoDto dto){
        Usuario usuario = usuarioService.usuarioAutenticado();
        Evento evento = eventoConverter.dtoParaDominio(dto, usuario);
        return eventoConverter.dominioParaDto(eventoRepository.salvar(evento));
    }

    @Transactional
    public void deletaEvento(Long id){
        Evento evento = eventoRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Evento não encontrado com o id "+ id));

        try{
            eventoRepository.deletar(evento);
        }catch(DataIntegrityViolationException ex){
            throw new ConflitoException("Evento está associado a outras entidades e não pode ser removido");
        }
    }

    @Transactional
    public void vinculaUsuarioAoEvento(Evento evento, Usuario usuario){
        evento.adicionarUsuario(usuario);
        eventoRepository.salvar(evento);
    }
}
