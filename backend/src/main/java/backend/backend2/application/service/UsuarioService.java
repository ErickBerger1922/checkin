package backend.backend2.application.service;

import backend.backend2.application.converters.UsuarioConverter;
import backend.backend2.application.dto.UsuarioDto;
import backend.backend2.domain.exception.ConflictException;
import backend.backend2.domain.model.funcao.Funcao;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.infrastructure.repository.UsuarioRepositoryJpa;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UsuarioService {

    private final UsuarioRepositoryJpa usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final FuncaoService funcaoService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepositoryJpa usuarioRepository, UsuarioConverter usuarioConverter, FuncaoService funcaoService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioConverter = usuarioConverter;
        this.funcaoService = funcaoService;
        this.passwordEncoder = passwordEncoder;
    }

    /*@Transactional(propagation = Propagation.REQUIRED)
    public UsuarioDto salvaUsuario(UsuarioDto dto) {
        emailExiste(dto.getEmail());
        dto.setSenha(passwordEncoder.encode(dto.getSenha()));

        Set<Funcao> funcoes = funcaoService.buscaPorNomes(dto.getFuncoes());
        Usuario usuario = usuarioConverter.paraEntidade(dto, funcoes);

        return usuarioConverter.paraDto(usuarioRepository.save(entidade));
    }

    private void emailExiste(String email){
        try{
            if(verificaSeEmailExiste(email)){
                throw new ConflictException("Email já existe "+ email);
            }
        } catch(ConflictException e){
            throw new ConflictException("Email já existe " + email);
        }
    }

    private boolean verificaSeEmailExiste(String email){
        return usuarioRepository.existePorEmail(email);
    }*/
}
