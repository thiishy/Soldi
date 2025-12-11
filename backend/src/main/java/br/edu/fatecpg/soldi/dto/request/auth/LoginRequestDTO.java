package br.edu.fatecpg.soldi.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(@Email @NotBlank(message = "O email é obrigatório.") String email,
                              @NotBlank(message = "A senha é obrigatória.") String senha) { }
