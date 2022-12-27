package br.com.cadastro.domain.entities;

import lombok.Data;

@Data
public class RelatorioFerias {

    private final String titulo = "Relatorio de Ferias";

    private Funcionario funcionario;

    private double descontoIrrf;

    private double descontoInss;

    private int beneficiarios;

    private double descontoTotal;

    private double salarioLiquido;

    private double outrosDescontos;

    private String observacoes;

    public RelatorioFerias(){}
    public RelatorioFerias(Funcionario funcionario, double descontoIrrf
            , double descontoInss, int beneficiarios
            , double descontoTotal, double salarioLiquido
            , double outrosDescontos, String observacoes) {
        this.funcionario = funcionario;
        this.descontoIrrf = descontoIrrf;
        this.descontoInss = descontoInss;
        this.beneficiarios = beneficiarios;
        this.descontoTotal = descontoTotal;
        this.salarioLiquido = salarioLiquido;
        this.outrosDescontos = outrosDescontos;
        this.observacoes = observacoes;
    }
}