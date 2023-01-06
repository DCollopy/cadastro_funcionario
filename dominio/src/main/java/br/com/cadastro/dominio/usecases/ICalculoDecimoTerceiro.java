package br.com.cadastro.dominio.usecases;

import java.time.LocalDate;

public interface ICalculoDecimoTerceiro {
    double calculoDecimoTerceiro(double salarioBruto, LocalDate mesesTrabalhados);

    double calculoDecimoTerceiroUnicaParcela(double salarioBruto, LocalDate mesesTrabalhados);
}
