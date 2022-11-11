package com.br.domain.entities;

import lombok.Data;

@Data
public class Cpf {

    private String numero;

    public Cpf(String numero) {
        if(numero == null || !numero.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new IllegalArgumentException("CPF inválido");
        }

        this.numero = numero;
    }


}
