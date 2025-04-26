package backend.backend2.domain.entities;

import backend.backend2.domain.valueobjetcs.CPF;
import backend.backend2.application.object.UsuarioRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

    public Usuario(UsuarioRequest usuario) {
        this.nome = usuario.nome();
        this.senha = usuario.senha();
    }

    public Usuario(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String senha;

    @Embedded
    private CPF cpf;

    private String cep;
    private String logradouro;
    private Long numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
}
