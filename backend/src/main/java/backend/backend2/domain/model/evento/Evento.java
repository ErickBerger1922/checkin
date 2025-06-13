package backend.backend2.domain.model.evento;

import backend.backend2.domain.model.checkin.Checkin;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.infrastructure.entity.CheckinJpa;
import backend.backend2.infrastructure.entity.UsuarioJpa;

import java.time.LocalDateTime;
import java.util.Collections;
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

    private Usuario empresaResponsavel;

    private Set<Usuario> usuarios = new HashSet<>();
    private Set<Checkin> checkins = new HashSet<>();

    public Evento(Long id, String nome, String localizacao, LocalDateTime dataInicioEvento, LocalDateTime dataFimEvento, Usuario empresaResponsavel) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.dataInicioEvento = dataInicioEvento;
        this.dataFimEvento = dataFimEvento;
        this.ativo = true;
        this.empresaResponsavel = empresaResponsavel;
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

    public Usuario getEmpresaResponsavel() {
        return empresaResponsavel;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public Set<Checkin> getCheckins() {
        return checkins;
    }

    public void ativarEvento(){
        this.ativo = true;
    }

    public void desativarEvento(){
        this.ativo = false;
    }

    public void adicionarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public void removerUsuario(Usuario usuario){
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