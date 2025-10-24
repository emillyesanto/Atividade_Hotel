package com.psii.hotel.service;

import com.psii.hotel.model.Hospede;
import com.psii.hotel.repository.HospedeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospedeService {

    private final HospedeRepository repository;

    public HospedeService(HospedeRepository repository) {
        this.repository = repository;
    }

    public List<Hospede> listarTodos() {
        return repository.findAll();
    }

    public Hospede salvar(Hospede hospede) {
        return repository.save(hospede);
    }

    public Hospede buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
