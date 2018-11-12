package guru.springframework.controllers;

import guru.springframework.domain.Passagem;
import guru.springframework.services.PassagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PassagemController {

    private PassagemService passagemService;

    @Autowired
    public void setPassagemServiceService(PassagemService passagemService) {
        this.passagemService = passagemService;
    }

    @RequestMapping("/passagem/list")
    public String listPassagem(Model model){
        model.addAttribute("passagem", passagemService.listAll());
        return "passagem/list";
    }

    @RequestMapping("/passagem/show/{id}")
    public String getPassagem(@PathVariable Integer id, Model model){
        model.addAttribute("passagem", passagemService.getById(id));
        return "passagem/show";
    }

    @RequestMapping("passagem/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("passagem", passagemService.getById(id));
        return "passagem/passagemform";
    }

    @RequestMapping("/passagem/new")
    public String newPassagem(Model model){
        model.addAttribute("passagem", new Passagem());
        return "passagem/passagemform";
    }

    @RequestMapping(value = "/passagem", method = RequestMethod.POST)
    public String saveOrUpdatePassagem(Passagem passagem){
        Passagem savedPassagem = passagemService.saveOrUpdate(passagem);
        return "redirect:/passagem/show/" + savedPassagem.getId();
    }

    @RequestMapping("/passagem/delete/{id}")
    public String delete(@PathVariable Integer id){
        passagemService.delete(id);
        return "redirect:/passagem/list";
    }
}
