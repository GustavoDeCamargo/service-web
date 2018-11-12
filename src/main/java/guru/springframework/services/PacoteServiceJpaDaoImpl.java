package guru.springframework.services;

import guru.springframework.domain.Pacote;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;


@Service
@Profile("jpadao")
public class PacoteServiceJpaDaoImpl implements PacoteService {

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Pacote> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Pacote", Pacote.class).getResultList();
    }

    @Override
    public Pacote getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Pacote.class, id);
    }

    @Override
    public Pacote saveOrUpdate(Pacote domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Pacote savedPacote = em.merge(domainObject);
        em.getTransaction().commit();

        return savedPacote;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Pacote.class, id));
        em.getTransaction().commit();
    }
}
