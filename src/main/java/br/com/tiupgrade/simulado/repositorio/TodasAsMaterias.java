package br.com.tiupgrade.simulado.repositorio;

import br.com.tiupgrade.simulado.dominio.Materia;

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

@Named("todasAsMaterias")
@RequestScoped
public class TodasAsMaterias {

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction transaction;

    @SuppressWarnings("unchecked")
    public Materia obterPorId(Long id){
        return (Materia) em.createQuery("select m from Materia m where id = :id").setParameter("id", id)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Materia> obterTodas(){
        return (List<Materia>) em.createQuery("select m from Materia m")
                .getResultList();
    }

    public Materia obterPorDescricao(String descricao){

        return (Materia) em.createQuery("select m from Materia m where descricao = :descricao").setParameter("descricao", descricao)
                .getResultList();

    }

    public boolean colocar(Materia materia) {
        try {

            transaction.begin();
            em.joinTransaction();
            if (materia.getId() != null) {
                materia = em.merge(materia);
            }//if
            em.persist(materia);
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

    public void remover(Materia materia) {
        try {
            transaction.begin();
            em.joinTransaction();
            if (materia.getId() != null) {
                materia = em.merge(materia);
            }//if
            em.remove(materia);
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
