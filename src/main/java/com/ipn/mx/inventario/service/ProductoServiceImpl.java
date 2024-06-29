package com.ipn.mx.inventario.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipn.mx.inventario.domain.dto.ProductoDTO;
import com.ipn.mx.inventario.domain.dto.ProductosCategoria;
import com.ipn.mx.inventario.domain.entity.Categoria;
import com.ipn.mx.inventario.domain.entity.Producto;
import com.ipn.mx.inventario.repository.CategoriaRepo;
import com.ipn.mx.inventario.repository.ProductoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{
    @Autowired
    ProductoRepository repository;
    @Autowired
    CategoriaRepo categoriaRepo;

    @Override
    @Transactional(readOnly = true)
    public List<ProductosCategoria> productoPorCategoria() {
        List<Object[]> resultados = repository.contarProductoPorCategoria();
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
    public List<Producto> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Producto save(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombreProducto(productoDTO.getNombreProducto());
        producto.setDescripcionProducto(productoDTO.getDescripcionProducto());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setExistencia(productoDTO.getExistencia());

        System.out.println(productoDTO.getIdCategoria());
        Optional<Categoria> categoriaOptional = categoriaRepo.findById(productoDTO.getIdCategoria());
        if (categoriaOptional.isPresent()) {
            producto.setIdCategoria(categoriaOptional.get());
        } else {
            // Handle the case where category is not found (e.g., log a warning or throw a custom exception)
            System.out.println("Categoria with ID " + productoDTO.getIdCategoria() + " not found");
        }

        return repository.save(producto);
    }

    @Override
    public Producto update(Long id, ProductoDTO producto) {
        Optional<Producto> p = repository.findById(id);
        if(p.isEmpty()){
            return null;
        }else{
            Producto productoAux = p.get();
            productoAux.setNombreProducto(producto.getNombreProducto());
            productoAux.setDescripcionProducto(producto.getDescripcionProducto());
            productoAux.setPrecio(producto.getPrecio());
            productoAux.setExistencia(producto.getExistencia());
            Categoria categoria = categoriaRepo.findById(producto.getIdCategoria()).orElseThrow();
            productoAux.setIdCategoria(categoria);
            return repository.save(productoAux);
        }
    }

    @Override
    @Transactional
    public void deleteById(Producto producto) {
        repository.deleteById(producto.getIdProducto());
    }
}
