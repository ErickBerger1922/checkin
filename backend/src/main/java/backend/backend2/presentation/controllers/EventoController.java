package backend.backend2.presentation.controllers;

import backend.backend2.application.object.CriarEventoRequest;
import backend.backend2.application.services.EventoService;
import backend.backend2.domain.entities.Evento;
import backend.backend2.domain.repository.EventoRepository;
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
    public ResponseEntity<?> criarEvento(@RequestBody CriarEventoRequest evento) {
        var retorno = eventoService.save(evento);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping
    public List<Evento> listarEventos() {
        return repository.findAll();
    }
}
