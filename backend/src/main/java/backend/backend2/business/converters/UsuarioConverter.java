package backend.backend2.business.converters;

import backend.backend2.business.dto.UsuarioDto;
import backend.backend2.infrastructure.entities.Funcao;
import backend.backend2.infrastructure.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UsuarioConverter {

    public Usuario paraEntidade(UsuarioDto dto, Set<Funcao> funcoes){
        Usuario entidade = new Usuario();

        entidade.setNome(dto.getNome());
        entidade.setEmail(dto.getEmail());
        entidade.setSenha(dto.getSenha());
        entidade.setCpf(dto.getCpf());
        entidade.setLogradouro(dto.getLogradouro());
        entidade.setNumero(dto.getNumero());
        entidade.setComplemento(dto.getComplemento());
        entidade.setBairro(dto.getBairro());
        entidade.setCidade(dto.getCidade());
        entidade.setEstado(dto.getEstado());
        entidade.setCep(dto.getCep());
        entidade.getFuncoes().addAll(funcoes);

        return entidade;
    }

    public UsuarioDto paraDto(Usuario entidade){
        UsuarioDto dto = new UsuarioDto();

        dto.setNome(entidade.getNome());
        dto.setEmail(entidade.getEmail());
        dto.setSenha(entidade.getSenha());
        dto.setCpf(entidade.getCpf());
        dto.setLogradouro(entidade.getLogradouro());
        dto.setNumero(entidade.getNumero());
        dto.setComplemento(entidade.getComplemento());
        dto.setBairro(entidade.getBairro());
        dto.setCidade(entidade.getCidade());
        dto.setEstado(entidade.getEstado());
        dto.setCep(entidade.getCep());
        dto.getFuncoes().addAll(entidade.getFuncoes().stream().map(GrantedAuthority::getAuthority).toList());

        return dto;
    }
}
