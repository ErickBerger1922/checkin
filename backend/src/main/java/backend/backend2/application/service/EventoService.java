package backend.backend2.application.service;

import backend.backend2.application.converters.EventoConverter;
import backend.backend2.application.dto.EventoDto;
import backend.backend2.application.exception.ResourceNotFoundException;
import backend.backend2.domain.exception.ConflictException;
import backend.backend2.domain.model.evento.Evento;
import backend.backend2.domain.repository.EventoRepository;
import backend.backend2.infrastructure.exception.DatabaseException;
import backend.backend2.infrastructure.repository.EventoRepositoryJpa;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final EventoConverter eventoConverter;

    public EventoService(EventoRepositoryJpa eventoRepository, EventoConverter eventoConverter) {
        this.eventoRepository = eventoRepository;
        this.eventoConverter = eventoConverter;
    }

    @Transactional(readOnly = true)
    public List<EventoDto> listaEventos() {
        return eventoRepository.listarTodos().stream().map(eventoConverter::dominioParaDto).toList();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public EventoDto salvaEvento(EventoDto dto){
        Evento evento = eventoConverter.dtoParaDominio(dto);
        return eventoConverter.dominioParaDto(eventoRepository.salvar(evento));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletaEvento(Long id){
        Evento evento = eventoRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com o id "+ id));

        try{
            eventoRepository.deletar(evento);
        }catch(DataIntegrityViolationException ex){
            throw new ConflictException("Evento está associado a outras entidades e não pode ser removido");
        }
    }
}
