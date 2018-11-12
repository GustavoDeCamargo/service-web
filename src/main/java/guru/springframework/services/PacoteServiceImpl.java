package guru.springframework.services;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.Pacote;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Profile("map")
public class PacoteServiceImpl extends AbstractMapService implements PacoteService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Pacote getById(Integer id) {
        return (Pacote) super.getById(id);
    }

    @Override
    public Pacote saveOrUpdate(Pacote domainObject) {
        return (Pacote) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

}
