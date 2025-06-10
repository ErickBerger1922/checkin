package backend.backend2.domain.repository;

import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.infrastructure.entity.UsuarioJpa;

import java.util.Optional;

public interface UsuarioRepository{

    Optional<UsuarioJpa> findByEmail(String email);

    boolean existePorEmail(String email);
}
