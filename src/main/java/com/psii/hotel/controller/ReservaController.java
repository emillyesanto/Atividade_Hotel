package com.psii.hotel.controller;

import com.psii.hotel.model.Reserva;
import com.psii.hotel.service.ReservaService;
import com.psii.hotel.service.HospedeService;
import com.psii.hotel.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final HospedeService hospedeService;
    private final HotelService hotelService;

    public ReservaController(ReservaService reservaService, HospedeService hospedeService, HotelService hotelService) {
        this.reservaService = reservaService;
        this.hospedeService = hospedeService;
        this.hotelService = hotelService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("reservas", reservaService.listarTodos());
        return "lista_reservas";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("hospedes", hospedeService.listarTodos());
        model.addAttribute("hoteis", hotelService.listarTodos());
        return "form_reservas";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Reserva reserva) {
        var hospede = hospedeService.buscarPorId(reserva.getHospede().getId());
        var hotel = hotelService.buscarPorId(reserva.getHotel().getId());

        reserva.setHospede(hospede);
        reserva.setHotel(hotel);

        reservaService.salvar(reserva);
        return "redirect:/reservas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("reserva", reservaService.buscarPorId(id));
        model.addAttribute("hospedes", hospedeService.listarTodos());
        model.addAttribute("hoteis", hotelService.listarTodos());
        return "form_reservas";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        reservaService.excluir(id);
        return "redirect:/reservas";
    }
}