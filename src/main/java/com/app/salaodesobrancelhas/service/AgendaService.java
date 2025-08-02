package com.app.salaodesobrancelhas.service;

import com.app.salaodesobrancelhas.entity.Agendamento;
import com.app.salaodesobrancelhas.entity.Cliente;
import com.app.salaodesobrancelhas.entity.Servico;
import com.app.salaodesobrancelhas.repository.AgendamentoRepository;
import com.app.salaodesobrancelhas.repository.ClienteRepository;
import com.app.salaodesobrancelhas.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AgendaService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;

    public AgendaService(AgendamentoRepository agendamentoRepository,
                         ClienteRepository clienteRepository,
                         ServicoRepository servicoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
        this.servicoRepository = servicoRepository;
    }

    public void agendar(Long clienteId, Long servicoId, LocalDate data, LocalTime hora) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Servico servico = servicoRepository.findById(servicoId)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setServico(servico);
        agendamento.setData(data);
        agendamento.setHora(hora);

        agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }
}
