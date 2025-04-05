package backend.backend2.controllers;

import backend.backend2.models.entities.Usuario;
import backend.backend2.models.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
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

