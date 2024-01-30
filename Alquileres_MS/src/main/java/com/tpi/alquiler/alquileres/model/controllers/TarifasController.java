package com.tpi.alquiler.alquileres.model.controllers;

import com.tpi.alquiler.alquileres.model.dto.ResponseTarifasDTO;
import com.tpi.alquiler.alquileres.model.dto.RequestTarifasDTO;
import com.tpi.alquiler.alquileres.services.TarifasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarifas")

public class TarifasController {

    @Autowired
    private TarifasService tarifasService;


    @GetMapping
    public List<ResponseTarifasDTO> getAll() {
        return tarifasService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseTarifasDTO getById(@PathVariable int id) {
        return tarifasService.getById(id);
    }

    @PostMapping
    public ResponseTarifasDTO add(@RequestBody RequestTarifasDTO tarifaDTO) {
        return tarifasService.add(tarifaDTO);
    }

    @PutMapping
    public ResponseTarifasDTO update(@Valid @RequestBody RequestTarifasDTO tarifaDTO) {
        return tarifasService.update(tarifaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseTarifasDTO delete(@PathVariable int id) {
        return tarifasService.delete(id);
    }
}
