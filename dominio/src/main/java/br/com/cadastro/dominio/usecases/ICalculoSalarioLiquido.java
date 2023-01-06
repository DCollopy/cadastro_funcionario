package br.com.cadastro.dominio.usecases;

public interface ICalculoSalarioLiquido {

    double calculaSalarioLiquido(double salarioBruto, double descontoTotal, double outrosDescontos);

    void validaSalarioBruto(double salarioBruto);
}
