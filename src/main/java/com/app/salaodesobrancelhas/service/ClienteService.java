package com.app.salaodesobrancelhas.service;


import com.app.salaodesobrancelhas.entity.Cliente;
import com.app.salaodesobrancelhas.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            // Atualizando os dados do cliente (ajustando conforme necessário)
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setObservacao(clienteAtualizado.getObservacao());
            cliente.setDataCadastro(clienteAtualizado.getDataCadastro());
            // Você pode atualizar outros campos conforme a necessidade
            return clienteRepository.save(cliente);
        }
        // Aqui você pode lançar uma exceção customizada ou retornar um valor nulo
        throw new RuntimeException("Cliente não encontrado para atualização");
    }

    @Transactional
    public void deletarCliente(long id){
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cliente não encontrado para exclusão");
        }
    }

    public List<Cliente> buscarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClientePorId(long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente nao encotrado com ID: " + id));
    }

    public List<Cliente> buscarClientesPorNome(String nome){
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Cliente> buscarClientesPorTelefone(String telefone) {
        return clienteRepository.findByTelefoneContaining(telefone);
    }
}
