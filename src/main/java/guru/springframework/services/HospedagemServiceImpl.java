package guru.springframework.services;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.Hospedagem;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Profile("map")
public class HospedagemServiceImpl extends AbstractMapService implements HospedagemService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Hospedagem getById(Integer id) {
        return (Hospedagem) super.getById(id);
    }

    @Override
    public Hospedagem saveOrUpdate(Hospedagem domainObject) {
        return (Hospedagem) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

}
