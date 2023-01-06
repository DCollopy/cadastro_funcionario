package br.com.cadastro.web.model;

import lombok.Data;

@Data
public class RelatorioDecimoTerceiroDTO {
    private String titulo;

    private FuncionarioDTO funcionario;

    private double salarioBrutoDecimoTerceiro;

    private double descontoIrrf;

    private double descontoInss;

    private double descontoTotal;

    private double salarioLiquido;

    private double outrosDescontos;

    private String observacoes;
}
