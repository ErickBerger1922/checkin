package backend.backend2.models.repository;

import backend.backend2.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    //Optional<Usuario> findByUsuarioAndSenha(String usuario, String senha);
}
