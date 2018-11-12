package guru.springframework.services;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Passagem;
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
public class PassagemServiceJpaDaoImplTest {

    private PassagemService passagemService;

    @Autowired
    public void setPassagemService(PassagemService passagemService) {
        this.passagemService = passagemService;
    }

    @Test
    public void testListMethod() throws Exception {

        List<Passagem> passagems = (List<Passagem>) passagemService.listAll();

        assert passagems.size() == 5;

    }
}
