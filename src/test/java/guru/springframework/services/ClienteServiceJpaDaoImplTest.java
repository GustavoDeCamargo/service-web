package guru.springframework.services;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Cliente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class ClienteServiceJpaDaoImplTest {

    private ClienteService clienteService;

    @Autowired
    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Test
    public void testList() throws Exception {
        List<Cliente> clientes = (List<Cliente>) clienteService.listAll();

        assert clientes.size() == 3;

    }
}
