package br.edu.fatecpg.soldi.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChatResponseDTO(String resposta, LocalDateTime dataResposta) {}
