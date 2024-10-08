package com.gustavolr.dominio;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Mentoria extends Conteudo{

    private LocalDate data;

    @Override
    public double calcularXp() {
        return xp + 20d;
    }

    @Override
    public String toString() {
        return "Mentoria [titulo=" + titulo + ", descricao=" + descricao + ", data=" + data + ", xp=" + xp + "]";
    }
}
