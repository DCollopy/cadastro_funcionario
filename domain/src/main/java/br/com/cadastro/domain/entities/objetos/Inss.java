package br.com.cadastro.domain.entities.objetos;

public record Inss() {

/*
    Tabela INSS (Valores atualizados de 2022)
            De	    Até	                Alíquota	Dedução do INSS
            R$0	1.212,00	                7,5%	 R$ 90,90
            R$1.212,01	2.427,35	        9,0%	 R$ 109,38
            R$2.427,36	3.641,03	        12,0%	 R$ 145,64
            R$3.641,04	7.087,22	        14,0%	 R$ */

    public static final double inss_tabela1 = 0.075;
    public static final double inss_tabela2 = 0.09;
    public static final double inss_tabela3 = 0.12;
    public static final double inss_tabela4 = 0.14;

    public static final double inss_deducao1 = 90.90;

    public static final double inss_deducao2 = 109.38;

    public static final double inss_deducao3 = 145.64;

    public static final double inss_valor_minimo = 1212.00;

    public static final double inss_valor_segundo = 2427.35;

    public static final double inss_valor_terceiro = 3641.03;

    public static final double inss_valor_maximo = 7087.22;
}
