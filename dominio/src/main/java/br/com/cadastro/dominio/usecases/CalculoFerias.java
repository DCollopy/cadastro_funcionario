package br.com.cadastro.dominio.usecases;

public class CalculoFerias implements ICalculoFerias {

    public double calculaFerias(double salarioBruto){
        salarioBruto += salarioBruto/3; // 1/3 de férias
        return salarioBruto;
    }
}
