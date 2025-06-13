package backend.backend2.application.service;

import backend.backend2.application.converters.UsuarioConverter;
import backend.backend2.application.dto.UsuarioDto;
import backend.backend2.application.dto.UsuarioLogadoDto;
import backend.backend2.application.exception.RecursoNaoEncontradoException;
import backend.backend2.domain.exception.ConflitoException;
import backend.backend2.domain.model.evento.Evento;
import backend.backend2.domain.model.funcao.Funcao;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.domain.repository.UsuarioRepository;
import backend.backend2.infrastructure.entity.UsuarioJpa;
import backend.backend2.infrastructure.repository.UsuarioRepositoryJpa;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final FuncaoService funcaoService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepositoryJpa usuarioRepository, UsuarioConverter usuarioConverter, FuncaoService funcaoService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioConverter = usuarioConverter;
        this.funcaoService = funcaoService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id){
        return usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario não encontrado com o id "+ id));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UsuarioDto salvaUsuario(UsuarioDto dto) {
        emailExiste(dto.getEmail());
        dto.setSenha(passwordEncoder.encode(dto.getSenha()));

        Set<Funcao> funcoes = funcaoService.buscaPorNomes(dto.getFuncoes());
        Usuario usuario = usuarioConverter.dtoParaDominio(dto, funcoes);

        return usuarioConverter.dominioParaDto(usuarioRepository.salvar(usuario));
    }

    private void emailExiste(String email){
        try{
            if(verificaSeEmailExiste(email)){
                throw new ConflitoException("Email já existe "+ email);
            }
        } catch(ConflitoException e){
            throw new ConflitoException("Email já existe " + email);
        }
    }

    private boolean verificaSeEmailExiste(String email){
        return usuarioRepository.existePorEmail(email);
    }

    @Transactional(readOnly = true)
    public UsuarioLogadoDto getMe(){
        Usuario usuario = usuarioAutenticado();
        UsuarioLogadoDto usuarioLogado = new UsuarioLogadoDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail().getEndereco()
        );
        usuarioLogado.getFuncoes().addAll(
                usuario.getFuncoes().stream().map(Funcao::getAuthority).collect(Collectors.toSet())
        );
        return usuarioLogado;
    }

    protected Usuario usuarioAutenticado(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
        String email = jwtPrincipal.getClaim("username");

        return usuarioRepository.buscaPorEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com o e-mail "+ email));
    }

}
