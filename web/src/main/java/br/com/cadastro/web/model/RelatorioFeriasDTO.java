package br.com.cadastro.web.model;

import lombok.Data;

@Data
public class RelatorioFeriasDTO {
    private String titulo;
    private FuncionarioDTO funcionario;
    private double descontoIrrf;
    private double descontoInss;
    private double descontoTotal;
    private double salarioLiquido;
    private double salarioBrutoFerias;
    private double outrosDescontos;
    private String observacoes;
}