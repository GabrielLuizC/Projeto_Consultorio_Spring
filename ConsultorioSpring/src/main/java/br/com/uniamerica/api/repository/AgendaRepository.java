package br.com.uniamerica.api.repository;

import br.com.uniamerica.api.entity.Agenda;
import br.com.uniamerica.api.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    @Modifying
    @Query("UPDATE Agenda agenda " +
            "SET agenda.excluido = :data " +
            "WHERE agenda.id = :agenda")

    public void updateStatus(
            @Param("agenda") Long agenda,
            @Param("data") LocalDateTime data);

    @Query("SELECT agenda.id " +
            "FROM Agenda agenda " +
            "WHERE agenda.dataAgenda = :dataAgendamentos")
    public List<Agenda> verificaAgenda(
            @Param("dataAgendamentos") LocalDateTime dataAgendamentos);

}

