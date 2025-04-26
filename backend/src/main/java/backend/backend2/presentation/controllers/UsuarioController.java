package backend.backend2.presentation.controllers;

import backend.backend2.domain.entities.Usuario;
import backend.backend2.domain.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuarios", description = "Endereço responsável pelo controle de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Operation(summary = "Salvar usuário",description = "Metodo responsável em salvar o usuário")
    public ResponseEntity<?> salvar(@RequestBody Usuario usuario) {

        if (usuario.getId() != null) {
            var usuarioexiste = usuarioRepository.findById(usuario.getId());
            if (usuarioexiste.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
        }

        var retornoSalvarUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(retornoSalvarUsuario);
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

