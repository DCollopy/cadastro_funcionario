package br.com.cadastro.persistencia.repositorio;

import br.com.cadastro.persistencia.entidade.AdminEntidade;
import br.com.cadastro.persistencia.entidade.CpfEntidade;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AdminRepositorio {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<AdminEntidade> listaAdmins() {
        return entityManager.createQuery("select a from AdminEntidade a", AdminEntidade.class).getResultList();
    }
    @Transactional
    public AdminEntidade salvar(AdminEntidade adminEntidade) {
        entityManager.persist(adminEntidade);
        return adminEntidade;
    }
    @Transactional
    public AdminEntidade encontrePorCpf(CpfEntidade cpf) {
        return entityManager.createQuery("select a from AdminEntidade a where a.cpf = :cpf", AdminEntidade.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
    }

    @Transactional
    public Boolean existeCpf(CpfEntidade cpf) {
        return entityManager.createQuery("select a from AdminEntidade a where a.cpf = :cpf", AdminEntidade.class)
                .setParameter("cpf", cpf)
                .getResultList().isEmpty();
    }

    @Transactional
    public AdminEntidade editar(AdminEntidade admin) {
        entityManager.merge(admin);
        return admin;
    }

    @Transactional
    public AdminEntidade excluir(CpfEntidade cpf) {
        AdminEntidade admin = encontrePorCpf(cpf);
        if (admin!= null) {
            entityManager.remove(admin);
        }
        return admin;
    }
}
