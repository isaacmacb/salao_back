package com.app.salaodesobrancelhas.service;

import com.app.salaodesobrancelhas.entity.Enum.TipoLancamento;
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
        financeiro.setData(LocalDateTime.now());
        return financeiroRepository.save(financeiro);
    }

    public void deletar(Long id) {
        financeiroRepository.deleteById(id);
    }

    public List<Financeiro> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return financeiroRepository.findByDataBetween(inicio, fim);
    }

    public BigDecimal totalPorPeriodoETipo(LocalDateTime inicio, LocalDateTime fim, TipoLancamento tipo) {
        return financeiroRepository.sumByTipoAndDataBetween(tipo, inicio, fim);
    }

    public BigDecimal totalDoDia(LocalDate dia) {
        LocalDateTime inicio = dia.atStartOfDay();
        LocalDateTime fim = dia.atTime(LocalTime.MAX);
        return totalPorPeriodoETipo(inicio, fim, TipoLancamento.ENTRADA);
    }

    public BigDecimal totalDoMes(LocalDate data) {
        LocalDate primeiroDia = data.withDayOfMonth(1);
        LocalDate ultimoDia = data.withDayOfMonth(data.lengthOfMonth());
        LocalDateTime inicio = primeiroDia.atStartOfDay();
        LocalDateTime fim = ultimoDia.atTime(LocalTime.MAX);
        return totalPorPeriodoETipo(inicio, fim, TipoLancamento.ENTRADA);
    }
}
