package br.com.cadastro.dominio.entidade.objetos;

import lombok.Data;

import java.util.logging.Logger;

@Data
public class Pis {

    private String numero_pis;

    public Pis(String numero_pis) {

        if (numero_pis == null || numero_pis.isEmpty()) {
            Logger.getLogger("PIS").info("Pis invalido");
            throw new IllegalArgumentException("Número do PIS não pode ser nulo ou vazio");
        }

        this.numero_pis = numero_pis;
    }

    public Pis() {
    }
}
