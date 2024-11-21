package com.api.crud.services;

import com.api.crud.models.BienesModel;
import com.api.crud.models.CategoriaModel;
import com.api.crud.models.UserModels;
import com.api.crud.repositories.IBienesRepository;
import com.api.crud.repositories.ICategoriaRepository;
import com.api.crud.repositories.IUserRepository;
import com.api.crud.repositories.dto.BienRequest;
import com.api.crud.repositories.dto.BienResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BienesService {

    @Autowired
    IBienesRepository bienesRepository;

    @Autowired
    ICategoriaRepository categoriaRepository;

    @Autowired
    IUserRepository userRepository;


    public BienesModel addBien(BienRequest request) {
        BienesModel nuevoBien = new BienesModel();

        if (request != null) {
            CategoriaModel categoriaModel = categoriaRepository.findById(request.getCategoriaId()).orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + request.getCategoriaId()));
            UserModels responsable = userRepository.findById(request.getResponsableId()).orElseThrow(() -> new RuntimeException("Responsable no encontrado con ID: " + request.getResponsableId()));
            UserModels creador = userRepository.findById(request.getUsuarioRegistroId()).orElseThrow(() -> new RuntimeException("Usuario que registra no encontrado con ID: " + request.getUsuarioRegistroId()));
            nuevoBien.setNombre(request.getNombre());
            nuevoBien.setDescripcion(request.getDescripcion());
            nuevoBien.setFechaAdquisicion(request.getFechaAdquisicion());
            nuevoBien.setValorAdquisicion(request.getValorAdquisicion());
            nuevoBien.setNumeroSerie(request.getNumeroSerie());
            nuevoBien.setMarca(request.getMarca());
            nuevoBien.setModelo(request.getModelo());
            nuevoBien.setCategoria(categoriaModel);
            nuevoBien.setUbicacion(request.getUbicacion());
            nuevoBien.setUsuarioRegistro(creador);
            nuevoBien.setResponsable(responsable);

            bienesRepository.save(nuevoBien);

        }

        return nuevoBien;
    }

    public List<BienResponse> listBienes() {
        List<BienResponse> response = new ArrayList<>();
        List<BienesModel> mapperBien = bienesRepository.findAll();

        for (BienesModel var : mapperBien) {
            BienResponse bienResponse = new BienResponse();
            bienResponse.setId(var.getId());
            bienResponse.setNombre(var.getNombre());
            bienResponse.setDescripcion(var.getDescripcion());
            bienResponse.setFechaAdquisicion(var.getFechaAdquisicion());
            bienResponse.setValorAdquisicion(var.getValorAdquisicion());
            bienResponse.setNumeroSerie(var.getNumeroSerie());
            bienResponse.setMarca(var.getMarca());
            bienResponse.setModelo(var.getModelo());
            bienResponse.setCategoria(var.getCategoria().getNombre());
            bienResponse.setUbicacion(var.getUbicacion());
            bienResponse.setUsuarioCreador(var.getUsuarioRegistro().getFirstName() + " " + var.getUsuarioRegistro().getLastName());
            bienResponse.setUsuarioResponsable(var.getResponsable().getFirstName() + " " + var.getResponsable().getLastName());
            response.add(bienResponse);

        }
        return response;
    }

    public void borrarBien(Long id) {
        bienesRepository.deleteById(id);
    }
}
