package com.app.salaodesobrancelhas.controller;


import com.app.salaodesobrancelhas.Dtos.LoginRequest;
import com.app.salaodesobrancelhas.entity.Usuario;
import com.app.salaodesobrancelhas.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    //Controller e DTO trabalham principalmente na comunicação com o front-end

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario usuario) {
        usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getSenha()
                    )
            );

            if (auth.isAuthenticated()) {
                // Retorna uma resposta simples, por exemplo:
                return ResponseEntity.ok(Map.of("message", "Login realizado com sucesso!"));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Falha no login"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Usuário ou senha inválidos"));
        }
    }

}
