package br.com.cadastro.persistencia.repositorio;

import br.com.cadastro.persistencia.entidade.CpfEntidade;
import br.com.cadastro.persistencia.entidade.FuncionarioEntidade;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class FuncionarioRepositorio {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public FuncionarioEntidade salvar(FuncionarioEntidade funcionarioEntidade) {
        entityManager.persist(funcionarioEntidade);
        return funcionarioEntidade;
    }

    public FuncionarioEntidade findByCpf(CpfEntidade cpf) {
        FuncionarioEntidade funcionario = (FuncionarioEntidade) entityManager.find(FuncionarioEntidade.class, cpf);
        return funcionario;
    }

    @Transactional
    public FuncionarioEntidade update(FuncionarioEntidade funcionario) {
        entityManager.merge(funcionario);
        return funcionario;
    }

    @Transactional
    public FuncionarioEntidade deleteByCpf(CpfEntidade cpf) {
        FuncionarioEntidade funcionario = findByCpf(cpf);
        if (funcionario!= null) {
            entityManager.remove(funcionario);
        }
        return funcionario;
    }
}
