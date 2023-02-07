package br.com.cadastro.dominio.usecases;

import br.com.cadastro.dominio.entidade.Empresa;

import java.util.logging.Logger;

public abstract class EmpresaValida {

    public Empresa criaEmpresa(Empresa empresa) {
        try {
            Empresa empresaCria = new Empresa(empresa.getRazaosocial(), empresa.getNomefantasia(), empresa.getCnpj());
            Logger.getLogger("EMPRESA").info("Empresa criada com sucesso");
            return empresaCria;
        } catch (Exception e) {
            Logger.getLogger("EMPRESA").info("Empresa nao criada");
            throw new IllegalArgumentException("Empresa nao criada");
        }
    }
}
