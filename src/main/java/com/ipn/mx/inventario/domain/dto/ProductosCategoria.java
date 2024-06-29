package com.ipn.mx.inventario.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductosCategoria implements Serializable {
    private static final long serialVersionUID = 1L;

    private String categoria;
    private long Cantidad;
}