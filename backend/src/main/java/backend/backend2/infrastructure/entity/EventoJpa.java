package backend.backend2.infrastructure.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_evento")
public class EventoJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String localizacao;
    private LocalDateTime dataInicioEvento;
    private LocalDateTime dataFimEvento;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private UsuarioJpa empresaResponsavel;

    @ManyToMany
    @JoinTable(name = "tb_evento_usuario",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<UsuarioJpa> usuarios = new HashSet<>();

    @OneToMany(mappedBy = "evento")
    private Set<CheckinJpa> checkins = new HashSet<>();

    public EventoJpa() {
    }

    public EventoJpa(Long id, String nome, String localizacao, LocalDateTime dataInicioEvento, LocalDateTime dataFimEvento, boolean ativo, UsuarioJpa empresaResponsavel) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.dataInicioEvento = dataInicioEvento;
        this.dataFimEvento = dataFimEvento;
        this.ativo = ativo;
        this.empresaResponsavel = empresaResponsavel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public LocalDateTime getDataInicioEvento() {
        return dataInicioEvento;
    }

    public void setDataInicioEvento(LocalDateTime dataInicioEvento) {
        this.dataInicioEvento = dataInicioEvento;
    }

    public LocalDateTime getDataFimEvento() {
        return dataFimEvento;
    }

    public void setDataFimEvento(LocalDateTime dataFimEvento) {
        this.dataFimEvento = dataFimEvento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public UsuarioJpa getEmpresaResponsavel() {
        return empresaResponsavel;
    }

    public void setEmpresaResponsavel(UsuarioJpa empresaResponsavel) {
        this.empresaResponsavel = empresaResponsavel;
    }

    public Set<UsuarioJpa> getUsuarios() {
        return usuarios;
    }

    public Set<CheckinJpa> getCheckins() {
        return checkins;
    }
}