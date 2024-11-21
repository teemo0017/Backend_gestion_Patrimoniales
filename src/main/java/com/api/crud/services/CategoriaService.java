package com.api.crud.services;

import com.api.crud.models.CategoriaModel;
import com.api.crud.repositories.ICategoriaRepository;
import com.api.crud.repositories.dto.CategoriaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    ICategoriaRepository categoriaRepository;

    public List<CategoriaResponse> getCategorias() {
        List<CategoriaResponse> categoriasResponse = new ArrayList<>();
        List<CategoriaModel> categoriasModel = categoriaRepository.findAll();

        for (CategoriaModel var : categoriasModel) {
            CategoriaResponse categoriaResponse = new CategoriaResponse();
            categoriaResponse.setId(var.getId());
            categoriaResponse.setNombre(var.getNombre());
            categoriasResponse.add(categoriaResponse);
        }


        return categoriasResponse;
    }

}
