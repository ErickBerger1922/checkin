package backend.backend2.presentation.controller;

import backend.backend2.application.dto.UsuarioDto;
import backend.backend2.application.dto.UsuarioLogadoDto;
import backend.backend2.application.record.LoginRecord;
import backend.backend2.application.service.UsuarioService;
import backend.backend2.infrastructure.entity.UsuarioJpa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Endereço responsável pelo controle de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "/me")
    public ResponseEntity<UsuarioLogadoDto> getMe(){
        return ResponseEntity.ok(usuarioService.getMe());
    }

    @PostMapping
    @Operation(summary = "Salvar usuário",description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário criado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDto> salvaUsuario(@RequestBody UsuarioDto usuario) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuario));
    }
}

