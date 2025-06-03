package backend.backend2.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_funcao")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Funcao implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    @Setter(AccessLevel.NONE)
    @ManyToMany
    private Set<Usuario> usuarios = new HashSet<>();

    @Override
    public String getAuthority() {
        return authority;
    }
}
