package br.com.cadastro.dominio.usecases;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Logger;

import static br.com.cadastro.dominio.entidade.objetos.Inss.*;

public class CalculoInss implements ICalculoInss {

    @Override
    public double calculaInss(double salarioBruto) {
        double inss = 0;
        double valor;

        if (salarioBruto <= inss_valor_minimo) {
            inss = inss_valor_minimo * inss_tabela1;
        } else if (salarioBruto > inss_valor_minimo && salarioBruto <= inss_valor_segundo) {
            valor = salarioBruto - inss_valor_minimo;
            inss = (valor * inss_tabela2) + inss_deducao1;
        } else if (salarioBruto > inss_valor_segundo && salarioBruto <= inss_valor_terceiro) {
            valor = salarioBruto - inss_valor_segundo;
            inss = (valor * inss_tabela3) + inss_deducao1 + inss_deducao2;
        } else if (salarioBruto > inss_valor_terceiro && salarioBruto <= inss_valor_maximo) {
            valor = salarioBruto - inss_valor_terceiro;
            inss = (valor * inss_tabela4) + inss_deducao1 + inss_deducao2 + inss_deducao3;
        } else {
            valor = salarioBruto - inss_valor_maximo;
            inss = (valor * inss_tabela4) + inss_deducao1 + inss_deducao2 + inss_deducao3;
        }

        BigDecimal bd = new BigDecimal(inss).setScale(3, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    @Override
    public double calculaDescontoInss(double salarioBruto) {
        if (salarioBruto > 0) {
            return calculaInss(salarioBruto);
        } else {
            Logger.getLogger("RELATORIOS").info("Salario Bruto Invalido");
            throw new IllegalArgumentException("Salario Bruto Invalido");
        }
    }
}