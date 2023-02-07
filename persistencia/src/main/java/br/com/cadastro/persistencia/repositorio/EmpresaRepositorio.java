package br.com.cadastro.persistencia.repositorio;

import br.com.cadastro.persistencia.entidade.CnpjEntidade;
import br.com.cadastro.persistencia.entidade.EmpresaEntidade;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmpresaRepositorio {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void salvar(EmpresaEntidade empresaEntidade) {
        entityManager.persist(empresaEntidade);
    }

    @Transactional
    public EmpresaEntidade encontrePorCnpj(CnpjEntidade cnpj) {
        return entityManager.find(EmpresaEntidade.class, cnpj);
    }

    @Transactional
    public boolean existeCnpj(CnpjEntidade cnpj) {
        return entityManager.createQuery("select e from EmpresaEntidade e where e.cnpj = :cnpj", EmpresaEntidade.class)
                .setParameter("cnpj", cnpj)
                .getResultList().isEmpty();
    }

    @Transactional
    public EmpresaEntidade editar(EmpresaEntidade empresa) {
        entityManager.merge(empresa);
        return empresa;
    }

    @Transactional
    public List<EmpresaEntidade> listarEmpresas() {
        return entityManager.createQuery("select e from EmpresaEntidade e", EmpresaEntidade.class).getResultList();
    }

    @Transactional
    public EmpresaEntidade excluir(CnpjEntidade cnpj) {
        EmpresaEntidade empresa = encontrePorCnpj(cnpj);
        if (empresa!= null) {
            entityManager.remove(empresa);
        }else {
            throw new RuntimeException("Empresa não encontrada");
        }
        return empresa;
    }
}
