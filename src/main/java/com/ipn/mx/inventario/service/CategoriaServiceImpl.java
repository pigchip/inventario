package com.ipn.mx.inventario.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipn.mx.inventario.domain.dto.ProductosCategoria;
import com.ipn.mx.inventario.domain.entity.Categoria;
import com.ipn.mx.inventario.repository.CategoriaRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepo repository;

        @Override
    @Transactional(readOnly = true)
    public List<ProductosCategoria> productoPorCategoria() {
        List<Object[]> resultados = repository.contarProductosPorCategoria();
        List<ProductosCategoria> lista = new ArrayList<>();
        for(Object[] registro : resultados){
            ProductosCategoria pc = new ProductosCategoria();
            pc.setCategoria((String)registro[0]);
            pc.setCantidad((Long)registro[1]);
            lista.add(pc);
        }

        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return repository.save(categoria);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
