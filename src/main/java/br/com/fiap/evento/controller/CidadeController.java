package br.com.fiap.evento.controller;

import br.com.fiap.evento.model.Cidade;
import br.com.fiap.evento.service.CidadeService;
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
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService service;

    @GetMapping("form")
    public String loadForm(Model model){
        model.addAttribute("cidade", new Cidade());
        return "cidade/nova-cidade";
    }

    @PostMapping()
    @Transactional
    public String insert(@Valid Cidade cidade,
                         BindingResult br,
                         RedirectAttributes attributes){
        if(br.hasErrors()){
            return "cidade/nova-cidade";
        }
        cidade = service.insert(cidade);
        attributes.addFlashAttribute("mensagem", "Categoria salva com sucesso!");
        return "redirect:/cidades";
    }

    @GetMapping()
    public String findAll(Model model){
        List<Cidade> cidades = service.findAll();
        model.addAttribute("cidades", cidades);
        return "cidade/listar-cidade";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable ("id") Long id, Model model){
        Cidade cidade = service.findById(id);
        model.addAttribute("cidade", cidade);
        return "cidade/editar-cidade";
    }

    @PutMapping("{id}")
    public String update(@PathVariable ("id") Long id,
                        @Valid Cidade cidade,
                        BindingResult br){
        if(br.hasErrors()){
            cidade.setId(id);
            return "/cidade/editar-cidade";
        }
        service.update(id, cidade);
        return "redirect:/cidades";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable ("id") Long id, Model model){
        service.delete(id);
        return "redirect:/cidades";
    }


}
