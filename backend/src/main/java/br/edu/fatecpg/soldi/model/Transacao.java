package br.edu.fatecpg.soldi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transacoes")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid_externo", nullable = false, unique = true)
    private UUID uuidExterno = UUID.randomUUID();

    @Column(nullable = false)
    private String tipo; // "RECEITA" ou "DESPESA"

    @Column(nullable = false)
    private BigDecimal valor;

    @Column
    private String descricao;

    @Column(nullable = false)
    private String categoria; // Ex: "Alimentação", "Transporte", "Salário", etc.

    @Column(name = "data_transacao", nullable = false)
    @CreationTimestamp
    private LocalDateTime dataTransacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}