package br.com.uniamerica.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;

    @Getter @Setter
    @Column(name="cadastro", nullable = false)
    private LocalDateTime cadastro;

    @Getter @Setter
    @Column(name="atualizado", nullable = false)
    private LocalDateTime atualizado;

    @Getter @Setter
    @Column(name="excluido", nullable = false)
    private LocalDateTime excluido;
}
