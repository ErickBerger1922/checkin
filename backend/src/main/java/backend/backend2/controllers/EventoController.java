package backend.backend2.controllers;

import backend.backend2.business.records.CriarEventoRequest;
import backend.backend2.business.services.EventoService;
import backend.backend2.infrastructure.entities.Evento;
import backend.backend2.infrastructure.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class EventoController {
    @Autowired
    private final EventoRepository repository;

    @Autowired
    private EventoService eventoService;

    public EventoController(EventoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    /*public ResponseEntity<?> criarEvento(@RequestBody CriarEventoRequest evento) {
        var retorno = eventoService.save(evento);
        return ResponseEntity.ok(retorno);
    }*/

    @GetMapping
    public List<Evento> listarEventos() {
        return repository.findAll();
    }
}
