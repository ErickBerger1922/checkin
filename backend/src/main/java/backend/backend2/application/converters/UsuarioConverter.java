package backend.backend2.application.converters;

import backend.backend2.application.dto.UsuarioDto;
import backend.backend2.domain.model.funcao.Funcao;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.domain.model.vo.Endereco;
import backend.backend2.infrastructure.entity.UsuarioJpa;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UsuarioConverter {

    /*public Usuario dtoParaUsuario(UsuarioDto dto, Set<Funcao> funcoes){
        return new Usuario(
            dto.getId(),
            dto.getEmail(),
            dto.getNome(),
            dto.getSenha(),
            dto.getCpf(),
            new Endereco(atributosParaVo(dto)),
            dto.getRazaoSocial(),
            dto.getCnpj(),
            dto.getCodigoAtivacao(),
            dto.getTipoUsuario()
        );
    }

    public UsuarioDto paraDto(UsuarioJpa entidade){
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
        dto.setCep(entidade.getCep());
        dto.setRazaoSocial(entidade.getRazaoSocial());
        dto.setCnpj(entidade.getCnpj());
        dto.setCodigoAtivacao(entidade.getCodigoAtivacao());
        dto.setTipoUsuario(entidade.getTipoUsuario());
        dto.getFuncoes().addAll(entidade.getFuncoes().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));

        return dto;
    }

    private Endereco atributosParaVo(UsuarioDto dto){
        return new Endereco(
                dto.getLogradouro(),
                dto.getNumero(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getCidade(),
                dto.getEstado(),
                dto.getCep()
        );
    }*/
}
