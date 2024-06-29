package com.ipn.mx.inventario.service;

import java.util.List;
import java.util.Optional;

import com.ipn.mx.inventario.domain.dto.ProductoDTO;
import com.ipn.mx.inventario.domain.dto.ProductosCategoria;
import com.ipn.mx.inventario.domain.entity.Producto;


public interface ProductoService {
    public List<ProductosCategoria> productoPorCategoria();

    public List<Producto> findAll();
    public Optional<Producto> findById(long id);
    public Producto save(ProductoDTO producto);
    public Producto update(Long id, ProductoDTO producto);
    public void deleteById(Producto producto);
}
