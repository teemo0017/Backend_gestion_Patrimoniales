package com.api.crud.repositories.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BienRequest {

    private Long id;

    @NotNull
    private String nombre;

    private String descripcion;

    @NotNull
    private Date fechaAdquisicion;

    @NotNull
    private BigDecimal valorAdquisicion;

    private String numeroSerie;

    private String marca;

    private String modelo;

    @NotNull
    private Long categoriaId; // ID de la categoría asociada

    @NotNull
    private String ubicacion;

    @NotNull
    private Long usuarioRegistroId; // ID del usuario que registra el bien

    private Long responsableId; // ID del usuario responsable (opcional)
}
