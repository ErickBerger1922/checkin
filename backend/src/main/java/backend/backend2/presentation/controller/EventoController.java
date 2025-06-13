package backend.backend2.presentation.controller;

import backend.backend2.application.dto.EventoDto;
import backend.backend2.application.dto.UsuarioCheckinDto;
import backend.backend2.application.service.EventoService;
import backend.backend2.application.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;
    private final UsuarioService usuarioService;

    public EventoController(EventoService eventoService, UsuarioService usuarioService) {
        this.eventoService = eventoService;
        this.usuarioService = usuarioService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENTERPRISE', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<EventoDto>> listaEventos(){
        return ResponseEntity.ok(eventoService.listaEventos());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENTERPRISE')")
    @GetMapping("/empresa")
    public ResponseEntity<List<EventoDto>> listaEventosConformeEmpresa(){
        Long empresaId = usuarioService.usuarioAutenticado().getId();
        return ResponseEntity.ok(eventoService.listaEventosConformeEmpresa(empresaId));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENTERPRISE')")
    @GetMapping("/empresa/{eventoId}/checkins")
    public ResponseEntity<List<UsuarioCheckinDto>> buscarUsuariosComCheckinNoEvento(@PathVariable Long eventoId) {
        List<UsuarioCheckinDto> checkins = eventoService.buscarUsuariosComCheckinNoEvento(eventoId);
        return ResponseEntity.ok(checkins);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENTERPRISE')")
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
