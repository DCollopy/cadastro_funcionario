package br.com.cadastro.web.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RelatorioMensalDTO {

    private String titulo;

    private LocalDate mesRelatorio;

    private FuncionarioDTO funcionario;

    private double descontoIrrf;

    private double descontoInss;

    private double descontoTotal;

    private double salarioLiquido;

    private double outrosDescontos;

    private String observacoes;
}
