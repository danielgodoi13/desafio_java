package com.daniel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "v_time_pessoa_formacao")
public class ViewPrincipal {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_time")
    private String nomeTime;

    @Column(name = "nome_pessoa")
    private String nomePessoa;

    @Column(name = "nome_formacao")
    private String nomeFormacao;

    @Transient
    private String fatoAleatorio;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTime() {
        return nomeTime;
    }

    public void setNomeTime(String nomeTime) {
        this.nomeTime = nomeTime;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getNomeFormacao() {
        return nomeFormacao;
    }

    public void setNomeFormacao(String nomeFormacao) {
        this.nomeFormacao = nomeFormacao;
    }

    public String getFatoAleatorio() {
        return fatoAleatorio;
    }

    public void setFatoAleatorio(String fatoAleatorio) {
        this.fatoAleatorio = fatoAleatorio;
    }
}
