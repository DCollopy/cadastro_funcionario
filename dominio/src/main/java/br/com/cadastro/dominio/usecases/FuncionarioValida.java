package br.com.cadastro.dominio.usecases;

import br.com.cadastro.dominio.entidade.Funcionario;

import java.util.logging.Logger;

public abstract class FuncionarioValida {

    public Funcionario criaFuncionario(Funcionario funcionario){
        try{
            Funcionario func = new Funcionario(funcionario.getNome(), funcionario.getSobrenome()
                    , funcionario.getCpf(), funcionario.getEmail(), funcionario.getTelefone()
                    , funcionario.getEndereco(), funcionario.getCargo()
                    , funcionario.getCarteiraTrabalho(), funcionario.getPis()
                    , funcionario.getSalario_bruto(), funcionario.getDependentes());
            Logger.getLogger("FUNCIONARIO").info("Funcionario criado com sucesso");
            return func;
        }catch (Exception e){
            Logger.getLogger("FUNCIONARIO").info("Funcionario invalido");
            throw new IllegalArgumentException("Funcionario invalido");
        }
    }

    public Funcionario editaFuncionario(Funcionario funcionario){
        try{
            Funcionario func = new Funcionario(funcionario.getNome(), funcionario.getSobrenome()
                    , funcionario.getCpf(), funcionario.getEmail(), funcionario.getTelefone()
                    , funcionario.getEndereco(), funcionario.getCargo()
                    , funcionario.getCarteiraTrabalho(), funcionario.getPis()
                    , funcionario.getSalario_bruto(), funcionario.getDependentes());
            Logger.getLogger("FUNCIONARIO").info("Funcionario editado com sucesso");
            return func;
        }catch (Exception e){
            Logger.getLogger("FUNCIONARIO").info("Funcionario invalido");
            throw new IllegalArgumentException("Funcionario invalido");
        }
    }
}