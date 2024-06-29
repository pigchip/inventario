package com.ipn.mx.inventario.integration;

import com.ipn.mx.inventario.domain.dto.MovimientoDTO;
import com.ipn.mx.inventario.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<MovimientoDTO> createMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        MovimientoDTO createdMovimiento = movimientoService.createMovimiento(movimientoDTO);
        return ResponseEntity.ok(createdMovimiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoDTO> updateMovimiento(@PathVariable Long id, @RequestBody MovimientoDTO movimientoDTO) {
        MovimientoDTO updatedMovimiento = movimientoService.updateMovimiento(id, movimientoDTO);
        return ResponseEntity.ok(updatedMovimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDTO> getMovimientoById(@PathVariable Long id) {
        MovimientoDTO movimientoDTO = movimientoService.getMovimientoById(id);
        return ResponseEntity.ok(movimientoDTO);
    }

    @GetMapping
    public ResponseEntity<List<MovimientoDTO>> getAllMovimientos() {
        List<MovimientoDTO> movimientos = movimientoService.getAllMovimientos();
        return ResponseEntity.ok(movimientos);
    }
}
