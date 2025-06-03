package backend.backend2.infrastructure.repositories;

import backend.backend2.infrastructure.entities.Funcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Long> {

    Optional<Funcao> findByAuthority(String nomeDaFuncao);
}
