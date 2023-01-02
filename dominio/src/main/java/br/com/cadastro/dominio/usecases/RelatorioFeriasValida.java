package br.com.cadastro.dominio.usecases;

import br.com.cadastro.dominio.entidade.RelatorioFerias;

import java.util.logging.Logger;

public abstract class RelatorioFeriasValida {
    private ICalculoInss calculoINSS;
    private ICaculoIrrf calculoIRRF;
    private ICalculoFerias calculoFerias;
    private ICalculoDesconto calculoDesconto;
    private ICalculoSalarioLiquido calculoSalario;

    public RelatorioFerias criaRelatorioFerias(RelatorioFerias relatorioFerias){
        try {
            double salarioBruto = relatorioFerias.getFuncionario().getSalario_bruto().getSalario_bruto();
            validaSalarioBruto(salarioBruto);

            relatorioFerias.setSalarioBrutoFerias(calculoFerias.calculaFerias(salarioBruto));
            relatorioFerias.setDescontoInss(calculoINSS.calculaDescontoInss(salarioBruto));
            relatorioFerias.setDescontoIrrf(calculoIRRF.calculoImpostoRenda(salarioBruto, relatorioFerias.getFuncionario().getDependentes()
                    , relatorioFerias.getDescontoInss()));
            relatorioFerias.setDescontoTotal(calculoDesconto.somaDescontos(relatorioFerias.getDescontoInss(), relatorioFerias.getDescontoIrrf()));
            relatorioFerias.setSalarioLiquido(calculoSalario.calculaSalarioLiquido(salarioBruto
                    , relatorioFerias.getDescontoTotal()
                    , relatorioFerias.getOutrosDescontos()));

            RelatorioFerias relatorioFeriasCria = new RelatorioFerias(relatorioFerias.getFuncionario(), relatorioFerias.getDescontoInss()
                    , relatorioFerias.getDescontoIrrf(), relatorioFerias.getDescontoTotal()
                    , relatorioFerias.getSalarioLiquido(), relatorioFerias.getSalarioBrutoFerias()
                    , relatorioFerias.getOutrosDescontos(), relatorioFerias.getObservacoes());

            Logger.getLogger("RelatorioFeriasValida").info("Relatório de férias validado com sucesso!");
            return relatorioFeriasCria;
        } catch (Exception e) {
            Logger.getLogger("Erro ao calcular férias");
            throw new IllegalArgumentException("Erro ao calcular férias");
        }
    }
    private void validaSalarioBruto(double salarioBruto) {
        if(salarioBruto < 0){
            Logger.getLogger("Salário bruto inválido");
            throw new IllegalArgumentException("Erro ao calcular férias - Salário bruto inválido");
        }
    }
}