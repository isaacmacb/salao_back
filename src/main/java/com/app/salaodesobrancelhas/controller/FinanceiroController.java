package com.app.salaodesobrancelhas.controller;

import com.app.salaodesobrancelhas.entity.Financeiro;
import com.app.salaodesobrancelhas.service.FinanceiroService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public ResponseEntity<Map<String, Object>> listarFinanceiro(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        LocalDateTime inicio = dataInicio != null ? dataInicio.atStartOfDay() : null;
        LocalDateTime fim = dataFim != null ? dataFim.atTime(LocalTime.MAX) : null;

        List<Financeiro> registros;

        if (inicio != null && fim != null) {
            registros = financeiroService.buscarPorPeriodo(inicio, fim);
        } else if (inicio != null) {
            registros = financeiroService.buscarPorPeriodo(inicio, LocalDateTime.now());
        } else if (fim != null) {
            registros = financeiroService.buscarPorPeriodo(LocalDateTime.of(1970, 1, 1, 0, 0), fim);
        } else {
            registros = financeiroService.listarTodos();
        }

        BigDecimal totalDia = financeiroService.totalDoDia(LocalDate.now());
        BigDecimal totalMes = financeiroService.totalDoMes(LocalDate.now());

        Map<String, Object> response = new HashMap<>();
        response.put("registros", registros);
        response.put("totalDia", totalDia);
        response.put("totalMes", totalMes);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Financeiro> salvar(@RequestBody Financeiro financeiro) {
        if (financeiro == null || financeiro.getValor() == null || financeiro.getTipo() == null) {
            return ResponseEntity.badRequest().build();
        }
        Financeiro salvo = financeiroService.salvar(financeiro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        financeiroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
