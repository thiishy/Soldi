package br.edu.fatecpg.soldi.service;

import br.edu.fatecpg.soldi.config.security.TokenConfig;
import br.edu.fatecpg.soldi.dto.request.auth.LoginRequestDTO;
import br.edu.fatecpg.soldi.dto.request.auth.RegistrarRequestDTO;
import br.edu.fatecpg.soldi.dto.response.auth.LoginResponseDTO;
import br.edu.fatecpg.soldi.dto.response.auth.RegistrarResponseDTO;
import br.edu.fatecpg.soldi.exception.PasswordConfirmationException;
import br.edu.fatecpg.soldi.model.Usuario;
import br.edu.fatecpg.soldi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;
    private final EmailService emailService;

    public LoginResponseDTO loginUsuario(LoginRequestDTO request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = tokenConfig.generateToken(usuario);

        return new LoginResponseDTO(token);
    }

    public RegistrarResponseDTO registrarUsuario(RegistrarRequestDTO request) {
        if(!request.senha().equals(request.confirmarSenha())) throw new PasswordConfirmationException("As senhas não coincidem.");

        Usuario novoUsuario = new Usuario();
        novoUsuario.setSenha(passwordEncoder.encode(request.senha()));
        novoUsuario.setEmail(request.email());
        novoUsuario.setNome(request.nome());

        usuarioRepository.save(novoUsuario);

        Map<String, Object> templateVariables = new HashMap<>();
        templateVariables.put("nome", novoUsuario.getNome());
        emailService.enviarEmail(new EmailDetails(novoUsuario.getEmail(),
                "Bem-vindo(a) ao Soldi!", templateVariables), "boasvindas.html");
        
        return new RegistrarResponseDTO(novoUsuario.getNome(), novoUsuario.getEmail());
    }
}
