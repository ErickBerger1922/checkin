package backend.backend2.business.services;

import backend.backend2.business.converters.UsuarioConverter;
import backend.backend2.business.dto.UsuarioDto;
import backend.backend2.infrastructure.entities.Funcao;
import backend.backend2.infrastructure.entities.Usuario;
import backend.backend2.infrastructure.exceptions.ConflictException;
import backend.backend2.infrastructure.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioConverter usuarioConverter;

    @Autowired
    private FuncaoService funcaoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDto salvaUsuario(UsuarioDto dto) {
        emailExiste(dto.getEmail());
        dto.setSenha(passwordEncoder.encode(dto.getSenha()));

        Set<Funcao> funcoes = funcaoService.buscaPorNomes(dto.getFuncoes());
        Usuario entidade = usuarioConverter.paraEntidade(dto, funcoes);

        return usuarioConverter.paraDto(usuarioRepository.save(entidade));
    }

    public void emailExiste(String email){
        try{
            boolean existe = verificaSeEmailExiste(email);
            if(existe){
                throw new ConflictException("Email já existe "+ email);
            }
        } catch(ConflictException e){
            throw new ConflictException("Email já existe " + email);
        }
    }

    public boolean verificaSeEmailExiste(String email){
        return usuarioRepository.existsByEmail(email);
    }
}
