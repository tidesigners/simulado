package br.com.tiupgrade.simulado.repositorio;

import br.com.tiupgrade.simulado.dominio.Curso;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rodrigosantos on 10/02/17.
 */

@Named("todosOsCursos")
@RequestScoped
public class TodosOsCursos {

    @PersistenceContext
    private EntityManager em;


    @Resource
    private UserTransaction transaction;


    @SuppressWarnings("unchecked")
    public Curso obterPorId(Long id){

        return (Curso) em.createQuery("select c from Curso c where id = :id").setParameter("id", id)
                .getResultList();

    }

    @SuppressWarnings("unchecked")
    public Curso obterPorDescricao(String descricao){

        return (Curso) em.createQuery("select c from Curso c where descricao = :descricao").setParameter("descricao", descricao)
                .getResultList();

    }

    @SuppressWarnings("unchecked")
    public List<Curso> obterTodos(){
        return (List<Curso>) em.createQuery("select c from Curso c")
                .getResultList();
    }

    public boolean colocar(Curso curso) {
        try {

            transaction.begin();
            em.joinTransaction();
            if (curso.getId() != null) {
                curso = em.merge(curso);
            }//if
            em.persist(curso);
            em.flush();
            transaction.commit();
        } catch (Exception e) {
            try {
                transaction.rollback();
                return false;
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage());
                return false;
            }//try
        }//try
        return true;
    }

    public void remover(Curso curso) {
        try {
            transaction.begin();
            em.joinTransaction();
            if (curso.getId() != null) {
                curso = em.merge(curso);
            }//if
            em.remove(curso);
            em.flush();
            transaction.commit();
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage());
            }//try
        }//try
    }


}
