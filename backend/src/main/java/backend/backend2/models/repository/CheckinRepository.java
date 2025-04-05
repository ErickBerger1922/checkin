package backend.backend2.models.repository;

import backend.backend2.models.entities.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface CheckinRepository extends JpaRepository<Checkin, Long> {
}
