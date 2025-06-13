package backend.backend2.application.dto;

import java.util.HashSet;
import java.util.Set;

public class UsuarioLogadoDto {

    private Long id;
    private String nome;
    private String email;

    private Set<String> funcoes = new HashSet<>();

    public UsuarioLogadoDto() {
    }

    public UsuarioLogadoDto(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Set<String> getFuncoes() {
        return funcoes;
    }
}
