package br.com.cadastro.domain.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RelatorioMensal {

    private final String titulo = "Relatorio Mensal";

    private final LocalDate mesRelatorio = LocalDate.now();

    private Funcionario funcionario;

    private double descontoIrrf;

    private double descontoInss;

    private int beneficiarios;

    private double descontoTotal;

    private double salarioLiquido;

    private double outrosDescontos;

    private String observacoes;

    public RelatorioMensal(){}

    public RelatorioMensal(Funcionario funcionario, double descontoIrrf
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
