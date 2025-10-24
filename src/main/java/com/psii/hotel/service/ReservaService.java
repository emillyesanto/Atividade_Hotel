package com.psii.hotel.service;

import com.psii.hotel.model.Reserva;
import com.psii.hotel.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository repository;

    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
    }

    public List<Reserva> listarTodos() {
        return repository.findAll();
    }

    public Reserva buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void salvar(Reserva reserva) {
        repository.save(reserva);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}