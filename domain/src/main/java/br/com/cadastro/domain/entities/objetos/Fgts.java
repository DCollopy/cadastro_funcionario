package br.com.cadastro.domain.entities.objetos;

public record Fgts() {

    /*
    * Imposto de Renda
    BASE DE CÁLCULO (R$)	                    ALÍQUOTA (%)	PARCELA A DEDUZIR (R$)
    Até R$ 1.903,98	0%	–
    De R$ 1.903,99 a R$ 2.826,65	                7,5%	        R$ 142,80
    De R$ 2.826,66 a R$ 3.751,05	                15%	            R$ 354,80
    De R$ 3.751,06 a R$ R$ 4.664,68	                22,5%	        R$ 636,13
    Acima de R$ 4.664,69	                        27,5%	        R$ 869,36%
    *
    *
    * Antes de se chegar ao tributo devido, cheque se o funcionário tem dependentes legais, pois são descontados R$ 189,59 por cada dependente.
      Podem ser considerados como dependentes: cônjuge, filhos até 21 anos, pais e avós (desde que não tenham rendimento).
    * Valor IRRF = (salário base * alíquota) – taxa - numero*(dependente)*/

    private static final double fgts_tabela1 = 0.075;

    private static final double fgts_tabela2 = 0.15;

    private static final double fgts_tabela3 = 0.225;

    private static final double fgts_tabela4 = 0.275;

    private static final double fgts_taxa1 = 142.80;

    private static final double fgts_taxa2 = 354.80;

    private static final double fgts_taxa3 = 636.13;

    private static final double fgts_taxa4 = 869.36;

    private static final double fgts_dependente = 189.59;

}
