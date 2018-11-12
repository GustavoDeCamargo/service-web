package guru.springframework.controllers;

import guru.springframework.domain.Cliente;
import guru.springframework.services.ClienteService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    public void testList() throws Exception{
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente());
        clientes.add(new Cliente());

        when(clienteService.listAll()).thenReturn((List) clientes);

        mockMvc.perform(get("/cliente/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("cliente/list"))
                .andExpect(model().attribute("clientes", hasSize(2)));
    }

    @Test
    public void testShow() throws Exception {
        Integer id = 1;

        when(clienteService.getById(id)).thenReturn(new Cliente());

        mockMvc.perform(get("/cliente/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("cliente/show"))
                .andExpect(model().attribute("cliente", instanceOf(Cliente.class)));
    }

    @Test
    public void testEdit() throws Exception {
        Integer id = 1;

        when(clienteService.getById(id)).thenReturn(new Cliente());

        mockMvc.perform(get("/cliente/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("cliente/customerform"))
                .andExpect(model().attribute("cliente", instanceOf(Cliente.class)));
    }

    @Test
    public void testNewCustomer() throws Exception {
        verifyZeroInteractions(clienteService);

        mockMvc.perform(get("/cliente/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("cliente/customerform"))
                .andExpect(model().attribute("cliente", instanceOf(Cliente.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        Cliente returnCliente = new Cliente();
        String firstName = "Micheal";
        String lastName = "Weston";
        String addressLine1 = "1 Main St";
        String addressLine2 = "Apt 301";
        String city = "Miami";
        String state = "Florida";
        String zipCode = "33101";
        String email = "micheal@burnnotice.com";
        String phoneNumber = "305.333.0101";

        returnCliente.setId(id);
        returnCliente.setFirstName(firstName);
        returnCliente.setLastName(lastName);
        returnCliente.setAddressLine1(addressLine1);
        returnCliente.setAddressLine2(addressLine2);
        returnCliente.setCity(city);
        returnCliente.setState(state);
        returnCliente.setZipCode(zipCode);
        returnCliente.setEmail(email);
        returnCliente.setPhoneNumber(phoneNumber);

        when(clienteService.saveOrUpdate(Matchers.<Cliente>any())).thenReturn(returnCliente);

        mockMvc.perform(post("/cliente")
        .param("id", "1")
        .param("firstName", firstName)
        .param("lastName", lastName)
        .param("addressLine1", addressLine1)
        .param("addressLine2", addressLine2)
        .param("city", city)
        .param("state", state)
        .param("zipCode", zipCode)
        .param("email", email)
        .param("phoneNumber", phoneNumber))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:cliente/show/1"))
                .andExpect(model().attribute("cliente", instanceOf(Cliente.class)))
                .andExpect(model().attribute("cliente", hasProperty("firstName", is(firstName))))
                .andExpect(model().attribute("cliente", hasProperty("lastName", is(lastName))))
                .andExpect(model().attribute("cliente", hasProperty("addressLine1", is(addressLine1))))
                .andExpect(model().attribute("cliente", hasProperty("addressLine2", is(addressLine2))))
                .andExpect(model().attribute("cliente", hasProperty("city", is(city))))
                .andExpect(model().attribute("cliente", hasProperty("state", is(state))))
                .andExpect(model().attribute("cliente", hasProperty("zipCode", is(zipCode))))
                .andExpect(model().attribute("cliente", hasProperty("email", is(email))))
                .andExpect(model().attribute("cliente", hasProperty("phoneNumber", is(phoneNumber))));

        ArgumentCaptor<Cliente> customerCaptor = ArgumentCaptor.forClass(Cliente.class);
        verify(clienteService).saveOrUpdate(customerCaptor.capture());

        Cliente boundCliente = customerCaptor.getValue();

        assertEquals(id, boundCliente.getId());
        assertEquals(firstName, boundCliente.getFirstName());
        assertEquals(lastName, boundCliente.getLastName());
        assertEquals(addressLine1, boundCliente.getAddressLine1());
        assertEquals(addressLine2, boundCliente.getAddressLine2());
        assertEquals(city, boundCliente.getCity());
        assertEquals(state, boundCliente.getState());
        assertEquals(zipCode, boundCliente.getZipCode());
        assertEquals(email, boundCliente.getEmail());
        assertEquals(phoneNumber, boundCliente.getPhoneNumber());


    }
}
