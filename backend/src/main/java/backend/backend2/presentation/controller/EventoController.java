package backend.backend2.presentation.controller;

import backend.backend2.application.dto.EventoDto;
import backend.backend2.application.service.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity<List<EventoDto>> listaEventos(){
        return ResponseEntity.ok(eventoService.listaEventos());
    }

    @PostMapping
    public ResponseEntity<?> salvaEvento(@RequestBody EventoDto dto) {
        return ResponseEntity.ok(eventoService.salvaEvento(dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletaEvento(@PathVariable Long id){
        eventoService.deletaEvento(id);
        return ResponseEntity.noContent().build();
    }
}
