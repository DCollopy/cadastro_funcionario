package br.com.cadastro.web.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FuncionarioDTO extends UsuarioDTO {

    private CarteiraTrabalhoDTO carteiraTrabalho;

    private PisDTO pis;

    private SalarioDTO salario_bruto;

    private int dependentes;

    private LocalDate data_admissao;
}
