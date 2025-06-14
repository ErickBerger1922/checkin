package backend.backend2.infrastructure.repository;

import backend.backend2.infrastructure.entity.EventoJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoJpaSpringRepository extends JpaRepository<EventoJpa, Long> {

    Optional<EventoJpa> findById(Long id);

    Optional<EventoJpa> findByEmpresaResponsavel_Id(Long empresaId);

    List<EventoJpa> findAllByEmpresaResponsavel_Id(Long empresaId);

    boolean existsById(Long id);

    void deleteById(Long id);
}
