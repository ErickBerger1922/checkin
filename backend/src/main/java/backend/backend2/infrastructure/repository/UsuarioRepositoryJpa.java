package backend.backend2.infrastructure.repository;

import backend.backend2.application.converters.UsuarioConverter;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.domain.repository.UsuarioRepository;
import backend.backend2.infrastructure.entity.UsuarioJpa;
import backend.backend2.query.projections.UserDetailsProjection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepositoryJpa implements UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    private final UsuarioConverter usuarioConverter;

    public UsuarioRepositoryJpa(UsuarioConverter usuarioConverter) {
        this.usuarioConverter = usuarioConverter;
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        UsuarioJpa usuarioJpa = em.find(UsuarioJpa.class, id);
        if (usuarioJpa == null) {
            return Optional.empty();
        }
        return Optional.of(usuarioConverter.jpaParaDominio(usuarioJpa));
    }

    @Override
    @Transactional
    public Optional<Usuario> buscaPorEmail(String email) {
        try {
            UsuarioJpa usuarioJpa = (UsuarioJpa) em.createNativeQuery(
                            "SELECT * FROM tb_usuario WHERE email = :email", UsuarioJpa.class)
                    .setParameter("email", email)
                    .getSingleResult();

            return Optional.of(usuarioConverter.jpaParaDominio(usuarioJpa));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public List<UserDetailsProjection> buscaUsuarioEFuncoesPorEmail(String email) {
        String sql = """
                SELECT tb_usuario.email AS username, tb_usuario.senha, tb_funcao.id AS funcaoId, tb_funcao.authority 
                FROM tb_usuario 
                INNER JOIN tb_usuario_funcao ON tb_usuario.id = tb_usuario_funcao.usuario_id 
                INNER JOIN tb_funcao ON tb_funcao.id = tb_usuario_funcao.funcao_id 
                WHERE tb_usuario.email = :email 
                """;

        List<Object[]> results = (List<Object[]>) em
                .createNativeQuery(sql)
                .setParameter("email", email)
                .getResultList();

        return results.stream().map(row -> new UserDetailsProjection() {
            @Override
            public String getUsername() {
                return (String) row[0];
            }

            @Override
            public String getPassword() {
                return (String) row[1];
            }

            @Override
            public Long getRoleId() {
                return ((Number) row[2]).longValue();
            }

            @Override
            public String getAuthority() {
                return (String) row[3];
            }
        }).collect(Collectors.toList());
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

    @Override
    @Transactional
    public Usuario salvar(Usuario usuario) {
        UsuarioJpa usuarioJpa = usuarioConverter.dominioParaJpa(usuario);

        if (usuarioJpa.getId() == null) {
            em.persist(usuarioJpa);
        } else {
            usuarioJpa = em.merge(usuarioJpa);
        }

        return usuarioConverter.jpaParaDominio(usuarioJpa);
    }
}