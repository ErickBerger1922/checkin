package backend.backend2.infrastructure.security;

import backend.backend2.application.dto.UsuarioLogadoDto;
import backend.backend2.application.exception.RecursoNaoEncontradoException;
import backend.backend2.domain.model.funcao.Funcao;
import backend.backend2.infrastructure.entity.FuncaoJpa;
import backend.backend2.infrastructure.entity.UsuarioJpa;
import backend.backend2.infrastructure.repository.UsuarioRepositoryJpa;
import backend.backend2.query.projections.UserDetailsProjection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepositoryJpa usuarioRepositoryJpa;

    public UserDetailsServiceImpl(UsuarioRepositoryJpa usuarioRepository) {
        this.usuarioRepositoryJpa = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = usuarioRepositoryJpa.buscaUsuarioEFuncoesPorEmail(email);
        if(result.size() == 0){
            throw new RecursoNaoEncontradoException("Usuário não encontrado.");
        }

        UsuarioJpa usuarioJpa = new UsuarioJpa();
        usuarioJpa.setEmail(email);
        usuarioJpa.setSenha(result.get(0).getPassword());
        for (UserDetailsProjection projection : result){
            usuarioJpa.adicionaFuncao(new FuncaoJpa(projection.getRoleId(), projection.getAuthority()));
        }

        return usuarioJpa;
    }
}
