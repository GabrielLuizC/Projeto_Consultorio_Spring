package br.com.uniamerica.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historicos", schema = "public")
public class Historico extends AbstractEntity {

    @Getter @Setter
    @Column(name="observacao", nullable = true)
    private String observacao;

    @Getter @Setter
    @Column(name="data", nullable = false)
    private LocalDateTime data;

    @Getter @Setter
    @Column(name="status_agenda", nullable = false)
    private StatusAgenda statusAgenda;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "secretaria")
    private Secretaria secretaria;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "paciente")
    private Paciente paciente;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "agenda")
    private Agenda agenda;
}
