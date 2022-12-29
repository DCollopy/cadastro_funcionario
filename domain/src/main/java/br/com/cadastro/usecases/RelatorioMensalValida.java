package br.com.cadastro.usecases;

import br.com.cadastro.domain.entities.RelatorioMensal;

import java.time.LocalDate;
import java.util.logging.Logger;

import static br.com.cadastro.usecases.CalculoInss.calculaInss;

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
            RelatorioMensal relatorio = new RelatorioMensal(relatorioMensal.getFuncionario(), relatorioMensal.getDescontoIrrf()
                    , calculaDescontoInss(relatorioMensal.getDescontoInss()), relatorioMensal.getBeneficiarios()
                    , relatorioMensal.getDescontoTotal(), relatorioMensal.getSalarioLiquido()
                    , relatorioMensal.getOutrosDescontos(), relatorioMensal.getObservacoes());

            Logger.getLogger("RELATORIO MENSAL").info("Relatorio Mensal Gerado com Sucesso");
            return relatorio;
        } catch (Exception e) {
            Logger.getLogger("RELATORIO MENSAL").info("Erro ao Gerar Relatorio Mensal");
            throw new RuntimeException("Erro ao criar relatorio mensal");
        }
    }

    public RelatorioMensal editaRelatorioMensal(RelatorioMensal relatorioMensal) {
        try {
            RelatorioMensal relatorio = new RelatorioMensal(relatorioMensal.getFuncionario(), relatorioMensal.getDescontoIrrf()
                    , relatorioMensal.getDescontoInss(), relatorioMensal.getBeneficiarios()
                    , relatorioMensal.getDescontoTotal(), relatorioMensal.getSalarioLiquido()
                    , relatorioMensal.getOutrosDescontos(), relatorioMensal.getObservacoes());
            Logger.getLogger("RELATORIO MENSAL").info("Relatorio Mensal Editar com Sucesso");
            return relatorio;
        } catch (Exception e) {
            Logger.getLogger("RELATORIO MENSAL").info("Erro ao Editar Relatorio Mensal");
            throw new RuntimeException("Erro ao editar relatorio mensal");
        }
    }

    public double calculaDescontoInss(double salarioBruto) {
        if (salarioBruto > 0) {
            return calculaInss(salarioBruto);
        } else {
            Logger.getLogger("RELATORIO MENSAL").info("Salario Bruto Invalido");
            throw new RuntimeException("Salario Bruto Invalido");
        }
    }

    public double calculoImpostoRenda(double salarioBruto) {
        return 0;
    }
}