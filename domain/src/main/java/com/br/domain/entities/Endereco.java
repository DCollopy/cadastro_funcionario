package com.br.domain.entities;

import lombok.Data;

@Data
public class Endereco {

    private String rua;

    private String numero;

    private String bairro;

    private String cidade;


    public Endereco(String rua, String numero, String bairro, String cidade) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
    }
}
