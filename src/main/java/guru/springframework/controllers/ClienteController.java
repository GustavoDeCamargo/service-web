package guru.springframework.controllers;

import guru.springframework.domain.Cliente;
import guru.springframework.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping("/cliente")
@Controller
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @RequestMapping({"/list", "/"})
    public String listClientes(Model model){
        model.addAttribute("cliente", clienteService.listAll());
        return "cliente/list";
    }

    @RequestMapping("/show/{id}")
    public String showCliente(@PathVariable Integer id, Model model){
        model.addAttribute("cliente", clienteService.getById(id));
        return "cliente/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("cliente", clienteService.getById(id));
        return "cliente/clienteform";
    }

    @RequestMapping("/new")
    public String newCliente(Model model){
        model.addAttribute("cliente", new Cliente());
        return "cliente/clienteform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdate(Cliente cliente){
        Cliente newCliente = clienteService.saveOrUpdate(cliente);
        return "redirect:cliente/show/" + newCliente.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        clienteService.delete(id);
        return "redirect:/cliente/list";
    }
}
