package backend.backend2.domain.repository;

import backend.backend2.domain.model.evento.Evento;

import java.util.List;
import java.util.Optional;

public interface EventoRepository {

    List<Evento> listarTodos();

    Optional<Evento> buscarPorId(Long id);

    Evento salvar(Evento evento);

    void deletar(Evento evento);
}