package com.app.salaodesobrancelhas.service;

import com.app.salaodesobrancelhas.entity.TelaAgendamento;
import com.app.salaodesobrancelhas.repository.TelaAgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelaAgendamentoService {
    private final TelaAgendamentoRepository telaAgendamentoRepository;

    public TelaAgendamentoService(TelaAgendamentoRepository telaAgendamentoRepository) {
        this.telaAgendamentoRepository = telaAgendamentoRepository;
    }

    public List<TelaAgendamento> listarTodos() {
        return telaAgendamentoRepository.findAll();
    }

    public TelaAgendamento salvar(TelaAgendamento agendamento) {
        return telaAgendamentoRepository.save(agendamento);
    }
}
