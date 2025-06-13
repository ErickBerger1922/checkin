package backend.backend2.presentation.controller;

import backend.backend2.application.dto.CheckinDto;
import backend.backend2.application.dto.CheckinListagemDto;
import backend.backend2.application.service.CheckinService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkins")
public class CheckinController {

    private final CheckinService checkinService;

    public CheckinController(CheckinService checkinService) {
        this.checkinService = checkinService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ENTERPRISE', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<CheckinListagemDto>> listaCheckins() {
        return ResponseEntity.ok(checkinService.listaCheckins());
    }

    @PreAuthorize("hasAnyRole('ROLE_ENTERPRISE', 'ROLE_USER')")
    @PostMapping
    public ResponseEntity<CheckinDto> salvaCheckin(@RequestBody CheckinDto dto) {
        return ResponseEntity.ok(checkinService.salvaCheckin(dto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ENTERPRISE', 'ROLE_USER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletaCheckin(@PathVariable Long id) {
        checkinService.deletaCheckin(id);
        return ResponseEntity.noContent().build();
    }
}