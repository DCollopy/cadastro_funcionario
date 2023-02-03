package br.com.cadastro.persistencia.entidade;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Data
@Table(name = "funcionario")
public class FuncionarioEntidade extends UsuarioEntidade implements Serializable {

    private CarteiraTrabalhoEntidade carteiraTrabalho;

    private PisEntidade pis;

    private SalarioEntidade salario_bruto;

    private int dependentes = 0;

    private LocalDate data_admissao;
}
