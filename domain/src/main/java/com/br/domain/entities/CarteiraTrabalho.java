package com.br.domain.entities;

import lombok.Data;

@Data
public class CarteiraTrabalho {

    private String numero_trabalho;

    public CarteiraTrabalho(String numero_trabalho) {

        if(numero_trabalho == null || numero_trabalho.isEmpty()) {
            throw new IllegalArgumentException("Número da carteira de trabalho não pode ser nulo ou vazio");
        }

        this.numero_trabalho = numero_trabalho;
    }
}
