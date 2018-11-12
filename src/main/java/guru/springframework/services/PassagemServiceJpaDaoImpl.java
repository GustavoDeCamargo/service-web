package guru.springframework.services;

import guru.springframework.domain.Passagem;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;


@Service
@Profile("jpadao")
public class PassagemServiceJpaDaoImpl implements PassagemService {

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Passagem> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Passagem", Passagem.class).getResultList();
    }

    @Override
    public Passagem getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Passagem.class, id);
    }

    @Override
    public Passagem saveOrUpdate(Passagem domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Passagem savedPassagem = em.merge(domainObject);
        em.getTransaction().commit();

        return savedPassagem;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Passagem.class, id));
        em.getTransaction().commit();
    }
}
