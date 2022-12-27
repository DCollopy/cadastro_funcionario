package br.com.cadastro.domain.entities.objetos;

import lombok.Data;

@Data
public record ValeTransporte() {

    private static double desconto = 0.06;
}
