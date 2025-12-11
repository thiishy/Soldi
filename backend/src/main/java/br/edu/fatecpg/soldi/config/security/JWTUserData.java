package br.edu.fatecpg.soldi.config.security;

import lombok.Builder;

import java.util.UUID;

@Builder
public record JWTUserData(UUID uuidUsuario, String email) { }
