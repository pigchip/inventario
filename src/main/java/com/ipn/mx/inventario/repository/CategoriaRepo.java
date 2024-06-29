package com.ipn.mx.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ipn.mx.inventario.domain.entity.Categoria;

import java.util.List;

public interface CategoriaRepo extends JpaRepository<Categoria, Long> {

    @Query(value = "SELECT * FROM CountProductsByCategory()", nativeQuery = true)
    List<Object[]> contarProductosPorCategoria();
}
