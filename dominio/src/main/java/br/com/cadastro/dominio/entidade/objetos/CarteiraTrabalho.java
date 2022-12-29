package br.com.cadastro.dominio.entidade.objetos;

import lombok.Data;

import java.util.logging.Logger;

@Data
public class CarteiraTrabalho {

    private String numero_trabalho;

    public CarteiraTrabalho(String numero_trabalho) {

        if(numero_trabalho == null || numero_trabalho.isEmpty()) {
            Logger.getLogger("CARTEIRA DE TRABALHO").info("Carteira de trabalho invalido");
            throw new IllegalArgumentException("Número da carteira de trabalho não pode ser nulo ou vazio");
        }

        this.numero_trabalho = numero_trabalho;
    }
}
