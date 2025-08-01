package com.app.salaodesobrancelhas.repository;

import com.app.salaodesobrancelhas.entity.Financeiro;
import com.app.salaodesobrancelhas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface FinanceiroRepository extends JpaRepository<Financeiro, Long> {
    List<Financeiro> findByDataBetween(LocalDateTime inicio, LocalDateTime fim); // buscar lançamentos por período

    // Soma de entradas
    @Query("SELECT SUM(f.valor) FROM Financeiro f WHERE f.tipo = :tipo AND f.data BETWEEN :inicio AND :fim")
    BigDecimal sumByTipoAndDataBetween(@Param("tipo") String tipo,
                                       @Param("inicio") LocalDateTime inicio,
                                       @Param("fim") LocalDateTime fim);
}
