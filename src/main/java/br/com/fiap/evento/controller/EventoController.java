package br.com.fiap.evento.controller;

import br.com.fiap.evento.model.Cidade;
import br.com.fiap.evento.model.Evento;
import br.com.fiap.evento.service.CidadeService;
import br.com.fiap.evento.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoService service;

    @Autowired
    private CidadeService cidadeService;

    @ModelAttribute("cidades")
    public List<Cidade> cidades(){return cidadeService.findAll();}

    
    @GetMapping("form")
    public String loadForm(Model model){
        model.addAttribute("evento", new Evento());
        return "evento/novo-evento";
    }

    @PostMapping()
    @Transactional
    public String insert(@Valid Evento evento,
                         BindingResult br,
                         RedirectAttributes attributes){
        if(br.hasErrors()){
            return "evento/novo-evento";
        }
        evento = service.insert(evento);
        attributes.addFlashAttribute("mensagem", "Categoria salva com sucesso!");
        return "redirect:/eventos";
    }

    @GetMapping()
    public String findAll(Model model){
        List<Evento> eventos = service.findAll();
        model.addAttribute("eventos", eventos);
        return "evento/listar-eventos";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        Evento evento = service.findById(id);
        model.addAttribute("evento", evento);
        return "evento/editar-evento";
    }

    @PutMapping("{id}")
    public String update(@PathVariable ("id") Long id,
                         @Valid Evento evento,
                         BindingResult br){
        if(br.hasErrors()){
            evento.setId(id);
            return "/evento/editar-evento";
        }
        service.update(id, evento);
        return "redirect:/eventos";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable ("id") Long id, Model model){
        service.delete(id);
        return "redirect:/eventos";
    }
}
