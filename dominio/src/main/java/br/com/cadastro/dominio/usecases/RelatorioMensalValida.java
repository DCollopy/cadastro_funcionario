package br.com.cadastro.dominio.usecases;

import br.com.cadastro.dominio.entidade.RelatorioMensal;
import br.com.cadastro.dominio.usecases.excel.GerarExcelSalario;
import br.com.cadastro.dominio.usecases.excel.IGerarExcelSalario;
import br.com.cadastro.dominio.usecases.excel.ILerExcel;

import java.time.LocalDate;
import java.util.logging.Logger;

public abstract class RelatorioMensalValida {
    private final LocalDate mesRelatorio = LocalDate.now();
    private final ICalculoInss calculoINSS = new CalculoInss();
    private final ICaculoIrrf calculoIRRF = new CalculoIrrf();
    private final ICalculoDesconto calculoDesconto = new CalculoDesconto();
    private final ICalculoSalarioLiquido calculoSalario = new CalculoSalarioLiquido();

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
            double salarioBruto = relatorioMensal.getFuncionario().getSalario_bruto().getSalario_bruto();
            calculoSalario.validaSalarioBruto(salarioBruto);

            relatorioMensal.setDescontoInss(calculoINSS.calculaDescontoInss(salarioBruto));
            relatorioMensal.setDescontoIrrf(calculoIRRF.calculoImpostoRenda(salarioBruto, relatorioMensal.getFuncionario().getDependentes(), relatorioMensal.getDescontoInss()));
            relatorioMensal.setDescontoTotal(calculoDesconto.somaDescontos(relatorioMensal.getDescontoInss(), relatorioMensal.getDescontoIrrf()));
            relatorioMensal.setSalarioLiquido(calculoSalario.calculaSalarioLiquido(salarioBruto, relatorioMensal.getDescontoTotal(), relatorioMensal.getOutrosDescontos()));

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
            RelatorioMensal relatorio =new RelatorioMensal(relatorioMensal.getFuncionario(), relatorioMensal.getDescontoIrrf()
                    , relatorioMensal.getDescontoInss(), relatorioMensal.getDescontoTotal(), relatorioMensal.getSalarioLiquido()
                    , relatorioMensal.getOutrosDescontos(), relatorioMensal.getObservacoes());
            Logger.getLogger("RELATORIO MENSAL").info("Relatorio Mensal Editar com Sucesso");
            return relatorio;
        } catch (Exception e) {
            Logger.getLogger("RELATORIO MENSAL").info("Erro ao Editar Relatorio Mensal");
            throw new IllegalArgumentException("Erro ao editar relatorio mensal");
        }
    }
}