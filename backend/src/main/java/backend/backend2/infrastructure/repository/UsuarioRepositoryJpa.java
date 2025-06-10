package backend.backend2.infrastructure.repository;

import backend.backend2.application.converters.UsuarioConverter;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.domain.repository.UsuarioRepository;
import backend.backend2.infrastructure.entity.UsuarioJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepositoryJpa implements UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    private final UsuarioConverter usuarioConverter;

    public UsuarioRepositoryJpa(UsuarioConverter usuarioConverter) {
        this.usuarioConverter = usuarioConverter;
    }

    @Override
    public Optional<UsuarioJpa> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public boolean existePorEmail(String email) {
        String sql = "SELECT EXISTS(SELECT 1 FROM tb_usuario WHERE email = :email)";

        Boolean existe = (Boolean) em
                .createNativeQuery(sql)
                .setParameter("email", email)
                .getSingleResult();

        return Boolean.TRUE.equals(existe);
    }
}
