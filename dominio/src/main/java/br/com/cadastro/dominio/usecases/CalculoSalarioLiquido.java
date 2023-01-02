package br.com.cadastro.dominio.usecases;

import java.util.logging.Logger;

public class CalculoSalarioLiquido {

    public double calculaSalarioLiquido(double salarioBruto, double descontoTotal, double outrosDescontos) {
        if (salarioBruto < 0) {
            Logger.getLogger("RELATORIO MENSAL").info("Salario Bruto Invalido");
            throw new IllegalArgumentException("Salario Bruto Invalido");
        }
        if (descontoTotal < 0) {
            Logger.getLogger("RELATORIO MENSAL").info("Desconto Total Invalido");
            throw new IllegalArgumentException("Desconto Total Invalido");
        }
        return salarioBruto - descontoTotal - outrosDescontos;
    }
}
