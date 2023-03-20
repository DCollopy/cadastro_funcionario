package br.com.cadastro.persistencia.repositorio;

import br.com.cadastro.persistencia.entidade.CpfEntidade;
import br.com.cadastro.persistencia.entidade.EmailEntidade;
import br.com.cadastro.persistencia.entidade.LoginEntidade;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class LoginRepositorio{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public LoginEntidade salvar(LoginEntidade loginEntidade) {
        entityManager.persist(loginEntidade);
        return loginEntidade;
    }

    @Transactional
    public LoginEntidade atualizar(LoginEntidade loginEntidade) {
        entityManager.merge(loginEntidade);
        return loginEntidade;
    }

    @Transactional
    public void excluir(LoginEntidade loginEntidade) {
        entityManager.remove(loginEntidade);
    }

    public LoginEntidade buscarPorCpf(CpfEntidade cpf) {
        return entityManager.createQuery("select f from LoginEntidade f where f.cpf = :cpf", LoginEntidade.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
    }

    public LoginEntidade buscarPorEmail(EmailEntidade email) {
        return entityManager.createQuery("select f from LoginEntidade f where f.email = :email", LoginEntidade.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public Boolean existePorCpf(CpfEntidade cpf) {
        return entityManager.createQuery("select f from LoginEntidade f where f.cpf = :cpf", LoginEntidade.class)
                .setParameter("cpf", cpf)
                .getResultList().isEmpty();
    }

    public LoginEntidade fazerLogin(EmailEntidade email, String senha) {
        return entityManager.createQuery("select f from LoginEntidade f where f.email = :email and f.senha = :senha", LoginEntidade.class)
                .setParameter("email", email)
                .setParameter("senha", senha)
                .getSingleResult();
    }
}
