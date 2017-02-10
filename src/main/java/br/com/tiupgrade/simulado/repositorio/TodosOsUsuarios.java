package br.com.tiupgrade.simulado.repositorio;

import br.com.tiupgrade.simulado.dominio.Usuario;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("todosOsUsuarios")
@RequestScoped
public class TodosOsUsuarios {

    @PersistenceContext
    EntityManager entityManager;

    @Resource
    UserTransaction transaction;

    public Usuario obterPorId(Integer id) {
        return (Usuario) entityManager.createQuery("select u from Usuario u where u.id = :id ")
                .setParameter("id", id).getSingleResult();
    }

    public Usuario obterPorLogin(String username) {
        try {
            return (Usuario) entityManager.createQuery("select u from Usuario u where u.username = :username").setParameter("username", username).getSingleResult();
        } catch (Exception e) {
            return null;
        }//try
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> obterTodos() {
        return (List<Usuario>) entityManager.createQuery("select u from Usuario u ").getResultList();
    }

    public boolean colocar(Usuario usuario) {
        try {

            transaction.begin();
            entityManager.joinTransaction();
            if (usuario.getId() != null) {
                usuario = entityManager.merge(usuario);
            }//if
            entityManager.persist(usuario);
            entityManager.flush();
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

    public void remover(Usuario usuario) {
        try {
            transaction.begin();
            entityManager.joinTransaction();
            if (usuario.getId() != null) {
                usuario = entityManager.merge(usuario);
            }//if
            entityManager.remove(usuario);
            entityManager.flush();
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
