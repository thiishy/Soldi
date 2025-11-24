package br.edu.fatecpg.soldi.controller;

import br.edu.fatecpg.soldi.dto.request.LoginRequestDTO;
import br.edu.fatecpg.soldi.dto.request.RegistrarRequestDTO;
import br.edu.fatecpg.soldi.dto.response.LoginResponseDTO;
import br.edu.fatecpg.soldi.dto.response.RegistrarResponseDTO;
import br.edu.fatecpg.soldi.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "Endpoints relacionados ao login e registro de usuários")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = authService.loginUsuario(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/registrar")
    public ResponseEntity<RegistrarResponseDTO> registrar(@Valid @RequestBody RegistrarRequestDTO request) {
        RegistrarResponseDTO response = authService.registrarUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
