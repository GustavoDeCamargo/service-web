package guru.springframework.controllers;

import guru.springframework.domain.Hospedagem;
import guru.springframework.services.HospedagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HospedagemController {

    private HospedagemService hospedagemService;

    @Autowired
    public void setHospedagemService(HospedagemService hospedagemService) {
        this.hospedagemService = hospedagemService;
    }

    @RequestMapping("/hospedagem/list")
    public String listHospedagens(Model model){
        model.addAttribute("hospedagem", hospedagemService.listAll());
        return "hospedagem/list";
    }

    @RequestMapping("/hospedagem/show/{id}")
    public String getHospedagens(@PathVariable Integer id, Model model){
        model.addAttribute("hospedagem", hospedagemService.getById(id));
        return "hospedagem/show";
    }

    @RequestMapping("hospedagem/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("hospedagem", hospedagemService.getById(id));
        return "hospedagem/hospedagemform";
    }

    @RequestMapping("/hospedagem/new")
    public String newHospedagem(Model model){
        model.addAttribute("hospedagem", new Hospedagem());
        return "hospedagem/hospedagemform";
    }

    @RequestMapping(value = "/hospedagem", method = RequestMethod.POST)
    public String saveOrUpdateHospedagem(Hospedagem hospedagem){
        Hospedagem savedHospedagem = hospedagemService.saveOrUpdate(hospedagem);
        return "redirect:/hospedagem/show/" + savedHospedagem.getId();
    }

    @RequestMapping("/hospedagem/delete/{id}")
    public String delete(@PathVariable Integer id){
        hospedagemService.delete(id);
        return "redirect:/hospedagem/list";
    }
}
