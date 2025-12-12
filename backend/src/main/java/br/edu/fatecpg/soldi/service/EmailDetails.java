package br.edu.fatecpg.soldi.service;

import java.util.Map;

public record EmailDetails(String destinatario, String assunto, Map<String, Object> templateVariables) { }
