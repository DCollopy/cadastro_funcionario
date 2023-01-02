package br.com.cadastro.dominio.usecases;

public interface ICaculoIrrf {

    double calculaIRRF(double salarioBruto, int dependentes);

    double calculoImpostoRenda(double salarioBruto, int dependentes, double inss);

}
