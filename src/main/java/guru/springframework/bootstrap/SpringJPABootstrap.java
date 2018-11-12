package guru.springframework.bootstrap;

import guru.springframework.domain.Cliente;
import guru.springframework.domain.Hospedagem;
import guru.springframework.domain.Pacote;
import guru.springframework.domain.Passagem;
import guru.springframework.services.ClienteService;
import guru.springframework.services.HospedagemService;
import guru.springframework.services.PacoteService;
import guru.springframework.services.PassagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private PassagemService passagemService;
    private ClienteService clienteService;
    private HospedagemService hospedagemService;
    private PacoteService pacoteService;

    @Autowired
    public void setPassagemService(PassagemService passagemService) {
        this.passagemService = passagemService;
    }

    @Autowired
    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Autowired
    public void setHospedagemService(HospedagemService hospedagemService) { this.hospedagemService = hospedagemService;
    }

    @Autowired
    public void setPacoteService(PacoteService pacoteService) { this.pacoteService = pacoteService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadPassagens();
        loadClientes();
        loadHospedagens();
        loadPacotes();
    }

    public void loadClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setFirstName("Micheal");
        cliente1.setLastName("Weston");
        cliente1.setAddressLine1("1 Main St");
        cliente1.setCity("Miami");
        cliente1.setState("Florida");
        cliente1.setZipCode("33101");
        cliente1.setEmail("micheal@burnnotice.com");
        cliente1.setPhoneNumber("305.333.0101");
        clienteService.saveOrUpdate(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setFirstName("Fiona");
        cliente2.setLastName("Glenanne");
        cliente2.setAddressLine1("1 Key Biscane Ave");
        cliente2.setCity("Miami");
        cliente2.setState("Florida");
        cliente2.setZipCode("33101");
        cliente2.setEmail("fiona@burnnotice.com");
        cliente2.setPhoneNumber("305.323.0233");
        clienteService.saveOrUpdate(cliente2);

        Cliente cliente3 = new Cliente();
        cliente3.setFirstName("Sam");
        cliente3.setLastName("Axe");
        cliente3.setAddressLine1("1 Little Cuba Road");
        cliente3.setCity("Miami");
        cliente3.setState("Florida");
        cliente3.setZipCode("33101");
        cliente3.setEmail("sam@burnnotice.com");
        cliente3.setPhoneNumber("305.426.9832");
        clienteService.saveOrUpdate(cliente3);
    }

    public void loadPassagens(){

        Passagem passagem1 = new Passagem();
        passagem1.setOrigem("Curitiba");
        passagem1.setPrice(new BigDecimal("12.99"));
        passagem1.setDestino("São Paulo");
        passagemService.saveOrUpdate(passagem1);

        Passagem passagem2 = new Passagem();
        passagem2.setOrigem("Rio de Janeiro");
        passagem2.setPrice(new BigDecimal("14.99"));
        passagem2.setDestino("Salvador");
        passagemService.saveOrUpdate(passagem2);

        Passagem passagem3 = new Passagem();
        passagem3.setOrigem("Porto Alegre");
        passagem3.setPrice(new BigDecimal("34.99"));
        passagem3.setDestino("Curitiba");
        passagemService.saveOrUpdate(passagem3);

        Passagem passagem4 = new Passagem();
        passagem4.setOrigem("Recife");
        passagem4.setPrice(new BigDecimal("44.99"));
        passagem4.setDestino("Campinas");
        passagemService.saveOrUpdate(passagem4);

        Passagem passagem5 = new Passagem();
        passagem5.setOrigem("Curitiba");
        passagem5.setPrice(new BigDecimal("25.99"));
        passagem5.setDestino("Buenos Aires");
        passagemService.saveOrUpdate(passagem5);

    }

    public void loadHospedagens(){

        Hospedagem hospedagem1 = new Hospedagem();
        hospedagem1.setDescription("Passagem 1");
        hospedagem1.setPrice(new BigDecimal("12.99"));
        hospedagem1.setDestino("http://example.com/product1");
        hospedagemService.saveOrUpdate(hospedagem1);

        Hospedagem hospedagem2 = new Hospedagem();
        hospedagem2.setDescription("Passagem 2");
        hospedagem2.setPrice(new BigDecimal("14.99"));
        hospedagem2.setDestino("http://example.com/product2");
        hospedagemService.saveOrUpdate(hospedagem2);

        Hospedagem hospedagem3 = new Hospedagem();
        hospedagem3.setDescription("Passagem 3");
        hospedagem3.setPrice(new BigDecimal("34.99"));
        hospedagem3.setDestino("http://example.com/product3");
        hospedagemService.saveOrUpdate(hospedagem3);

        Hospedagem hospedagem4 = new Hospedagem();
        hospedagem4.setDescription("Passagem 4");
        hospedagem4.setPrice(new BigDecimal("44.99"));
        hospedagem4.setDestino("http://example.com/product4");
        hospedagemService.saveOrUpdate(hospedagem4);

        Hospedagem hospedagem5 = new Hospedagem();
        hospedagem5.setDescription("Passagem 5");
        hospedagem5.setPrice(new BigDecimal("25.99"));
        hospedagem5.setDestino("http://example.com/product5");
        hospedagemService.saveOrUpdate(hospedagem5);

    }

    public void loadPacotes(){

        Pacote pacote1 = new Pacote();
        pacote1.setDescription("Passagem 1");
        pacote1.setPrice(new BigDecimal("12.99"));
        pacote1.setDestino("http://example.com/product1");
        pacoteService.saveOrUpdate(pacote1);

        Pacote pacote2 = new Pacote();
        pacote2.setDescription("Passagem 2");
        pacote2.setPrice(new BigDecimal("14.99"));
        pacote2.setDestino("http://example.com/product2");
        pacoteService.saveOrUpdate(pacote2);

        Pacote pacote3 = new Pacote();
        pacote3.setDescription("Passagem 3");
        pacote3.setPrice(new BigDecimal("34.99"));
        pacote3.setDestino("http://example.com/product3");
        pacoteService.saveOrUpdate(pacote3);

        Pacote pacote4 = new Pacote();
        pacote4.setDescription("Passagem 4");
        pacote4.setPrice(new BigDecimal("44.99"));
        pacote4.setDestino("http://example.com/product4");
        pacoteService.saveOrUpdate(pacote4);

        Pacote pacote5 = new Pacote();
        pacote5.setDescription("Passagem 5");
        pacote5.setPrice(new BigDecimal("25.99"));
        pacote5.setDestino("http://example.com/product5");
        pacoteService.saveOrUpdate(pacote5);

    }
}
