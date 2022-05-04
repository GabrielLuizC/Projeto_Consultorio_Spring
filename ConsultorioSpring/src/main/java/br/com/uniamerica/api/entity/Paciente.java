package br.com.uniamerica.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "pacientes", schema = "public")
public class Paciente extends Pessoa {

    @Getter @Setter
    @Column(name="tipo_atendimento", nullable = false)
    private TipoAtendimento tipoAtendimento;

    @Getter @Setter
    @Column(name="numero_cartao_convenio", nullable = false)
    private String numeroCartaoConvenio;

    @Getter @Setter
    @Column(name="data_vencimento", nullable = false)
    private LocalDateTime dataVencimento;

    @ManyToOne
    private Convenio convenio;

}
