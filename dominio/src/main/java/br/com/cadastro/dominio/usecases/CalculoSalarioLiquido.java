package br.com.cadastro.dominio.usecases;

import java.util.logging.Logger;

public class CalculoSalarioLiquido implements ICalculoSalarioLiquido {

    public double calculaSalarioLiquido(double salarioBruto, double descontoTotal, double outrosDescontos) {
        validaSalarioBruto(salarioBruto);
        if (descontoTotal < 0) {
            Logger.getLogger("RELATORIO MENSAL").info("Desconto Total Invalido");
            throw new IllegalArgumentException("Desconto Total Invalido");
        }
        return salarioBruto - descontoTotal - outrosDescontos;
    }

    public void validaSalarioBruto(double salarioBruto) {
        if (salarioBruto <= 0) {
            Logger.getLogger("RELATORIOs").info("Salario Bruto Invalido");
            throw new IllegalArgumentException("Salario Bruto Invalido");
        }
    }
}
