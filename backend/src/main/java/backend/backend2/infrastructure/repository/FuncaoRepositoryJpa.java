package backend.backend2.infrastructure.repository;

import backend.backend2.application.converters.FuncaoConverter;
import backend.backend2.domain.model.funcao.Funcao;
import backend.backend2.domain.repository.FuncaoRepository;
import backend.backend2.infrastructure.entity.FuncaoJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FuncaoRepositoryJpa implements FuncaoRepository {

    @PersistenceContext
    private EntityManager em;

    private final FuncaoConverter funcaoConverter;

    public FuncaoRepositoryJpa(FuncaoConverter funcaoConverter) {
        this.funcaoConverter = funcaoConverter;
    }

    @Override
    public Optional<Funcao> buscarPorFuncao(String funcao) {
        String sql = "SELECT * FROM tb_funcao WHERE authority = :authority";

        try {
            FuncaoJpa funcaoJpa = (FuncaoJpa) em
                    .createNativeQuery(sql, FuncaoJpa.class)
                    .setParameter("authority", funcao)
                    .getSingleResult();

            return Optional.of(funcaoConverter.jpaParaFuncao(funcaoJpa));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
