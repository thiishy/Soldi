package br.edu.fatecpg.soldi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record RegistrarRequestDTO(@NotBlank(message = "O nome é obrigatório.") String nome,
                                  @Email @NotBlank(message = "O email é obrigatório.") String email,
                                  @NotBlank(message = "A senha é obrigatória.") String senha,
                                  @NotBlank(message = "A confirmação da senha é obrigatória.") String confirmarSenha) { }
