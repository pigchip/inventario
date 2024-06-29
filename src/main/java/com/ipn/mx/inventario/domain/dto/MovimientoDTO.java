package com.ipn.mx.inventario.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoDTO implements Serializable {
    private Long idMovimiento;
    private char tipoMovimiento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaMovimiento;
    private Integer cantidad;
    private ProductoDTO producto;
}
