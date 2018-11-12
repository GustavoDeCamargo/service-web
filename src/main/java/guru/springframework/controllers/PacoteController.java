package guru.springframework.controllers;

import guru.springframework.domain.Hospedagem;
import guru.springframework.domain.Pacote;
import guru.springframework.services.PacoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PacoteController {

    private PacoteService pacoteService;

    @Autowired
    public void setHospedagemService(PacoteService pacoteService) {
        this.pacoteService = pacoteService;
    }

    @RequestMapping("/pacote/list")
    public String listPacotes(Model model){
        model.addAttribute("pacote", pacoteService.listAll());
        return "pacote/list";
    }

    @RequestMapping("/pacote/show/{id}")
    public String getHospedagens(@PathVariable Integer id, Model model){
        model.addAttribute("pacote", pacoteService.getById(id));
        return "pacote/show";
    }

    @RequestMapping("pacote/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("pacote", pacoteService.getById(id));
        return "pacote/pacoteform";
    }

    @RequestMapping("/pacote/new")
    public String newHospedagem(Model model){
        model.addAttribute("pacote", new Hospedagem());
        return "pacote/pacoteform";
    }

    @RequestMapping(value = "/pacote", method = RequestMethod.POST)
    public String saveOrUpdatePacote(Pacote pacote){
        Pacote savedPacote = pacoteService.saveOrUpdate(pacote);
        return "redirect:/pacote/show/" + savedPacote.getId();
    }

    @RequestMapping("/pacote/delete/{id}")
    public String delete(@PathVariable Integer id){
        pacoteService.delete(id);
        return "redirect:/pacote/list";
    }
}
