package br.com.uniamerica.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "secretarias", schema = "public")
public class Secretaria extends Pessoa {

    @Getter @Setter
    @Column(name="salario", nullable = false)
    private BigDecimal salario;

    @Getter @Setter
    @Column(name="data_contratacao", nullable = false)
    private LocalDateTime dataContratacao;

    @Getter @Setter
    @Column(name="pis", nullable = false)
    private String pis;

}
