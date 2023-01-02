package br.com.cadastro.dominio.usecases;

public interface ICalculoInss {

    double calculaInss(double salarioBruto);

    double calculaDescontoInss(double salarioBruto);
}
