package com.ipn.mx.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ipn.mx.inventario.domain.entity.Producto;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query(value = "SELECT * FROM CountProductsByCategory()", nativeQuery = true)
    List<Object[]> contarProductoPorCategoria();
}
