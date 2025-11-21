package br.edu.fatecpg.soldi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record LoginRequestDTO(@Email @NotEmpty(message = "O email é obrigatório.") String email,
                              @NotEmpty(message = "A senha é obrigatória.") String senha) { }
