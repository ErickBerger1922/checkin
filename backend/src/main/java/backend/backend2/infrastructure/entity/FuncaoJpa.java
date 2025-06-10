package backend.backend2.infrastructure.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_funcao")
public class FuncaoJpa implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    @ManyToMany(mappedBy = "funcoes")
    private Set<UsuarioJpa> usuarios = new HashSet<>();

    public FuncaoJpa() {
    }

    public FuncaoJpa(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<UsuarioJpa> getUsuarios() {
        return usuarios;
    }
}
