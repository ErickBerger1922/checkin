package backend.backend2.domain.repository;

import backend.backend2.domain.entities.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface CheckinRepository extends JpaRepository<Checkin, Long> {
}
