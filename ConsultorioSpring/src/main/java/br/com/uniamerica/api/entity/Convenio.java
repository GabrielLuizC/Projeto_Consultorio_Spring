package br.com.uniamerica.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "convenios", schema = "public")
public class Convenio extends AbstractEntity {

    @Getter @Setter
    @Column(name = "nome", nullable = false, unique = true, length = 50)
    private String nome;

    @Getter @Setter
    @Column(name="valor", nullable = false)
    private BigDecimal valor;

}
