package br.edu.fatecpg.soldi.service;

import br.edu.fatecpg.soldi.config.TokenConfig;
import br.edu.fatecpg.soldi.dto.request.LoginRequestDTO;
import br.edu.fatecpg.soldi.dto.request.RegistrarRequestDTO;
import br.edu.fatecpg.soldi.dto.response.LoginResponseDTO;
import br.edu.fatecpg.soldi.dto.response.RegistrarResponseDTO;
import br.edu.fatecpg.soldi.model.Usuario;
import br.edu.fatecpg.soldi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public LoginResponseDTO loginUsuario(LoginRequestDTO request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = tokenConfig.generateToken(usuario);

        return new LoginResponseDTO(token);
    }

    public RegistrarResponseDTO registrarUsuario(RegistrarRequestDTO request) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setSenha(passwordEncoder.encode(request.senha()));
        novoUsuario.setEmail(request.email());
        novoUsuario.setNome(request.nome());

        usuarioRepository.save(novoUsuario);
        return new RegistrarResponseDTO(novoUsuario.getNome(), novoUsuario.getEmail());
    }
}
