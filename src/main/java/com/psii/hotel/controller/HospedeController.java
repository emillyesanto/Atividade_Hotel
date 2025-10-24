package com.psii.hotel.controller;

import com.psii.hotel.model.Hospede;
import com.psii.hotel.service.HospedeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hospedes")
public class HospedeController {

    private final HospedeService service;

    public HospedeController(HospedeService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("hospedes", service.listarTodos());
        return "lista_hospedes";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("hospede", new Hospede());
        return "form_hospedes";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Hospede hospede) {
        service.salvar(hospede);
        return "redirect:/hospedes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("hospede", service.buscarPorId(id));
        return "form_hospedes";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/hospedes";
    }
}