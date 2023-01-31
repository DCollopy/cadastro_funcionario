package br.com.cadastro.dominio.entidade;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.logging.Logger;

@Data
@SuperBuilder
public class RelatorioMensal {

    private final String titulo = "Relatorio Mensal";

    private final LocalDate mesRelatorio = LocalDate.now();
    private Funcionario funcionario;
    private double descontoIrrf;
    private double descontoInss;
    private double descontoTotal;
    private double salarioLiquido;
    private double outrosDescontos;
    private String observacoes;
    public RelatorioMensal(){}

    public RelatorioMensal(Funcionario funcionario
            , double descontoInss , double descontoIrrf
            , double descontoTotal, double salarioLiquido
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
        this.descontoIrrf = descontoIrrf;
        this.descontoInss = descontoInss;
        this.descontoTotal = descontoTotal;
        this.salarioLiquido = salarioLiquido;
        this.outrosDescontos = outrosDescontos;
        this.observacoes = observacoes;
    }

    public RelatorioMensal(Funcionario funcionario, double outrosDescontos, String observacoes) {
        if(funcionario == null){
            Logger.getLogger("RELATORIO MENSAL").info("Funcionario Invalido");
            throw new IllegalArgumentException("Funcionario Invalido");
        }
        this.funcionario = funcionario;
        this.outrosDescontos = outrosDescontos;
        this.observacoes = observacoes;
    }
}