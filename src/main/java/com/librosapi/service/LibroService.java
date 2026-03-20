package com.librosapi.service;

import com.librosapi.model.Libro;
import com.librosapi.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repository;

    public List<Libro> listarTodos() {
        return repository.findAll();
    }

    public Optional<Libro> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Libro guardar(Libro libro) {
        return repository.save(libro);
    }

    public Optional<Libro> actualizar(Long id, Libro detallesLibro) {
        return repository.findById(id).map(libro -> {
            libro.setTitulo(detallesLibro.getTitulo());
            libro.setAutor(detallesLibro.getAutor());
            libro.setAnioPublicacion(detallesLibro.getAnioPublicacion());
            libro.setPrecio(detallesLibro.getPrecio());
            libro.setDisponible(detallesLibro.isDisponible());
            return repository.save(libro);
        });
    }

    public boolean eliminar(Long id) {
        return repository.findById(id).map(libro -> {
            repository.delete(libro);
            return true;
        }).orElse(false);
    }
}