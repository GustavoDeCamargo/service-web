package guru.springframework.services;

import guru.springframework.domain.Cliente;
import guru.springframework.domain.DomainObject;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Profile("map")
public class ClienteServiceImpl extends AbstractMapService implements ClienteService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Cliente getById(Integer id) {
        return (Cliente) super.getById(id);
    }

    @Override
    public Cliente saveOrUpdate(Cliente domainObject) {
        return (Cliente) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

}
