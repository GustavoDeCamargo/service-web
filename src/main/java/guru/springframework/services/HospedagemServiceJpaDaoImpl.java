package guru.springframework.services;

import guru.springframework.domain.Hospedagem;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;


@Service
@Profile("jpadao")
public class HospedagemServiceJpaDaoImpl implements HospedagemService {

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Hospedagem> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Hospedagem", Hospedagem.class).getResultList();
    }

    @Override
    public Hospedagem getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Hospedagem.class, id);
    }

    @Override
    public Hospedagem saveOrUpdate(Hospedagem domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Hospedagem savedHospedagem = em.merge(domainObject);
        em.getTransaction().commit();

        return savedHospedagem;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Hospedagem.class, id));
        em.getTransaction().commit();
    }
}
