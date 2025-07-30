package com.app.salaodesobrancelhas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Financeiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true) // relacionamento opcional
    @JoinColumn(name = "agendamento_id")
    private Agendamento agendamento;

    private String tipo; // ENTRADA ou SAÍDA

    @ManyToOne
    private Servico servico;

    private BigDecimal valor;

    private String descricao;

    private LocalDateTime data;
}
