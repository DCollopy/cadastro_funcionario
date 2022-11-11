package com.br.domain.entities;

import lombok.Data;

@Data
public class Pis {

    private String numero_pis;

    public Pis(String numero_pis) {

        if(numero_pis == null || numero_pis.isEmpty()) {
            throw new IllegalArgumentException("Número do PIS não pode ser nulo ou vazio");
        }

        this.numero_pis = numero_pis;
    }
}
