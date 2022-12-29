package br.com.cadastro.dominio.usecases;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static br.com.cadastro.dominio.entidade.objetos.Irrf.*;

public class CalculoIrrf {

    public static double calculoIRRF(double salarioBruto, int dependentes) {
        double imposto = 0;

        if (dependentes > 0) {
            salarioBruto = salarioBruto - (dependentes * irrf_dependente);
        }
        if (salarioBruto <= valor_minimo) {
            return imposto;
        } else if (salarioBruto >= valor_minimo && salarioBruto <= valor_um) {
            imposto = (salarioBruto * irrf_tabela1) - irrf_taxa1;
        } else if (salarioBruto >= valor_um && salarioBruto <= valor_dois) {
            imposto = (salarioBruto * irrf_tabela2) - irrf_taxa2;
        } else if (salarioBruto >= valor_dois && salarioBruto <= valor_maximo) {
            imposto = (salarioBruto * irrf_tabela3) - irrf_taxa3;
        } else {
            imposto = (salarioBruto * irrf_tabela4) - irrf_taxa4;
        }

        BigDecimal bd = new BigDecimal(imposto).setScale(3, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }
}