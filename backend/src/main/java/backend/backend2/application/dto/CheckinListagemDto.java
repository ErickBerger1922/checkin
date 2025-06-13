package backend.backend2.application.dto;

import java.time.LocalDateTime;

public class CheckinListagemDto {

    private LocalDateTime dataHora;
    private String nomeUsuario;
    private String nomeEvento;

    public CheckinListagemDto() {
    }

    public CheckinListagemDto(LocalDateTime dataHora, String nomeUsuario, String nomeEvento) {
        this.dataHora = dataHora;
        this.nomeUsuario = nomeUsuario;
        this.nomeEvento = nomeEvento;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }
}
