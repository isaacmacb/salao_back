package com.app.salaodesobrancelhas.controller;

import com.app.salaodesobrancelhas.entity.TelaAgendamento;
import com.app.salaodesobrancelhas.service.TelaAgendamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telaAgendamento")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class TelaAgendamentoController {
    private final TelaAgendamentoService  telaAgendamentoService;

    public TelaAgendamentoController(TelaAgendamentoService telaAgendamentoService) {
        this.telaAgendamentoService = telaAgendamentoService;
    }

    @GetMapping
    public List<TelaAgendamento> listarTodos() {
        return telaAgendamentoService.listarTodos();
    }

    @PostMapping
    public TelaAgendamento salvar(@RequestBody TelaAgendamento agendamento) {
        return telaAgendamentoService.salvar(agendamento);
    }
}
