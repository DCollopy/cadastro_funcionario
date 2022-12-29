package br.com.cadastro.dominio.entidade.objetos;

import lombok.Data;

import java.util.logging.Logger;

@Data
public class Salario {
    private double salario_bruto;

    public Salario() {
    }
    public Salario(double salario_bruto) {
        if (salario_bruto < 0) {
            Logger.getLogger("SALARIO").info("Salario invalido");
            throw new IllegalArgumentException("Salario invalido");
        }
        this.salario_bruto = salario_bruto;
    }
}