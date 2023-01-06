package br.com.cadastro.dominio.usecases;

import br.com.cadastro.dominio.entidade.RelatorioDecimoTerceiro;

import java.time.LocalDate;
import java.util.logging.Logger;

public abstract class RelatorioDecimoTerceiroValida {

    private final ICalculoDecimoTerceiro calculoDecimoTerceiro = new CalculoDecimoTerceiro();

    private final ICalculoInss calculoINSS = new CalculoInss();

    private final ICaculoIrrf calculoIRRF = new CalculoIrrf();

    private final ICalculoDesconto calculoDesconto = new CalculoDesconto();

    private final ICalculoSalarioLiquido calculoSalario = new CalculoSalarioLiquido();

    public RelatorioDecimoTerceiro criaPrimeiraParcelaDecimo13(RelatorioDecimoTerceiro relatorioDecimoTerceiro) {

        try {

            double salarioBruto = relatorioDecimoTerceiro.getFuncionario().getSalario_bruto().getSalario_bruto();
            calculoSalario.validaSalarioBruto(salarioBruto);

            relatorioPrimeiraParcelaExiste(relatorioDecimoTerceiro);

            relatorioDecimoTerceiro.setSalarioBrutoDecimoTerceiro(calculoDecimoTerceiro.calculoDecimoTerceiro(salarioBruto
                    , relatorioDecimoTerceiro.getFuncionario().getData_admissao()));

            return new RelatorioDecimoTerceiro(relatorioDecimoTerceiro.getSalarioBrutoDecimoTerceiro());

        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
            throw new IllegalArgumentException("Erro ao validar relatório de décimo terceiro!");
        }
    }

    public RelatorioDecimoTerceiro criaSegundaParcelaDecimo13(RelatorioDecimoTerceiro relatorioDecimoTerceiro) {

        try {
            double calculoSalarioBruto = validaPrimeiraParcela(relatorioDecimoTerceiro.getSalarioBrutoDecimoTerceiro()
                    , relatorioDecimoTerceiro.getFuncionario().getData_admissao()
                    , relatorioDecimoTerceiro.getFuncionario().getSalario_bruto().getSalario_bruto());

            relatorioDecimoTerceiro.setSalarioBrutoDecimoTerceiro(calculoSalarioBruto);

            descontoInss(relatorioDecimoTerceiro);

            descontoIrrf(relatorioDecimoTerceiro);

            somaDescontosImpostos(relatorioDecimoTerceiro);

            salarioLiquido(relatorioDecimoTerceiro);

            RelatorioDecimoTerceiro relatorioDecimoTerceiroCria = new RelatorioDecimoTerceiro(
                    relatorioDecimoTerceiro.getFuncionario(), relatorioDecimoTerceiro.getDescontoInss()
                    , relatorioDecimoTerceiro.getDescontoIrrf(), relatorioDecimoTerceiro.getDescontoTotal()
                    , relatorioDecimoTerceiro.getSalarioLiquido(), relatorioDecimoTerceiro.getOutrosDescontos()
                    , relatorioDecimoTerceiro.getObservacoes());

            Logger.getLogger("RelatorioDecimoTerceiroValida").info("Relatório de décimo terceiro validado com sucesso!");
            return relatorioDecimoTerceiroCria;

        } catch (Exception e) {
            Logger.getLogger("RelatorioDecimoTerceiroValida").info("Erro ao validar relatório de décimo terceiro!");
            throw new IllegalArgumentException("Erro ao validar relatório de décimo terceiro!");
        }
    }

    private void salarioLiquido(RelatorioDecimoTerceiro relatorioDecimoTerceiro) {
        relatorioDecimoTerceiro.setSalarioLiquido(calculoSalario.calculaSalarioLiquido(
                relatorioDecimoTerceiro.getSalarioBrutoDecimoTerceiro()
                , relatorioDecimoTerceiro.getDescontoTotal()
                , relatorioDecimoTerceiro.getOutrosDescontos()));
    }

    private void somaDescontosImpostos(RelatorioDecimoTerceiro relatorioDecimoTerceiro) {
        relatorioDecimoTerceiro.setDescontoTotal(calculoDesconto.somaDescontos(
                relatorioDecimoTerceiro.getDescontoInss()
                , relatorioDecimoTerceiro.getDescontoIrrf()));
    }

    private void descontoIrrf(RelatorioDecimoTerceiro relatorioDecimoTerceiro) {
        relatorioDecimoTerceiro.setDescontoIrrf(calculoIRRF.calculoImpostoRenda(
                relatorioDecimoTerceiro.getSalarioBrutoDecimoTerceiro()
                , relatorioDecimoTerceiro.getFuncionario().getDependentes()
                , relatorioDecimoTerceiro.getDescontoInss()));
    }

    private void descontoInss(RelatorioDecimoTerceiro relatorioDecimoTerceiro) {
        relatorioDecimoTerceiro.setDescontoInss(calculoINSS.calculaDescontoInss(relatorioDecimoTerceiro.getSalarioBrutoDecimoTerceiro()));
    }

    private double validaPrimeiraParcela(double salarioBrutoDecimoTerceiro, LocalDate dataAdmissao, double salarioBruto) {
        calculoSalario.validaSalarioBruto(salarioBruto);

        if (salarioBrutoDecimoTerceiro == 0.0) {
            Logger.getLogger("RelatorioDecimoTerceiroValida").info("Ops,Primeira parcela ainda não foi calculada,gerando parcela unica!");
            return calculoDecimoTerceiro.calculoDecimoTerceiroUnicaParcela(salarioBruto, dataAdmissao);
        }
        return salarioBrutoDecimoTerceiro;
    }

    private void relatorioPrimeiraParcelaExiste(RelatorioDecimoTerceiro relatorioDecimoTerceiro) {
        if (relatorioDecimoTerceiro.getSalarioBrutoDecimoTerceiro() != 0)
            throw new IllegalArgumentException("Relatorio Primeira parcela já foi gerado!");
    }

}