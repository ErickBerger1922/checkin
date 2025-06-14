package backend.backend2.application.service;

import backend.backend2.application.converters.EventoConverter;
import backend.backend2.application.dto.EventoDto;
import backend.backend2.application.dto.UsuarioCheckinDto;
import backend.backend2.application.exception.RecursoNaoEncontradoException;
import backend.backend2.domain.exception.ConflitoException;
import backend.backend2.domain.model.evento.Evento;
import backend.backend2.domain.model.shared.enums.TipoUsuario;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.domain.repository.EventoRepository;
import backend.backend2.infrastructure.repository.EventoJpaSpringRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final EventoJpaSpringRepository eventoSpringRepository;
    private final EventoConverter eventoConverter;
    private final UsuarioService usuarioService;

    public EventoService(EventoRepository eventoRepository, EventoJpaSpringRepository eventoSpringRepository, EventoConverter eventoConverter, UsuarioService usuarioService) {
        this.eventoRepository = eventoRepository;
        this.eventoSpringRepository = eventoSpringRepository;
        this.eventoConverter = eventoConverter;
        this.usuarioService = usuarioService;
    }

    @Transactional(readOnly = true)
    public Evento buscarPorId(Long id){
        return eventoRepository.findByEmpresaResponsavel_Id(id);
    }

    @Transactional(readOnly = true)
    public List<EventoDto> listaEventos() {
        List<Evento> eventos = eventoSpringRepository.findAll().stream().map(eventoConverter::jpaParaDominio).toList();
        return eventos.stream().map(eventoConverter::dominioParaDto).toList();
    }

    @Transactional(readOnly = true)
    public List<EventoDto> listaEventosConformeEmpresa(Long empresaId) {
        Usuario usuario = usuarioService.buscarPorId(empresaId);
        List<EventoDto> eventos = new ArrayList<>();
        if(usuario.getTipoUsuario() == TipoUsuario.EMPRESA){
            eventos = eventoRepository.findAllByEmpresaResponsavel_Id(empresaId).stream().map(eventoConverter::dominioParaDto).toList();
        }
        return eventos;
    }

    @Transactional(readOnly = true)
    public List<UsuarioCheckinDto> buscarUsuariosComCheckinNoEvento(Long eventoId) {
        List<Usuario> usuarios = eventoRepository.buscarUsuariosComCheckinNoEvento(eventoId);
        return usuarios.stream().map(usuario -> new UsuarioCheckinDto(
                                                                usuario.getId(),
                                                                usuario.getNome(),
                                                                usuario.getEmail().getEndereco()))
                                                                .collect(Collectors.toList()
                                                            );
    }

    @Transactional
    public EventoDto salvaEvento(EventoDto dto){
        Usuario usuario = usuarioService.usuarioAutenticado();
        Evento evento = eventoConverter.dtoParaDominio(dto, usuario);
        return eventoConverter.dominioParaDto(eventoRepository.salvar(evento));
    }

    @Transactional
    public void deletaEvento(Long id){
        if(!eventoRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Evento não encontrado com o id "+ id);
        }
        try{
            eventoRepository.deleteById(id);
        }catch(DataIntegrityViolationException ex){
            throw new ConflitoException("Evento está associado a outras entidades e não pode ser removido");
        }
    }

    @Transactional
    public void vinculaUsuarioAoEvento(Long eventoId, Long usuarioId){
        eventoRepository.adicionaUsuarioNoEvento(eventoId, usuarioId);
    }

    @Transactional
    public void desvinculaUsuarioDoEvento(Long eventoId, Long usuarioId){
        Evento evento = eventoRepository.findById(eventoId);

        evento.getUsuarios().clear();

        eventoRepository.desvinculaUsuarioDoEvento(eventoId, usuarioId);
    }
}
