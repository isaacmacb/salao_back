package com.app.salaodesobrancelhas.repository;

import com.app.salaodesobrancelhas.entity.Agendamento;
import com.app.salaodesobrancelhas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByData(LocalDate data); // por dia

    List<Agendamento> findByDataBetween(LocalDate inicio, LocalDate fim); // por semana/mês

    boolean existsByDataAndHora(LocalDate data, LocalTime hora); // verificar se horário está ocupado
}

