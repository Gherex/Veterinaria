package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import logic.Mascota;

import java.util.List;

public class MascotaJpaController {

    private EntityManagerFactory emf = null;

    public MascotaJpaController() {
        this.emf = JpaUtil.getEntityManagerFactory();
    }

    public void crearMascota(Mascota mascota) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(mascota);
        em.getTransaction().commit();
        em.close();
    }

    public Mascota buscarMascota(int id) {
        EntityManager em = emf.createEntityManager();
        Mascota mascota = em.find(Mascota.class, id);
        em.close();
        return mascota;
    }

    public List<Mascota> listarMascotas() {
        EntityManager em = emf.createEntityManager();
        List<Mascota> lista = em.createQuery("SELECT m FROM Mascota m", Mascota.class).getResultList();
        em.close();
        return lista;
    }

    public void actualizarMascota(Mascota mascota) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(mascota);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminarMascota(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Mascota mascota = em.find(Mascota.class, id);
        if (mascota != null) {
            em.remove(mascota);
        }
        em.getTransaction().commit();
        em.close();
    }
}