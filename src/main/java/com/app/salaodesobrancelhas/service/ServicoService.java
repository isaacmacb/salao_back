package com.app.salaodesobrancelhas.service;

import com.app.salaodesobrancelhas.entity.Servico;
import com.app.salaodesobrancelhas.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {
    private final ServicoRepository  servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> buscarPorId(Long id) {
        return servicoRepository.findById(id);
    }

    public Servico salvar(Servico servico) {
        return servicoRepository.save(servico);
    }

    public Servico atualizar(Long id, Servico novoServico) {
        return servicoRepository.findById(id).map(servico -> {
            servico.setNome(novoServico.getNome());
            servico.setValor(novoServico.getValor());
            servico.setDuracao(novoServico.getDuracao());
            return servicoRepository.save(servico);
        }).orElseThrow(() -> new RuntimeException("Serviço não encontrado com ID: " + id));
    }

    public void deletar(Long id) {
        servicoRepository.deleteById(id);
    }
}
