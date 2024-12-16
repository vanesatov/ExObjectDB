package org.example.services;

import org.example.models.Comentario;
import org.example.models.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private static EntityManagerFactory emf;

    public Service(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * Registra un nuevo usuario en la plataforma
     *
     * @param u
     */
    public void saveUsuario(Usuario u) {
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Quiero tener un listado con todos los comentarios de un usuario específico
     */

    public List<Comentario> getComentarios(String correo) {
        List<Comentario> salida = new ArrayList<>();
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Comentario> q = em.createQuery("select c from Comentario c where c.usuario.correo = :correo", Comentario.class);
            q.setParameter("correo", correo);
            salida = q.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    /**
     * Añade un comentario a la plataforma
     *
     * @param c
     */
    public void saveComentarioToUsuario(Comentario c, String correoUsuario) {
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Usuario usuario = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class)
                    .setParameter("correo", correoUsuario)
                    .getSingleResult();
            c.setUsuario(usuario);
            em.persist(c);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Quiero tener un listado con todos los usuarios que han valorado con la máxima puntuación
     */
    public List<String> getUsuariosConValoracionMaxima() {
        List<String> salida = new ArrayList<>();
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<String> q = em.createQuery("select c.usuario.correo from Comentario c where c.valoracion = 10", String.class);
            salida = q.getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

}
