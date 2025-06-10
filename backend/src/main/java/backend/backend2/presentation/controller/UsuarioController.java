package backend.backend2.presentation.controller;

import backend.backend2.application.dto.UsuarioDto;
import backend.backend2.application.record.LoginRecord;
import backend.backend2.application.service.UsuarioService;
import backend.backend2.infrastructure.entity.UsuarioJpa;
import backend.backend2.infrastructure.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    @Operation(summary = "Login Usuários", description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginRecord usuario){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuario.email(),
                        usuario.senha())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    /*@PostMapping
    @Operation(summary = "Salvar usuário",description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário criado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDto> salvaUsuario(@RequestBody UsuarioDto usuario) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuario));
    }

    @GetMapping("/{id}")
    public UsuarioJpa ListarPorId(@PathVariable Long id){
        return new UsuarioJpa();
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody UsuarioJpa pessoaFisica){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public List<UsuarioJpa> deletar(@PathVariable Long id){
        return new ArrayList<>();
    }

     */
}

