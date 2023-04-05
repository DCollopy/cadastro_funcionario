package br.com.cadastro.persistencia.repositorio;

import br.com.cadastro.persistencia.entidade.CpfEntidade;
import br.com.cadastro.persistencia.entidade.FuncionarioEntidade;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public class FuncionarioRepositorio {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<FuncionarioEntidade> listaFuncionarios() {
        return entityManager.createQuery("select f from FuncionarioEntidade f", FuncionarioEntidade.class).getResultList();
    }
    @Transactional
    public FuncionarioEntidade salvar(FuncionarioEntidade funcionarioEntidade) {
        entityManager.persist(funcionarioEntidade);
        return funcionarioEntidade;
    }
    @Transactional
    public FuncionarioEntidade encontrePorCpf(CpfEntidade cpf) {
        return entityManager.createQuery("select f from FuncionarioEntidade f where f.cpf = :cpf", FuncionarioEntidade.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
    }

    @Transactional
    public Boolean existeCpf(CpfEntidade cpf) {
        return entityManager.createQuery("select f from FuncionarioEntidade f where f.cpf = :cpf", FuncionarioEntidade.class)
                .setParameter("cpf", cpf)
                .getResultList().isEmpty();
    }

    @Transactional
    public FuncionarioEntidade editar(FuncionarioEntidade funcionario) {
        entityManager.merge(funcionario);
        return funcionario;
    }

    @Transactional
    public FuncionarioEntidade excluir(CpfEntidade cpf) {
        FuncionarioEntidade funcionario = encontrePorCpf(cpf);
        if (funcionario!= null) {
            entityManager.remove(funcionario);
        }
        return funcionario;
    }
}