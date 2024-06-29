package com.ipn.mx.inventario.service;

import com.ipn.mx.inventario.domain.dto.MovimientoDTO;

import java.util.List;

public interface MovimientoService {
    MovimientoDTO createMovimiento(MovimientoDTO movimientoDTO);
    MovimientoDTO updateMovimiento(Long id, MovimientoDTO movimientoDTO);
    void deleteMovimiento(Long id);
    MovimientoDTO getMovimientoById(Long id);
    List<MovimientoDTO> getAllMovimientos();
}
