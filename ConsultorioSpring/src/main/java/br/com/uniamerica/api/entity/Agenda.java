package br.com.uniamerica.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendas", schema = "public")
public class Agenda extends AbstractEntity {

    @Getter @Setter
    @Column(name="status_agenda", nullable = false)
    private StatusAgenda statusAgenda;

    @Getter @Setter
    @Column(name="data_agenda", nullable = false)
    private LocalDateTime dataAgenda;

    @Getter @Setter
    @Column(name="encaixe", nullable = false)
    private Boolean encaixe;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Medico medico;
}
