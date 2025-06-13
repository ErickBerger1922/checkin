package backend.backend2.domain.repository;

import backend.backend2.domain.model.checkin.Checkin;

import java.util.List;
import java.util.Optional;

public interface CheckinRepository{

    Optional<Checkin> buscarPorId(Long id);

    List<Checkin> listarTodos();

    Checkin salvar(Checkin checkin);

    void deletar(Checkin checkin);
}
