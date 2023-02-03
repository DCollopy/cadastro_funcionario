package br.com.cadastro.persistencia.entidade;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Data
@Table(name = "salario")
public class SalarioEntidade {
    private double salario_bruto;
}
