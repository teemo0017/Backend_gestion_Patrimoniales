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

    public BienRequest buscarBien(Long id) {
        BienesModel bienesModel = bienesRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
        BienRequest bienResponse = new BienRequest();

        bienResponse.setId(bienesModel.getId());
        bienResponse.setNombre(bienesModel.getNombre());
        bienResponse.setDescripcion(bienesModel.getDescripcion());
        bienResponse.setFechaAdquisicion(bienesModel.getFechaAdquisicion());
        bienResponse.setValorAdquisicion(bienesModel.getValorAdquisicion());
        bienResponse.setNumeroSerie(bienesModel.getNumeroSerie());
        bienResponse.setMarca(bienesModel.getMarca());
        bienResponse.setModelo(bienesModel.getModelo());
        bienResponse.setCategoriaId(bienesModel.getCategoria().getId());
        bienResponse.setUbicacion(bienesModel.getUbicacion());
        bienResponse.setUsuarioRegistroId(bienesModel.getUsuarioRegistro().getId());
        bienResponse.setResponsableId(bienesModel.getResponsable().getId());


        return bienResponse;
    }

    public void actualizarBien(BienRequest bienActualizado) {

        if (bienActualizado.getId() != null) {

            this.bienesRepository.findById(bienActualizado.getId()).map(bien -> {

                CategoriaModel categoriaModel = new CategoriaModel();
                UserModels usuarioRegistro = new UserModels();
                UserModels usuarioResponsable = new UserModels();

                categoriaModel = categoriaRepository.findById(bienActualizado.getCategoriaId()).orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + bienActualizado.getCategoriaId()));
                usuarioRegistro = userRepository.findById(bienActualizado.getUsuarioRegistroId()).orElseThrow(() -> new RuntimeException("Usuario Registro no encontrada con ID: " + bienActualizado.getUsuarioRegistroId()));
                usuarioResponsable = userRepository.findById(bienActualizado.getResponsableId()).orElseThrow(() -> new RuntimeException("Usuario Responsable no encontrada con ID: " + bienActualizado.getResponsableId()));

                bien.setNombre(bienActualizado.getNombre());
                bien.setDescripcion(bienActualizado.getDescripcion());
                bien.setFechaAdquisicion(bienActualizado.getFechaAdquisicion());
                bien.setValorAdquisicion(bienActualizado.getValorAdquisicion());
                bien.setNumeroSerie(bienActualizado.getNumeroSerie());
                bien.setMarca(bienActualizado.getMarca());
                bien.setModelo(bienActualizado.getModelo());
                bien.setCategoria(categoriaModel);
                bien.setUbicacion(bienActualizado.getUbicacion());
                bien.setUsuarioRegistro(usuarioRegistro);
                bien.setResponsable(usuarioResponsable);
                return bienesRepository.save(bien);
            }).orElseThrow(() -> new RuntimeException("Bien no encontrado con ID: " + bienActualizado.getId()));
        }

    }
}
