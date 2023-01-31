package br.com.cadastro.dominio.entidade;

import lombok.Data;

import java.time.LocalDate;
import java.util.logging.Logger;

@Data
public class RelatorioFerias {

    private final String titulo = "Relatorio de Ferias";
    private Funcionario funcionario;
    private double descontoIrrf;
    private double descontoInss;
    private double descontoTotal;
    private double salarioLiquido;
    private double salarioBrutoFerias;
    private double outrosDescontos;
    private String observacoes;

    private final LocalDate dataInicioFerias = LocalDate.now();
    public RelatorioFerias(){}
    public RelatorioFerias(Funcionario funcionario, double descontoInss
            , double descontoIrrf, double descontoTotal
            , double salarioLiquido, double salarioBrutoFerias
            , double outrosDescontos, String observacoes) {

        if(funcionario == null){
            Logger.getLogger("RELATORIO MENSAL").info("Funcionario Invalido");
            throw new IllegalArgumentException("Funcionario Invalido");
        }
        if(descontoInss < 0){
            Logger.getLogger("RELATORIO MENSAL").info("Desconto Inss Invalido");
            throw new IllegalArgumentException("Desconto Inss Invalido");
        }
        this.funcionario = funcionario;
        this.descontoInss = descontoInss;
        this.descontoIrrf = descontoIrrf;
        this.descontoTotal = descontoTotal;
        this.salarioLiquido = salarioLiquido;
        this.salarioBrutoFerias = salarioBrutoFerias;
        this.outrosDescontos = outrosDescontos;
        this.observacoes = observacoes;
    }

    public RelatorioFerias(Funcionario funcionario, double outrosDescontos, String observacoes) {
        if(funcionario == null){
            Logger.getLogger("RELATORIO MENSAL").info("Funcionario Invalido");
            throw new IllegalArgumentException("Funcionario Invalido");
        }
        this.funcionario = funcionario;
        this.outrosDescontos = outrosDescontos;
        this.observacoes = observacoes;
    }
}