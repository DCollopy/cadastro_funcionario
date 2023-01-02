package br.com.cadastro.dominio.usecases;

import br.com.cadastro.dominio.entidade.RelatorioMensal;

import java.time.LocalDate;
import java.util.logging.Logger;

public abstract class RelatorioMensalValida {
    private final LocalDate mesRelatorio = LocalDate.now();
    private  ICaculoIrrf calculoIRRF;
    private  CalculoInss calculoINSS;
    private  ICalculoDesconto calculoDesconto;
    private  ICalculoSalarioLiquido calculoSalario;


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

            relatorioMensal.setDescontoInss(calculoINSS.calculaDescontoInss(relatorioMensal.getFuncionario().getSalario_bruto().getSalario_bruto()));
            relatorioMensal.setDescontoIrrf(calculoIRRF.calculoImpostoRenda(relatorioMensal.getFuncionario().getSalario_bruto().getSalario_bruto(), relatorioMensal.getFuncionario().getDependentes(), relatorioMensal.getDescontoInss()));
            relatorioMensal.setDescontoTotal(calculoDesconto.somaDescontos(relatorioMensal.getDescontoInss(), relatorioMensal.getDescontoIrrf()));
            relatorioMensal.setSalarioLiquido(calculoSalario.calculaSalarioLiquido(relatorioMensal.getFuncionario().getSalario_bruto().getSalario_bruto(), relatorioMensal.getDescontoTotal(), relatorioMensal.getOutrosDescontos()));

            RelatorioMensal relatorio =new RelatorioMensal(relatorioMensal.getFuncionario()
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