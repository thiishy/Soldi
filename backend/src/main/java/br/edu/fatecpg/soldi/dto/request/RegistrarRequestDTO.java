package br.edu.fatecpg.soldi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record RegistrarRequestDTO(@NotEmpty(message = "O nome é obrigatório.") String nome,
                                  @Email @NotEmpty(message = "O email é obrigatório.") String email,
                                  @NotEmpty(message = "A senha é obrigatória.") String senha) { }
