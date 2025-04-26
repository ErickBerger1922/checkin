package backend.backend2.domain.entities;


import backend.backend2.application.object.CriarEventoRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    public Evento (CriarEventoRequest evento){
        this.ativo = true;
        this.evento = evento.nomeEvento();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String evento;
    private boolean ativo;
}