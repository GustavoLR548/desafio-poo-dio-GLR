package com.gustavolr.dominio;

import lombok.Data;

@Data
public abstract class Conteudo {

    protected static final double XP_PADRAO = 10d;

    protected String titulo;
    protected String descricao;

    protected double xp = XP_PADRAO;

    public abstract double calcularXp();
}
