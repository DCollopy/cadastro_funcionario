package com.br.domain.entities;

import lombok.Data;

@Data
public class Telefone {

    private String ddd;

    private String numero;

    public Telefone(String ddd, String numero) {
        if(ddd == null || !ddd.matches("\\d{2}")) {
            throw new IllegalArgumentException("DDD inválido");
        }
        if(numero == null || !numero.matches("\\d{4}-\\d{4}")) {
            throw new IllegalArgumentException("Número de telefone inválido");
        }
        this.ddd = ddd;
        this.numero = numero;
    }
}
