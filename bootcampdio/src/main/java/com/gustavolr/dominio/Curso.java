package com.gustavolr.dominio;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Curso extends Conteudo{

    private int cargaHoraria;

    @Override
    public double calcularXp() {
        return xp * cargaHoraria;
    }

    @Override
    public String toString() {
        return "Curso [titulo=" + titulo + ", cargaHoraria=" + cargaHoraria + ", descricao=" + descricao + ", xp=" + xp
                + "]";
    }
}
