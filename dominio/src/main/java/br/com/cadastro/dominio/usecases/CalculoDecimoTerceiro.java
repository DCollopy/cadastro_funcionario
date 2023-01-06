package br.com.cadastro.dominio.usecases;

import java.time.LocalDate;
import java.util.logging.Logger;

public class CalculoDecimoTerceiro implements ICalculoDecimoTerceiro {

    @Override
    public double calculoDecimoTerceiro(double salarioBruto, LocalDate mesesTrabalhados) {
        //mesApenasNovembroEDezembro();
        salarioBruto = salarioBruto / 12;
        return (salarioBruto * calculoDataAdmissao(mesesTrabalhados)) / 2;
    }

    public double calculoDecimoTerceiroUnicaParcela(double salarioBruto, LocalDate mesesTrabalhados) {
        //mesApenasNovembroEDezembro();
        salarioBruto = salarioBruto / 12;
        int mes = calculoDataAdmissao(mesesTrabalhados);
        return salarioBruto * mes;
    }

    private int calculoDataAdmissao(LocalDate dataAdmissao) {
        LocalDate dataAtual = LocalDate.of(2022, 12, 31);
        //return LocalDate.now().until(dataAdmissao).getMonths();
        int mes = dataAdmissao.until(dataAtual).getMonths();
        return mes;
    }

    private void mesApenasNovembroEDezembro() {
        if (LocalDate.now().getMonthValue() != 11 || LocalDate.now().getMonthValue() != 12) {
            Logger.getLogger("CalculoDecimoTerceiro").info("Apenas os meses de novembro e dezembro são válidos para o cálculo do décimo terceiro!");
            throw new IllegalArgumentException("Apenas os meses de novembro e dezembro são válidos para o cálculo do décimo terceiro!");
        }
    }
}