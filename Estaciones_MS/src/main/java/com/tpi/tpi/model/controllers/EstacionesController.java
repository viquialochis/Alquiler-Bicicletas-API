package com.tpi.tpi.model.controllers;

import com.tpi.tpi.model.dto.RequestEstacionDTO;
import com.tpi.tpi.model.dto.ResponseEstacionDTO;
import com.tpi.tpi.services.EstacionesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("estaciones") //http://localhost:8080/estaciones
public class EstacionesController {
    private final EstacionesService estacionesService;

    public EstacionesController(EstacionesService estacionesService) {
        this.estacionesService = estacionesService;
    }

    @GetMapping
    private ResponseEntity<List<ResponseEstacionDTO>> getAll(){
        List<ResponseEstacionDTO> estacionesFounded = estacionesService.getAll();
        return ResponseEntity.ok(estacionesFounded);
    }

    @PostMapping
    private ResponseEntity<ResponseEstacionDTO> add(@RequestBody RequestEstacionDTO estacion) {
        ResponseEstacionDTO estacionAdded = estacionesService.add(estacion);
        return ResponseEntity.ok(estacionAdded);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEstacionDTO> getById(@PathVariable Integer id) {
        ResponseEstacionDTO estacionFound = estacionesService.getById(id);
        return ResponseEntity.ok(estacionFound);}


    @PutMapping
    public ResponseEntity<ResponseEstacionDTO> update(@Valid @RequestBody RequestEstacionDTO estacion) {
        ResponseEstacionDTO estacionUpdated = estacionesService.update(estacion);
        return ResponseEntity.ok(estacionUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEstacionDTO> delete(@PathVariable Integer id) {
        ResponseEstacionDTO estacionDeleted = estacionesService.delete(id);
        return ResponseEntity.ok(estacionDeleted);

    }

    @GetMapping("/cercana")
    public ResponseEntity<ResponseEstacionDTO> getEstacionMasCercana(@RequestParam Double latitud, @RequestParam Double longitud) {
        ResponseEstacionDTO estacionCercana = estacionesService.getEstacionMasCercana(latitud, longitud);
        return ResponseEntity.ok(estacionCercana);
    }

}
