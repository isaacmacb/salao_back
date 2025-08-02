package com.app.salaodesobrancelhas.repository;

import com.app.salaodesobrancelhas.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
