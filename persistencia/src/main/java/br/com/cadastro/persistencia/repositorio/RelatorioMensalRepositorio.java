package br.com.cadastro.persistencia.repositorio;

import br.com.cadastro.persistencia.entidade.CpfEntidade;
import br.com.cadastro.persistencia.entidade.FuncionarioEntidade;
import br.com.cadastro.persistencia.entidade.RelatorioMensalEntidade;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public class RelatorioMensalRepositorio {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public RelatorioMensalEntidade salvarRelatorioMensal(RelatorioMensalEntidade relatorioMensalEntidade) {
        entityManager.persist(relatorioMensalEntidade);
        return relatorioMensalEntidade;
    }

    @Transactional
    public void deletarRelatorioMensal(RelatorioMensalEntidade relatorioMensalEntidade) {
        entityManager.remove(relatorioMensalEntidade);
    }

    @Transactional
    public void atualizarRelatorioMensal(RelatorioMensalEntidade relatorioMensalEntidade) {
        entityManager.merge(relatorioMensalEntidade);
    }

    @Transactional
    public void buscarRelatorioMensalPorCpf(CpfEntidade cpf) {

    }

    @Transactional
    public RelatorioMensalEntidade buscarRelatorioMensalPorMes(LocalDate mesRelatorio, FuncionarioEntidade funcionarioEntidade) {
        return entityManager.createQuery("SELECT r FROM RelatorioMensalEntidade r WHERE r.mesRelatorio = :mesRelatorio AND r.funcionarios = :funcionarioEntidade", RelatorioMensalEntidade.class)
                .setParameter("mesRelatorio", mesRelatorio)
                .setParameter("funcionarioEntidade", funcionarioEntidade)
                .getSingleResult();
    }

    @Transactional
    public RelatorioMensalEntidade buscarRelatorioMensalPorId(Long id) {
        return entityManager.find(RelatorioMensalEntidade.class, id);
    }

    @Transactional
    public boolean existeRelatorioMensal(Long id) {
        return entityManager.createQuery("SELECT r FROM RelatorioMensalEntidade r WHERE r.id = :id", RelatorioMensalEntidade.class)
                .setParameter("id", id)
                .getResultList()
                .size() > 0;
    }
}
