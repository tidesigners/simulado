package br.com.tiupgrade.simulado.repositorio;

import br.com.tiupgrade.simulado.dominio.Resposta;

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

@Named("todasAsRespostas")
@RequestScoped
public class TodasAsRespostas {

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction transaction;

    @SuppressWarnings("unchecked")
    public Resposta obterPorId(Long id){
        return (Resposta) em.createQuery("select r from Resposta r where id = :id").setParameter("id", id)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Resposta> obterTodas(){
        return (List<Resposta>) em.createQuery("select r from Resposta r")
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public Resposta obterPorTexto(String texto){

        return (Resposta) em.createQuery("select r from Resposta r where :texto in texto").setParameter("texto", texto)
                .getResultList();

    }

    public boolean colocar(Resposta Resposta) {
        try {

            transaction.begin();
            em.joinTransaction();
            if (Resposta.getId() != null) {
                Resposta = em.merge(Resposta);
            }//if
            em.persist(Resposta);
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

    public void remover(Resposta Resposta) {
        try {
            transaction.begin();
            em.joinTransaction();
            if (Resposta.getId() != null) {
                Resposta = em.merge(Resposta);
            }//if
            em.remove(Resposta);
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
