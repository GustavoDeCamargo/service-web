package guru.springframework.controllers;

import guru.springframework.domain.Passagem;
import guru.springframework.services.PassagemService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PassagemControllerTest {

    @Mock //Mockito Mock object
    private PassagemService passagemService;

    @InjectMocks //setups up controller, and injects mock objects into it.
    private PassagemController passagemController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this); //initilizes controller and mocks

        mockMvc = MockMvcBuilders.standaloneSetup(passagemController).build();
    }

    @Test
    public void testList() throws Exception{

        List<Passagem> passagems = new ArrayList<>();
        passagems.add(new Passagem());
        passagems.add(new Passagem());

        //specific Mockito interaction, tell stub to return list of passagems
        when(passagemService.listAll()).thenReturn((List) passagems); //need to strip generics to keep Mockito happy.

        mockMvc.perform(get("/passagem/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("passagem/list"))
                .andExpect(model().attribute("passagems", hasSize(2)));
    }

    @Test
    public void testShow() throws Exception{
        Integer id = 1;

        //Tell Mockito stub to return new passagem for ID 1
        when(passagemService.getById(id)).thenReturn(new Passagem());

        mockMvc.perform(get("/passagem/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("passagem/show"))
                .andExpect(model().attribute("passagem", instanceOf(Passagem.class)));
    }

    @Test
    public void testEdit() throws Exception{
        Integer id = 1;

        //Tell Mockito stub to return new passagem for ID 1
        when(passagemService.getById(id)).thenReturn(new Passagem());

        mockMvc.perform(get("/passagem/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("passagem/productform"))
                .andExpect(model().attribute("passagem", instanceOf(Passagem.class)));
    }

    @Test
    public void testNewProduct() throws Exception {
        Integer id = 1;

        //should not call service
        verifyZeroInteractions(passagemService);

        mockMvc.perform(get("/passagem/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("passagem/productform"))
                .andExpect(model().attribute("passagem", instanceOf(Passagem.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        String origem = "Test Description";
        BigDecimal price = new BigDecimal("12.00");
        String destino = "example.com";

        Passagem returnPassagem = new Passagem();
        returnPassagem.setId(id);
        returnPassagem.setOrigem(origem);
        returnPassagem.setPrice(price);
        returnPassagem.setDestino(destino);

        when(passagemService.saveOrUpdate(Matchers.<Passagem>any())).thenReturn(returnPassagem);

        mockMvc.perform(post("/passagem")
        .param("id", "1")
        .param("origem", origem)
        .param("price", "12.00")
        .param("destino", "example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/passagem/show/1"))
                .andExpect(model().attribute("passagem", instanceOf(Passagem.class)))
                .andExpect(model().attribute("passagem", hasProperty("id", is(id))))
                .andExpect(model().attribute("passagem", hasProperty("origem", is(origem))))
                .andExpect(model().attribute("passagem", hasProperty("price", is(price))))
                .andExpect(model().attribute("passagem", hasProperty("destino", is(destino))));

        //verify properties of bound object
        ArgumentCaptor<Passagem> boundProduct = ArgumentCaptor.forClass(Passagem.class);
        verify(passagemService).saveOrUpdate(boundProduct.capture());

        assertEquals(id, boundProduct.getValue().getId());
        assertEquals(origem, boundProduct.getValue().getOrigem());
        assertEquals(price, boundProduct.getValue().getPrice());
        assertEquals(destino, boundProduct.getValue().getDestino());
    }

    @Test
    public void testDelete() throws Exception{
        Integer id = 1;

        mockMvc.perform(get("/passagem/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/passagem/list"));

        verify(passagemService, times(1)).delete(id);
    }
}
