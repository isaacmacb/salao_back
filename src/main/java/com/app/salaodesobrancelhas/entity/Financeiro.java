package com.app.salaodesobrancelhas.entity;

import com.app.salaodesobrancelhas.entity.Enum.FormaPagamento;
import com.app.salaodesobrancelhas.entity.Enum.StatusLancamento;
import com.app.salaodesobrancelhas.entity.Enum.TipoLancamento;
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

    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    private String servico;  // Se quiser associar entidade Serviço, ajuste aqui

    private String descricao;

    private BigDecimal valor;
}

