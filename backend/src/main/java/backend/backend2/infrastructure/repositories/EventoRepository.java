package backend.backend2.infrastructure.repositories;

import backend.backend2.infrastructure.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento,String> {
}


