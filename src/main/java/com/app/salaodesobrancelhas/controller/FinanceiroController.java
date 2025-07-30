package com.app.salaodesobrancelhas.controller;

import com.app.salaodesobrancelhas.entity.Financeiro;
import com.app.salaodesobrancelhas.service.FinanceiroService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/financeiro")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class FinanceiroController {
    private final FinanceiroService financeiroService;

    public FinanceiroController(FinanceiroService financeiroService) {
        this.financeiroService = financeiroService;
    }

    @GetMapping
    public String listarFinanceiro(Model model,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        List<Financeiro> registros;
        BigDecimal totalDia = BigDecimal.ZERO;
        BigDecimal totalMes = BigDecimal.ZERO;

        if (dataInicio != null && dataFim != null) {
            registros = financeiroService.buscarPorPeriodo(dataInicio.atStartOfDay(), dataFim.atTime(LocalTime.MAX));
        } else {
            registros = financeiroService.listarTodos();
        }

        totalDia = financeiroService.totalDoDia(LocalDate.now());
        totalMes = financeiroService.totalDoMes(LocalDate.now());

        model.addAttribute("registros", registros);
        model.addAttribute("totalDia", totalDia);
        model.addAttribute("totalMes", totalMes);

        return "financeiro";
    }


    @PostMapping
    public String salvar(@Valid Financeiro financeiro) {
        financeiroService.salvar(financeiro);
        return "redirect:/financeiro";
    }

    @PostMapping("/remover/{id}")
    public String remover(@PathVariable Long id) {
        financeiroService.deletar(id);
        return "redirect:/financeiro";
    }
}
