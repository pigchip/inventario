package com.ipn.mx.inventario.repository;

import com.ipn.mx.inventario.domain.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepo extends JpaRepository<Movimiento, Long> {
}
