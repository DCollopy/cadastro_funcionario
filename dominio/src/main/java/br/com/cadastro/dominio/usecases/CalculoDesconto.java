package br.com.cadastro.dominio.usecases;

import java.util.logging.Logger;
public class CalculoDesconto implements ICalculoDesconto {

    public double somaDescontos(double inss, double irrf) {
        if (inss < 0) {
            Logger.getLogger("RELATORIOS").info("Desconto INSS Invalido");
            throw new IllegalArgumentException("Desconto INSS Invalido");
        }
        if (irrf < 0) {
            Logger.getLogger("RELATORIOS").info("Desconto IRRF Invalido");
            throw new IllegalArgumentException("Desconto IRRF Invalido");
        }
        return inss + irrf;
    }
}
