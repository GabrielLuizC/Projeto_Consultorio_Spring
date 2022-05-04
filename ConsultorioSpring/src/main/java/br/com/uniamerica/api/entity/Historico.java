package br.com.uniamerica.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    private Secretaria secretaria;

    @ManyToOne
    @Getter @Setter
    private Paciente paciente;

    @ManyToOne
    @Getter @Setter
    private Agenda agenda;
}
