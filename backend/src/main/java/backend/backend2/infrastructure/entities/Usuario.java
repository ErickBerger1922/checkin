package backend.backend2.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @ManyToMany
    @JoinTable(name = "tb_usuario_funcao",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "funcao_id"))
    private Set<Funcao> funcoes = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "usuarios")
    private Set<Evento> eventos = new HashSet<>();

    @OneToOne(mappedBy = "usuario")
    private Empresa empresa;

    public void adicionaFuncao(Funcao funcao){
        funcoes.add(funcao);
    }

    public boolean hasRole(String nomeFuncao){
        for (Funcao funcao : funcoes) {
            if(funcao.getAuthority().equals(nomeFuncao)){
                return true;
            }
        }
        return false;
    }

}
