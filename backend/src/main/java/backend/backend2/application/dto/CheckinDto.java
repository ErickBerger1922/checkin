package backend.backend2.application.dto;

import java.time.LocalDateTime;

public class CheckinDto {

    private Long id;
    private LocalDateTime dataHora;
    private Long usuarioId;
    private Long eventoId;

    public CheckinDto() {
    }

    public CheckinDto(Long id, LocalDateTime dataHora, Long usuarioId, Long eventoId) {
        this.id = id;
        this.dataHora = dataHora;
        this.usuarioId = usuarioId;
        this.eventoId = eventoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }
}
