package com.br.domain.entities;

import lombok.Data;

@Data
public class Email {

    private String endereco_email;

    public Email(String endereco_email) {

        if(!endereco_email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"))  throw new IllegalArgumentException("Email inválido");

        this.endereco_email = endereco_email;
    }
}
