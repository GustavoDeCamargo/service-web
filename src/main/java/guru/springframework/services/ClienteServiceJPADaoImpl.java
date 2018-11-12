package guru.springframework.services;

import guru.springframework.domain.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;


@Service
@Profile("jpadao")
public class ClienteServiceJPADaoImpl implements ClienteService {
    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Cliente> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Cliente", Cliente.class).getResultList();
    }

    @Override
    public Cliente getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Cliente.class, id);
    }

    @Override
    public Cliente saveOrUpdate(Cliente domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Cliente savedCliente = em.merge(domainObject);
        em.getTransaction().commit();

        return savedCliente;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Cliente.class, id));
        em.getTransaction().commit();
    }
}
