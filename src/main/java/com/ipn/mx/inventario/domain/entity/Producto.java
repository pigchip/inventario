package com.ipn.mx.inventario.domain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "producto")
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProducto;

    @Column(name = "nombre_producto", length =50, nullable = false)
    private String nombreProducto;

    @Column(name = "descripcion_producto", length =250, nullable = false)
    private String descripcionProducto;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "existencia", nullable = false)
    private int existencia;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria idCategoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Movimiento> movimientos;
}