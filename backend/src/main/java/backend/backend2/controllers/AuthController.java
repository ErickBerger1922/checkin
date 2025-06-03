package backend.backend2.controllers;

import backend.backend2.business.records.LoginRequest;
import backend.backend2.business.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação",description = "Endereço responsável por controle de autenticação de usuário.")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    /*
    @PostMapping
    @Operation(summary = "Login do usuário",description = "Login do usuario que retorna o JWT Token valido.")
    public ResponseEntity<?> login(LoginRequest loginRequest){

        var ResultGerarToken = tokenService.gerarToken(loginRequest);
        return ResponseEntity.ok(ResultGerarToken);
    }*/
}
