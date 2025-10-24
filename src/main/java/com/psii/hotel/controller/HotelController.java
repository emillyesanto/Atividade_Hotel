package com.psii.hotel.controller;

import com.psii.hotel.model.Hotel;
import com.psii.hotel.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hoteis")
public class HotelController {

    private final HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("hoteis", service.listarTodos());
        return "lista_hotel";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "form_hotel";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Hotel hotel) {
        service.salvar(hotel);
        return "redirect:/hoteis";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("hotel", service.buscarPorId(id));
        return "form_hotel";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/hoteis";
    }
}