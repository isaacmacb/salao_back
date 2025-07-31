package com.app.salaodesobrancelhas.controller;

import com.app.salaodesobrancelhas.entity.Financeiro;
import com.app.salaodesobrancelhas.service.FinanceiroService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/financeiro")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class FinanceiroController {

    private final FinanceiroService financeiroService;

    public FinanceiroController(FinanceiroService financeiroService) {
        this.financeiroService = financeiroService;
    }

    @GetMapping
    public Map<String, Object> listarFinanceiro(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        List<Financeiro> registros;
        BigDecimal totalDia;
        BigDecimal totalMes;

        if (dataInicio != null && dataFim != null) {
            registros = financeiroService.buscarPorPeriodo(dataInicio.atStartOfDay(), dataFim.atTime(LocalTime.MAX));
        } else {
            registros = financeiroService.listarTodos();
        }

        totalDia = financeiroService.totalDoDia(LocalDate.now());
        totalMes = financeiroService.totalDoMes(LocalDate.now());

        Map<String, Object> response = new HashMap<>();
        response.put("registros", registros);
        response.put("totalDia", totalDia);
        response.put("totalMes", totalMes);

        return response;
    }

    @PostMapping
    public Financeiro salvar(@RequestBody Financeiro financeiro) {
        return financeiroService.salvar(financeiro);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        financeiroService.deletar(id);
    }
}
