package com.api.crud.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REGISTROS_BIENES")
public class BienesModel implements Serializable {
    private static final long serialVersionUID = -3138191497993461942L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Temporal(TemporalType.DATE)
    private Date fechaAdquisicion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorAdquisicion;

    @Column(length = 50)
    private String numeroSerie;

    @Column(length = 50)
    private String marca;

    @Column(length = 50)
    private String modelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaModel categoria;

    @JoinColumn(name = "ubicacion_id", nullable = false)
    private String ubicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_registro_id", nullable = false)
    private UserModels usuarioRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsable_id")
    private UserModels responsable;
}
