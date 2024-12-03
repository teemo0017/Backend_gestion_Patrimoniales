package com.api.crud.repositories.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BienResponse {
    private Long id;

    private String nombre;

    private String descripcion;

    private Date fechaAdquisicion;

    private BigDecimal valorAdquisicion;

    private String numeroSerie;

    private String marca;

    private String modelo;

    private String categoria; // ID de la categoría asociada

    private String ubicacion;

    private String usuarioCreador; // ID del usuario que registra el bien

    private String usuarioResponsable; // ID del usuario responsable (opcional)

}
