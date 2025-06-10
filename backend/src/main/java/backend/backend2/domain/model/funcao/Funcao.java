package backend.backend2.domain.model.funcao;

import java.util.Objects;

public class Funcao {

    private Long id;
    private String authority;

    public Funcao(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Funcao funcao = (Funcao) o;
        return Objects.equals(id, funcao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
