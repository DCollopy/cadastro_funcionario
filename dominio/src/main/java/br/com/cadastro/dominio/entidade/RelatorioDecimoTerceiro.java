package br.com.cadastro.dominio.entidade;

import lombok.Data;

@Data
public class RelatorioDecimoTerceiro {

    private final String titulo = "Relatorio de Decimo Terceiro";

    private Funcionario funcionario;

    private double salarioBrutoDecimoTerceiro;

    private double descontoIrrf;

    private double descontoInss;

    private double descontoTotal;

    private double salarioLiquido;

    private double outrosDescontos;

    private String observacoes;

    public RelatorioDecimoTerceiro() {
    }

    public RelatorioDecimoTerceiro(Funcionario funcionario, double descontoIrrf
            , double descontoInss, double descontoTotal, double salarioLiquido
            , double outrosDescontos, String observacoes) {
        this.funcionario = funcionario;
        this.descontoIrrf = descontoIrrf;
        this.descontoInss = descontoInss;
        this.descontoTotal = descontoTotal;
        this.salarioLiquido = salarioLiquido;
        this.outrosDescontos = outrosDescontos;
        this.observacoes = observacoes;
    }

    public RelatorioDecimoTerceiro(double salarioBrutoDecimoTerceiro) {
        this.salarioBrutoDecimoTerceiro = salarioBrutoDecimoTerceiro;
    }

    public RelatorioDecimoTerceiro(Funcionario funcionario, double outrosDescontos, String observacoes) {
        this.funcionario = funcionario;
        this.outrosDescontos = outrosDescontos;
        this.observacoes = observacoes;
    }
}