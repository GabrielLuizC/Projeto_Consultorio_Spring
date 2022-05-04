package br.com.uniamerica.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Pessoa extends AbstractEntity {

    @Getter @Setter
    @Column(name="nome", nullable = false)
    private String nome;

    @Getter @Setter
    @Column(name="telefone", nullable = false)
    private String telefone;

    @Getter @Setter
    @Column(name="celular", nullable = false)
    private String celular;

    @Getter @Setter
    @Column(name="nacionalidade", nullable = false)
    private String nacionalidade;

    @Getter @Setter
    @Column(name="cpf", nullable = false)
    private String cpf;

    @Getter @Setter
    @Column(name="rg", nullable = false)
    private String rg;

    @Getter @Setter
    @Column(name="email", nullable = false)
    private String email;

    @Getter @Setter
    @Column(name="login", nullable = false)
    private String login;

    @Getter
    @Column(name="senha", nullable = false)
    private String senha;

    @Getter @Setter
    @Column(name="sexo", nullable = false)
    private Sexo sexo;
}
