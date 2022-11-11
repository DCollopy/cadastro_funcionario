package com.br.domain.entities;

import lombok.Data;

@Data
public record ValeTransporte() {

    private static double desconto = 0.06;
}
