package com.app.salaodesobrancelhas.controller;

import com.app.salaodesobrancelhas.entity.Agendamento;
import com.app.salaodesobrancelhas.service.AgendaService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agenda")
@CrossOrigin("*")
public class AgendamentoController {
    private final AgendaService agendaService;

    public AgendamentoController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    public List<Agendamento> listarAgendamentoDoDia() {
        return agendaService.listarAgendamentoDoDia();
    }

    @PostMapping
    public ResponseEntity<?> novoAgendamento(@RequestBody Map<String, String> dados) {
        try {
            Long clienteId = Long.parseLong(dados.get("clienteId"));
            Long servicoId = Long.parseLong(dados.get("servicoId"));
            LocalDate data = LocalDate.parse(dados.get("data"));
            LocalTime hora = LocalTime.parse(dados.get("hora"));

            agendaService.agendar(clienteId, servicoId, data, hora);
            return ResponseEntity.ok(Map.of("mensagem", "Agendamento criado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}
