package br.com.cadastro.dominio.entidade.objetos;

public record Irrf() {

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

    public static final double irrf_tabela1 = 0.075;

    public static final double irrf_tabela2 = 0.15;

    public static final double irrf_tabela3 = 0.225;

    public static final double irrf_tabela4 = 0.275;

    public static final double irrf_taxa1 = 142.80;

    public static final double irrf_taxa2 = 354.80;

    public static final double irrf_taxa3 = 636.13;

    public static final double irrf_taxa4 = 869.36;

    public static final double irrf_dependente = 189.59;

    public static final double valor_minimo = 1903.98;

    public static final double valor_um = 2826.65;

    public static final double valor_dois = 3751.05;

    public static final double valor_maximo= 4664.68;

}