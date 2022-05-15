package br.com.uniamerica.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    @Getter @Setter
    @JoinColumn(name = "convenio")
    private Convenio convenio;
}
