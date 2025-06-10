package backend.backend2.infrastructure.repository;

import backend.backend2.domain.model.checkin.Checkin;
import backend.backend2.domain.repository.CheckinRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CheckinRepositoryJpa implements CheckinRepository {

    @Override
    public Optional<Checkin> buscarPorId(Long id) {
        return Optional.empty();
    }
}
