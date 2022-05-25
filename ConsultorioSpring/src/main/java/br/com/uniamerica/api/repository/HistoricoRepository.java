package br.com.uniamerica.api.repository;

import br.com.uniamerica.api.entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    @Modifying
    @Query("UPDATE Historico historico " +
            "SET historico.excluido = :data " +
            "WHERE historico.id = :historico")
    public void updateStatus(@Param("historico") Long historico,
                             @Param("data") LocalDateTime data);
}
