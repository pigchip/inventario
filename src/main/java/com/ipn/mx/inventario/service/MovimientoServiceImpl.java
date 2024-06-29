package com.ipn.mx.inventario.service;

import com.ipn.mx.inventario.domain.dto.MovimientoDTO;
import com.ipn.mx.inventario.domain.dto.ProductoDTO;
import com.ipn.mx.inventario.domain.entity.Movimiento;
import com.ipn.mx.inventario.domain.entity.Producto;
import com.ipn.mx.inventario.repository.MovimientoRepo;
import com.ipn.mx.inventario.repository.ProductoRepository;
import com.ipn.mx.inventario.domain.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepo movimientoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public MovimientoDTO createMovimiento(MovimientoDTO movimientoDTO) {
        // Buscar el producto por ID
        Producto producto = productoRepository.findById(movimientoDTO.getProducto().getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto not found"));

        // Crear el movimiento con el producto existente
        Movimiento movimiento = Movimiento.builder()
                .tipoMovimiento(movimientoDTO.getTipoMovimiento())
                .fechaMovimiento(movimientoDTO.getFechaMovimiento())
                .cantidad(movimientoDTO.getCantidad())
                .producto(producto)
                .build();

        Movimiento savedMovimiento = movimientoRepository.save(movimiento);

        return convertToDto(savedMovimiento);
    }

    @Override
    public MovimientoDTO updateMovimiento(Long id, MovimientoDTO movimientoDTO) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento not found"));
        movimiento.setTipoMovimiento(movimientoDTO.getTipoMovimiento());
        movimiento.setFechaMovimiento(movimientoDTO.getFechaMovimiento());
        movimiento.setCantidad(movimientoDTO.getCantidad());

        Producto producto = productoRepository.findById(movimientoDTO.getProducto().getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto not found"));
        movimiento.setProducto(producto);

        Movimiento updatedMovimiento = movimientoRepository.save(movimiento);
        return convertToDto(updatedMovimiento);
    }

    @Override
    public void deleteMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public MovimientoDTO getMovimientoById(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento not found"));
        return convertToDto(movimiento);
    }

    @Override
    public List<MovimientoDTO> getAllMovimientos() {
        List<Movimiento> movimientos = movimientoRepository.findAll();
        return movimientos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private MovimientoDTO convertToDto(Movimiento movimiento) {
        return MovimientoDTO.builder()
                .idMovimiento(movimiento.getIdMovimiento())
                .tipoMovimiento(movimiento.getTipoMovimiento())
                .fechaMovimiento(movimiento.getFechaMovimiento())
                .cantidad(movimiento.getCantidad())
                .producto(convertToDto(movimiento.getProducto()))
                .build();
    }

    private ProductoDTO convertToDto(Producto producto) {
        return ProductoDTO.builder()
                .idProducto(producto.getIdProducto())
                .nombreProducto(producto.getNombreProducto())
                .descripcionProducto(producto.getDescripcionProducto())
                .precio(producto.getPrecio())
                .existencia(producto.getExistencia())
                .idCategoria(producto.getIdCategoria().getIdCategoria())
                .build();
    }

    private Producto convertToEntity(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setIdProducto(productoDTO.getIdProducto());
        producto.setNombreProducto(productoDTO.getNombreProducto());
        producto.setDescripcionProducto(productoDTO.getDescripcionProducto());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setExistencia(productoDTO.getExistencia());

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(productoDTO.getIdCategoria());
        producto.setIdCategoria(categoria);

        return producto;
    }
}
