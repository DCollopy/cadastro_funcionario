package br.com.cadastro.dominio.entidade;

import lombok.Data;

import java.util.logging.Logger;

@Data
public class Transporte {

    private String tipoTransporte;

    private double valorTarifaTransporte;

    private Funcionario funcionario;

    public Transporte(){}

    public Transporte(String tipoTransporte, double valorTarifaTransporte, Funcionario funcionario) {
        if( valorTarifaTransporte < 0 || funcionario == null){
            Logger.getLogger("TRANSPORTE").info("Dados invalidos");
            throw new RuntimeException("Dados invalidos");
        }
        this.tipoTransporte = tipoTransporte;
        this.valorTarifaTransporte = valorTarifaTransporte;
        this.funcionario = funcionario;
    }
}