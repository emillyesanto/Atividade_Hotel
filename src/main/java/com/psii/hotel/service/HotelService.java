package com.psii.hotel.service;

import com.psii.hotel.model.Hotel;
import com.psii.hotel.repository.HotelRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HotelService {

    private final HotelRepository repository;

    public HotelService(HotelRepository repository) {
        this.repository = repository;
    }

    public List<Hotel> listarTodos() {
        return repository.findAll();
    }

    public Hotel salvar(Hotel hotel) {
        return repository.save(hotel);
    }

    public Hotel buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}