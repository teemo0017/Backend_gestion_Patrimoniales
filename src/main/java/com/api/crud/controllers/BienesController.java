package com.api.crud.controllers;

import com.api.crud.models.BienesModel;
import com.api.crud.repositories.dto.BienRequest;
import com.api.crud.repositories.dto.BienResponse;
import com.api.crud.repositories.dto.CategoriaResponse;
import com.api.crud.services.BienesService;
import com.api.crud.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bien")
public class BienesController {

    @Autowired
    BienesService bienesService;

    @Autowired
    CategoriaService categoriaService;

    @PostMapping(path = "/addbien")
    public ResponseEntity<Void> saveBien(@RequestBody BienRequest request) {
        BienesModel bienesModel = this.bienesService.addBien(request);
        if (bienesModel != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/getallcategorias")
    public List<CategoriaResponse> getAllCategorias() {
        return categoriaService.getCategorias();
    }

    @GetMapping(path = "/getallbienes")
    public List<BienResponse> getAllBienes() {
        return this.bienesService.listBienes();
    }

    @GetMapping(path = "borrarbien/{id}")
    public void borrarBIen(@PathVariable Long id) {
        this.bienesService.borrarBien(id);
    }

}
