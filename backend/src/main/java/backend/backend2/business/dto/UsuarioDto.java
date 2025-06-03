package backend.backend2.business.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDto {

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    @Setter(AccessLevel.NONE)
    private List<String> funcoes = new ArrayList<>();
}
