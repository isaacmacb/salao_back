package com.app.salaodesobrancelhas.repository;

import com.app.salaodesobrancelhas.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNomeContainingIgnoreCase(String nome); // busca parcial por nome
    List<Cliente> findByTelefoneContaining(String telefone);    // busca parcial por telefone
}
