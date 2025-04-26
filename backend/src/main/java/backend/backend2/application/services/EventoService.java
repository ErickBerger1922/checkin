package backend.backend2.application.services;

import backend.backend2.application.object.CriarEventoRequest;
import backend.backend2.domain.entities.Evento;
import backend.backend2.domain.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;


    public Evento save (CriarEventoRequest request){
        return repository.save(new Evento(request));
    }

}
