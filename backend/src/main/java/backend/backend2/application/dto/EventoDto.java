package backend.backend2.application.dto;

import java.time.LocalDateTime;

public class EventoDto {

    private String nome;
    private String localizacao;
    private LocalDateTime dataInicioEvento;
    private LocalDateTime dataFimEvento;
    private boolean ativo;
    private EmpresaResponsavelDto empresaResponsavel;

    public EventoDto() {
    }

    public EventoDto(String nome, String localizacao, LocalDateTime dataInicioEvento, LocalDateTime dataFimEvento, boolean ativo) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.dataInicioEvento = dataInicioEvento;
        this.dataFimEvento = dataFimEvento;
        this.ativo = ativo;
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

    public EmpresaResponsavelDto getEmpresaResponsavel() {
        return empresaResponsavel;
    }

    public void setEmpresaResponsavel(EmpresaResponsavelDto empresaResponsavel) {
        this.empresaResponsavel = empresaResponsavel;
    }
}
