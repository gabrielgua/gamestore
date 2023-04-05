package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.model.Categoria;
import com.gabriel.gamestore.domain.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CategoriaService {

    private CategoriaRepository repository;

    public Categoria buscarPorId(Long categoriaId) {
        return repository.findById(categoriaId).orElseThrow();
    }

    public List<Categoria> buscarVariosPorIds(Set<Long> categoriaIds) {
        return repository.findAllById(categoriaIds);
    }
}