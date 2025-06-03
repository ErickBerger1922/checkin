package backend.backend2.business.services;

import backend.backend2.business.records.CriarEventoRequest;
import backend.backend2.infrastructure.entities.Evento;
import backend.backend2.infrastructure.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    /*public Evento save (CriarEventoRequest request){
        return repository.save(new Evento(request));
    }*/

}
