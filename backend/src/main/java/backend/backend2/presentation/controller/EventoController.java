package backend.backend2.presentation.controller;

import backend.backend2.application.dto.CheckinDto;
import backend.backend2.application.dto.EventoDto;
import backend.backend2.application.service.CheckinService;
import backend.backend2.application.service.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;
    private final CheckinService checkinService;

    public EventoController(EventoService eventoService, CheckinService checkinService) {
        this.eventoService = eventoService;
        this.checkinService = checkinService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENTERPRISE', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<EventoDto>> listaEventos(){
        return ResponseEntity.ok(eventoService.listaEventos());
    }

    /*@GetMapping("/{id}/checkins")
    public ResponseEntity<List<CheckinDto>> listarCheckinsPorEvento(@PathVariable Long eventoId) {
        List<CheckinDto> checkins = checkinService.buscarCheckinsPorEvento(eventoId);
        return ResponseEntity.ok(checkins);
    }*/

    @PreAuthorize("hasRole('ROLE_ENTERPRISE')")
    @PostMapping
    public ResponseEntity<?> salvaEvento(@RequestBody EventoDto dto) {
        return ResponseEntity.ok(eventoService.salvaEvento(dto));
    }

    @PreAuthorize("hasRole('ROLE_ENTERPRISE')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletaEvento(@PathVariable Long id){
        eventoService.deletaEvento(id);
        return ResponseEntity.noContent().build();
    }
}
