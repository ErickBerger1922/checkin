package backend.backend2.controllers;

import backend.backend2.models.entities.Evento;
import backend.backend2.models.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class EventoController {
    @Autowired
    private final EventoRepository repository;

    public EventoController(EventoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Evento criarEvento(@RequestBody Evento evento) {
        return repository.save(evento);
    }

    @GetMapping
    public List<Evento> listarEventos() {
        return repository.findAll();
    }
}
