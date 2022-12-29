package br.com.cadastro.usecases;

import br.com.cadastro.domain.entities.RelatorioMensal;
import java.time.LocalDate;
import java.util.logging.Logger;

import static br.com.cadastro.usecases.CalculoInss.calculaInss;
import static br.com.cadastro.usecases.CalculoIrrf.calculoIRRF;

public abstract class RelatorioMensalValida {
    private final LocalDate mesRelatorio = LocalDate.now();

    public boolean validaExiteRelatorioGerado(RelatorioMensal relatorioMensal) {
        if (relatorioMensal.getMesRelatorio()
                .getMonth().equals(mesRelatorio.getMonth()) && relatorioMensal.getMesRelatorio()
                .getYear() == mesRelatorio.getYear()) {
            Logger.getLogger("RELATORIO MENSAL").info("Relatorio ja gerado para o mes atual");
            return true;
        }
        return false;
    }

    public RelatorioMensal criaRelatorioMensal(RelatorioMensal relatorioMensal) {
        try {

            relatorioMensal.setDescontoInss(calculaDescontoInss(relatorioMensal.getFuncionario().getSalario_bruto().getSalario_bruto()));
            relatorioMensal.setDescontoIrrf(calculoImpostoRenda(relatorioMensal.getFuncionario().getSalario_bruto().getSalario_bruto(), relatorioMensal.getFuncionario().getDependentes(),relatorioMensal.getDescontoInss()));
            relatorioMensal.setDescontoTotal(somaDescontos(relatorioMensal.getDescontoInss(), relatorioMensal.getDescontoIrrf()));
            relatorioMensal.setSalarioLiquido(calculaSalarioLiquido(relatorioMensal.getFuncionario().getSalario_bruto().getSalario_bruto(), relatorioMensal.getDescontoTotal(), relatorioMensal.getOutrosDescontos()));

            RelatorioMensal relatorio = new RelatorioMensal(relatorioMensal.getFuncionario()
                    , relatorioMensal.getDescontoInss()
                    , relatorioMensal.getDescontoIrrf()
                    , relatorioMensal.getDescontoTotal()
                    , relatorioMensal.getSalarioLiquido()
                    , relatorioMensal.getOutrosDescontos()
                    , relatorioMensal.getObservacoes());

            Logger.getLogger("RELATORIO MENSAL").info("Relatorio Mensal Gerado com Sucesso");
            return relatorio;
        } catch (Exception e) {
            Logger.getLogger("RELATORIO MENSAL").info("Erro ao Gerar Relatorio Mensal");
            throw new IllegalArgumentException("Erro ao criar relatorio mensal");
        }
    }

    public RelatorioMensal editaRelatorioMensal(RelatorioMensal relatorioMensal) {
        try {
            RelatorioMensal relatorio = new RelatorioMensal(relatorioMensal.getFuncionario(), relatorioMensal.getDescontoIrrf()
                    , relatorioMensal.getDescontoInss(), relatorioMensal.getDescontoTotal(), relatorioMensal.getSalarioLiquido()
                    , relatorioMensal.getOutrosDescontos(), relatorioMensal.getObservacoes());
            Logger.getLogger("RELATORIO MENSAL").info("Relatorio Mensal Editar com Sucesso");
            return relatorio;
        } catch (Exception e) {
            Logger.getLogger("RELATORIO MENSAL").info("Erro ao Editar Relatorio Mensal");
            throw new IllegalArgumentException("Erro ao editar relatorio mensal");
        }
    }

    public double calculaDescontoInss(double salarioBruto) {
        if (salarioBruto > 0) {
            return calculaInss(salarioBruto);
        } else {
            Logger.getLogger("RELATORIO MENSAL").info("Salario Bruto Invalido");
            throw new IllegalArgumentException("Salario Bruto Invalido");
        }
    }

    public double calculoImpostoRenda(double salarioBruto, int dependentes, double inss) {
        if (salarioBruto < 0) {
            Logger.getLogger("RELATORIO MENSAL").info("Salario Bruto Invalido");
            throw new IllegalArgumentException("Salario Bruto Invalido");
        }
        if (inss < 0) {
            Logger.getLogger("RELATORIO MENSAL").info("Desconto INSS Invalido");
            throw new IllegalArgumentException("Desconto INSS Invalido");
        }
        double salario = salarioBruto - inss;
        return calculoIRRF(salario, dependentes);
    }

    public double somaDescontos(double inss, double irrf) {
        if(inss < 0) {
            Logger.getLogger("RELATORIO MENSAL").info("Desconto INSS Invalido");
            throw new IllegalArgumentException("Desconto INSS Invalido");
        }
        if(irrf < 0) {
            Logger.getLogger("RELATORIO MENSAL").info("Desconto IRRF Invalido");
            throw new IllegalArgumentException("Desconto IRRF Invalido");
        }
        return inss + irrf;
    }

    public double calculaSalarioLiquido(double salarioBruto, double descontoTotal, double outrosDescontos) {
        if(salarioBruto < 0) {
            Logger.getLogger("RELATORIO MENSAL").info("Salario Bruto Invalido");
            throw new IllegalArgumentException("Salario Bruto Invalido");
        }
        if(descontoTotal < 0) {
            Logger.getLogger("RELATORIO MENSAL").info("Desconto Total Invalido");
            throw new IllegalArgumentException("Desconto Total Invalido");
        }
        return salarioBruto - descontoTotal - outrosDescontos;
    }
}