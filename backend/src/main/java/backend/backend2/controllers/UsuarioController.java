package backend.backend2.controllers;

import backend.backend2.business.dto.UsuarioDto;
import backend.backend2.business.records.LoginRequest;
import backend.backend2.business.services.UsuarioService;
import backend.backend2.infrastructure.entities.Usuario;
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
@RequestMapping("/usuario")
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
    public String login(@RequestBody LoginRequest usuario){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuario.email(),
                        usuario.senha())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
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

    @GetMapping("/{id}")
    public Usuario ListarPorId(@PathVariable Long id){
        return new Usuario();
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody Usuario usuario){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public List<Usuario> deletar(@PathVariable Long id){
        return new ArrayList<>();
    }
}

