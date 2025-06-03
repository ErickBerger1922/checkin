package backend.backend2.infrastructure.repositories;

import backend.backend2.infrastructure.entities.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface CheckinRepository extends JpaRepository<Checkin, Long> {
}
