package backend.backend2.business.records;

import java.time.LocalDateTime;

public record CriarEventoRequest(String nomeEvento,
                                 String localizacao,
                                 LocalDateTime dataInicioEvento,
                                 LocalDateTime dataFimEvento) {
}
