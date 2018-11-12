package guru.springframework.services;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.Passagem;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Profile("map")
public class PassagemServiceImpl extends AbstractMapService implements PassagemService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Passagem getById(Integer id) {
        return (Passagem) super.getById(id);
    }

    @Override
    public Passagem saveOrUpdate(Passagem domainObject) {
        return (Passagem) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

   }
