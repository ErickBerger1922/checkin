package backend.backend2.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_empresa")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String codigoAtivacao;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "empresa")
    private Set<Evento> eventos = new HashSet<>();
}
