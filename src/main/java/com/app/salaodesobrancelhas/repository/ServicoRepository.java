package com.app.salaodesobrancelhas.repository;

import com.app.salaodesobrancelhas.entity.Servico;
import com.app.salaodesobrancelhas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
