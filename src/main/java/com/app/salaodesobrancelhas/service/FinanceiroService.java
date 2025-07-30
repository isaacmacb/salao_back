package com.app.salaodesobrancelhas.service;

import com.app.salaodesobrancelhas.entity.Financeiro;
import com.app.salaodesobrancelhas.repository.FinanceiroRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class FinanceiroService {
    private final FinanceiroRepository financeiroRepository;

    public FinanceiroService(FinanceiroRepository financeiroRepository) {
        this.financeiroRepository = financeiroRepository;
    }
    public List<Financeiro> listarTodos() {
        return financeiroRepository.findAll();
    }

    public Optional<Financeiro> buscarPorId(Long id) {
        return financeiroRepository.findById(id);
    }

    public Financeiro salvar(Financeiro financeiro) {
        financeiro.setData(LocalDateTime.now()); // seta a data automaticamente
        return financeiroRepository.save(financeiro);
    }

    public void deletar(Long id) {
        financeiroRepository.deleteById(id);
    }

    public List<Financeiro> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        LocalDateTime inicioDateTime = inicio.atStartOfDay();
        LocalDateTime fimDateTime = fim.atTime(LocalTime.MAX);
        return financeiroRepository.findByDataBetween(inicioDateTime, fimDateTime);
    }

    public BigDecimal totalPorPeriodoETipo(LocalDate inicio, LocalDate fim, String tipo) {
        LocalDateTime inicioDateTime = inicio.atStartOfDay();
        LocalDateTime fimDateTime = fim.atTime(LocalTime.MAX);
        return financeiroRepository.sumByTipoAndDataBetween(tipo, inicioDateTime, fimDateTime);
    }

    public BigDecimal totalDoDia(LocalDate dia) {
        return totalPorPeriodoETipo(dia, dia, "ENTRADA");
    }

    public BigDecimal totalDoMes(LocalDate data) {
        LocalDate primeiroDia = data.withDayOfMonth(1);
        LocalDate ultimoDia = data.withDayOfMonth(data.lengthOfMonth());
        return totalPorPeriodoETipo(primeiroDia, ultimoDia, "ENTRADA");
    }

    public List<Financeiro> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return financeiroRepository.findByDataBetween(inicio, fim);
    }
}
