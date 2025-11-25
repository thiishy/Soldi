package br.edu.fatecpg.soldi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record RegistrarRequestDTO(@NotBlank(message = "O nome é obrigatório.") String nome,
                                  @Email @NotBlank(message = "O email é obrigatório.") String email,
                                  @Size(min = 6, max = 64, message = "A senha deve ter no mínimo 6 e no máximo 64 caracteres.") @NotBlank(message = "A senha é obrigatória.") String senha,
                                  @Size(min = 6, max = 64, message = "A senha deve ter no mínimo 6 e no máximo 64 caracteres.") @NotBlank(message = "A confirmação da senha é obrigatória.") String confirmarSenha) { }
