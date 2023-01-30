package br.com.cadastro.dominio.usecases.excel;

import br.com.cadastro.dominio.entidade.Funcionario;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ILerExcel {
    List<Funcionario> leituraExcel(String file) throws IOException;
}
