package br.com.cadastro.domain.entities.objetos;

import lombok.Data;

import java.util.logging.Logger;

@Data
public class Cpf {

    private String cpf;

    public Cpf(String cpf) {
        if(cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            Logger.getLogger("CPF").info("CPF invalido");
            throw new IllegalArgumentException("CPF inválido");
        }

        this.cpf = cpf;
    }


}
