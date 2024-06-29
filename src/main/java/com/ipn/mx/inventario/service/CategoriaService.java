package com.ipn.mx.inventario.service;

import java.util.List;
import java.util.Optional;

import com.ipn.mx.inventario.domain.dto.ProductosCategoria;
import com.ipn.mx.inventario.domain.entity.Categoria;

public interface CategoriaService {
    public List<ProductosCategoria> productoPorCategoria();
    
    List<Categoria> findAll();

    Optional<Categoria> findById(Long id);

    Categoria save(Categoria categoria);

    void delete(Long id);
}
