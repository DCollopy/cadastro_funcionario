package com.br.domain.entities;

import lombok.Data;

@Data
public record Inss() {

/*
    Tabela INSS (Valores atualizados de 2022)
            De	    Até	                Alíquota	Dedução do INSS
            R$0	1.212,00	                7,5%	 R$ 0
            R$1.212,01	2.427,35	        9,0%	 R$ 18,18
            R$2.427,36	3.641,03	        12,0%	 R$ 91,00
            R$3.641,04	7.087,22	        14,0%	 R$ 163,82*/

    private static final double inss_tabela1 = 0.075;
    private static final double inss_tabela2 = 0.09;
    private static final double inss_tabela3 = 0.12;
    private static final double inss_tabela4 = 0.14;

}
