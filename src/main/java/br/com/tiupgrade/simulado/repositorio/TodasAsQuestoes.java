package br.com.tiupgrade.simulado.repositorio;

import br.com.tiupgrade.simulado.dominio.Questao;

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
 * Created by rodrigo on 13/02/17.
 */

@Named("todasAsQuestoes")
@RequestScoped
public class TodasAsQuestoes {

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction transaction;

    @SuppressWarnings("unchecked")
    public Questao obterPorId(Long id){
        return (Questao) em.createQuery("select q from Questao q where id = :id").setParameter("id", id)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Questao> obterTodas(){
        return (List<Questao>) em.createQuery("select q from Questao q")
                .getResultList();
    }

    public Questao obterPorTitulo(String titulo){

        return (Questao) em.createQuery("select q from Questao q where titulo = :titulo").setParameter("titulo", titulo)
                .getResultList();

    }

    public boolean colocar(Questao Questao) {
        try {

            transaction.begin();
            em.joinTransaction();
            if (Questao.getId() != null) {
                Questao = em.merge(Questao);
            }//if
            em.persist(Questao);
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

    public void remover(Questao Questao) {
        try {
            transaction.begin();
            em.joinTransaction();
            if (Questao.getId() != null) {
                Questao = em.merge(Questao);
            }//if
            em.remove(Questao);
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
