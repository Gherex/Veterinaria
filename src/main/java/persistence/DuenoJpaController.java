package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import logic.Dueno;

import java.util.List;

public class DuenoJpaController {

    private EntityManagerFactory emf = null;

    public DuenoJpaController() {
        this.emf = JpaUtil.getEntityManagerFactory();
    }

    public void crearDueno(Dueno dueno) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(dueno);
        em.getTransaction().commit();
        em.close();
    }

    public Dueno buscarDueno(int id) {
        EntityManager em = emf.createEntityManager();
        Dueno dueno = em.find(Dueno.class, id);
        em.close();
        return dueno;
    }

    public List<Dueno> listarDuenos() {
        EntityManager em = emf.createEntityManager();
        List<Dueno> lista = em.createQuery("SELECT d FROM Dueno d", Dueno.class).getResultList();
        em.close();
        return lista;
    }

    public void actualizarDueno(Dueno dueno) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(dueno);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminarDueno(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Dueno dueno = em.find(Dueno.class, id);
        if (dueno != null) {
            em.remove(dueno);
        }
        em.getTransaction().commit();
        em.close();
    }
}
