package com.ipn.mx.inventario.domain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Setter
    @Column(name = "nombre_categoria", length = 100, nullable = false)
    private String nombreCategoria;

    @Setter
    @Column(name = "descripcion_categoria", length = 250, nullable = false)
    private String descripcionCategoria;

    @JsonIgnoreProperties(
            value = {
                    "idProducto",
                    "hibernateLzyInitializer",
                    "handler"
            },
            allowSetters = true
    )
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idCategoria", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Producto> productos;


    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        for(Producto prod : productos) {
            prod.setIdCategoria(this);
        }
    }
}