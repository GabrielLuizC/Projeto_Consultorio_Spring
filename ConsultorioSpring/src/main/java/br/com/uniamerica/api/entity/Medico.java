package br.com.uniamerica.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "medicos", schema = "public")
public class Medico extends Pessoa {

    @Getter @Setter
    @Column(name="crm", nullable = false)
    private String crm;

    @Getter @Setter
    @Column(name="porcentagem_participacao", nullable = false)
    private BigDecimal porcentagemParticipacao;

    @Getter @Setter
    @Column(name="consultorio", nullable = false)
    private String consultorio;

    @Getter @Setter
    @Column(name="valor_consulta", nullable = false)
    private BigDecimal valorConsulta;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "especialidade")
    private Especialidade especialidade;
}
