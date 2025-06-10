package backend.backend2.domain.model.evento;

import backend.backend2.infrastructure.entity.CheckinJpa;
import backend.backend2.infrastructure.entity.UsuarioJpa;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Evento {

    private Long id;
    private String nome;
    private String localizacao;
    private LocalDateTime dataInicioEvento;
    private LocalDateTime dataFimEvento;
    private boolean ativo;

    private Set<UsuarioJpa> usuarios = new HashSet<>();
    private Set<CheckinJpa> checkins = new HashSet<>();

    public Evento(Long id, String nome, String localizacao, LocalDateTime dataInicioEvento, LocalDateTime dataFimEvento) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.dataInicioEvento = dataInicioEvento;
        this.dataFimEvento = dataFimEvento;
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public LocalDateTime getDataInicioEvento() {
        return dataInicioEvento;
    }

    public LocalDateTime getDataFimEvento() {
        return dataFimEvento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Set<UsuarioJpa> getUsuarios() {
        return usuarios;
    }

    public Set<CheckinJpa> getCheckins() {
        return checkins;
    }

    public void ativarEvento(){
        this.ativo = true;
    }

    public void desativarEvento(){
        this.ativo = false;
    }

    public void adicionarUsuario(UsuarioJpa usuario){
        this.usuarios.add(usuario);
    }

    public void removerUsuario(UsuarioJpa usuario){
        this.usuarios.remove(usuario);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(id, evento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}