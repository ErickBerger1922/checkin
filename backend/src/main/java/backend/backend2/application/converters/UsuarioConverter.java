package backend.backend2.application.converters;

import backend.backend2.application.dto.UsuarioDto;
import backend.backend2.domain.model.funcao.Funcao;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.domain.model.valueobjects.Cpf;
import backend.backend2.domain.model.valueobjects.Email;
import backend.backend2.domain.model.valueobjects.Endereco;
import backend.backend2.infrastructure.entity.FuncaoJpa;
import backend.backend2.infrastructure.entity.UsuarioJpa;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UsuarioConverter {

    public Usuario dtoParaDominio(UsuarioDto dto, Set<Funcao> funcoes){
        Usuario usuario = new Usuario(
            dto.getId(),
            new Email(dto.getEmail()),
            dto.getSenha(),
            dto.getNome(),
            new Cpf(dto.getCpf()),
            enderecoAtributosParaVo(dto),
            dto.getRazaoSocial(),
            dto.getCnpj(),
            dto.getCodigoAtivacao(),
            dto.getTipoUsuario()
        );

        if(funcoes != null){
            funcoes.forEach(usuario::adicionaFuncao);
        }

        return usuario;
    }

    public UsuarioJpa dominioParaJpa(Usuario usuario){
        UsuarioJpa usuarioJpa = new UsuarioJpa();

        usuarioJpa.setEmail(usuario.getEmail().getEndereco());
        usuarioJpa.setSenha(usuario.getSenha());
        usuarioJpa.setNome(usuario.getNome());
        usuarioJpa.setCpf(usuario.getCpf().getNumero());
        usuarioJpa.setLogradouro(usuario.getEndereco().getLogradouro());
        usuarioJpa.setNumero(usuario.getEndereco().getNumero());
        usuarioJpa.setComplemento(usuario.getEndereco().getComplemento());
        usuarioJpa.setBairro(usuario.getEndereco().getBairro());
        usuarioJpa.setCidade(usuario.getEndereco().getCidade());
        usuarioJpa.setEstado(usuario.getEndereco().getEstado());
        usuarioJpa.setCep(usuario.getEndereco().getCep());
        usuarioJpa.setRazaoSocial(usuario.getRazaoSocial());
        usuarioJpa.setCnpj(usuario.getCnpj());
        usuarioJpa.setCodigoAtivacao(usuario.getCodigoAtivacao());
        usuarioJpa.setTipoUsuario(usuario.getTipoUsuario());
        usuarioJpa.getFuncoes().addAll(usuario.getFuncoes().stream().map(this::dominioParaJpa).collect(Collectors.toSet()));

        return usuarioJpa;
    }

    public Usuario jpaParaDominio(UsuarioJpa usuarioJpa){
        Usuario usuario = new Usuario(
                usuarioJpa.getId(),
                new Email(usuarioJpa.getEmail()),
                usuarioJpa.getSenha(),
                usuarioJpa.getNome(),
                new Cpf(usuarioJpa.getCpf()),
                enderecoAtributosParaVo(usuarioJpa),
                usuarioJpa.getRazaoSocial(),
                usuarioJpa.getCnpj(),
                usuarioJpa.getCodigoAtivacao(),
                usuarioJpa.getTipoUsuario()
        );

        usuario.getFuncoes().addAll(usuarioJpa.getFuncoes().stream().map(this::JpaParaDominio).collect(Collectors.toSet()));

        return usuario;
    }

    public UsuarioDto dominioParaDto(Usuario usuario){
        UsuarioDto dto = new UsuarioDto();

        dto.setEmail(usuario.getEmail().getEndereco());
        dto.setSenha(usuario.getSenha());
        dto.setNome(usuario.getNome());
        dto.setCpf(usuario.getCpf().getNumero());
        dto.setLogradouro(usuario.getEndereco().getLogradouro());
        dto.setNumero(usuario.getEndereco().getNumero());
        dto.setComplemento(usuario.getEndereco().getComplemento());
        dto.setBairro(usuario.getEndereco().getBairro());
        dto.setCidade(usuario.getEndereco().getCidade());
        dto.setEstado(usuario.getEndereco().getEstado());
        dto.setCep(usuario.getEndereco().getCep());
        dto.setRazaoSocial(usuario.getRazaoSocial());
        dto.setCnpj(usuario.getCnpj());
        dto.setCodigoAtivacao(usuario.getCodigoAtivacao());
        dto.setTipoUsuario(usuario.getTipoUsuario());

        return dto;
    }

    private Endereco enderecoAtributosParaVo(UsuarioDto dto){
        return new Endereco(
                dto.getLogradouro(),
                dto.getNumero(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getCidade(),
                dto.getEstado(),
                dto.getCep()
        );
    }

    private Endereco enderecoAtributosParaVo(UsuarioJpa usuarioJpa){
        return new Endereco(
                usuarioJpa.getLogradouro(),
                usuarioJpa.getNumero(),
                usuarioJpa.getComplemento(),
                usuarioJpa.getBairro(),
                usuarioJpa.getCidade(),
                usuarioJpa.getEstado(),
                usuarioJpa.getCep()
        );
    }

    private FuncaoJpa dominioParaJpa(Funcao funcao){
        return new FuncaoJpa(
                funcao.getId(),
                funcao.getAuthority()
        );
    }

    private Funcao JpaParaDominio(FuncaoJpa funcaoJpa){
        return new Funcao(
                funcaoJpa.getId(),
                funcaoJpa.getAuthority()
        );
    }
}
