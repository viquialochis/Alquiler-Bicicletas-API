package com.tpi.alquiler.alquileres.model.controllers;



import com.tpi.alquiler.alquileres.model.dto.RequestUpdateAlquileresDTO;
import com.tpi.alquiler.alquileres.model.dto.ResponseAlquileresDTO;
import com.tpi.alquiler.alquileres.model.dto.RequestAlquileresDTO;
import com.tpi.alquiler.alquileres.services.AlquileresService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alquileres") //http://localhost:8080/alquileres

public class AlquileresController {
    @Autowired
    private AlquileresService alquileresService;

    @GetMapping
    private ResponseEntity<List<ResponseAlquileresDTO>> getAll(){
        List<ResponseAlquileresDTO> alquleresFounded = alquileresService.getAll();
        return ResponseEntity.ok(alquleresFounded);
    }

    @PostMapping
    private ResponseEntity<ResponseAlquileresDTO> iniciarAlquiler(@RequestBody RequestAlquileresDTO alquiler) {
        ResponseAlquileresDTO alquilerAgregado = alquileresService.add(alquiler);
        return ResponseEntity.ok(alquilerAgregado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAlquileresDTO> getById(@PathVariable Integer id) {
        ResponseAlquileresDTO alquilerFounded = alquileresService.getById(id);
        return ResponseEntity.ok(alquilerFounded);}

    @GetMapping("/enCurso")
    public ResponseEntity<List<ResponseAlquileresDTO>> getByEnCurso() {
        List<ResponseAlquileresDTO>alquileresFounded = alquileresService.alquileresEnCurso();
        return ResponseEntity.ok(alquileresFounded);}


    @PutMapping
    public ResponseEntity<ResponseAlquileresDTO> update(@Valid @RequestBody RequestUpdateAlquileresDTO alquiler) {
        ResponseAlquileresDTO alquilerUpdated = alquileresService.updateAlquiler(alquiler);
        return ResponseEntity.ok(alquilerUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseAlquileresDTO> delete(@PathVariable Integer id) {
        ResponseAlquileresDTO alquilerEliminado = alquileresService.delete(id);
        return ResponseEntity.ok(alquilerEliminado);

    }
    @PutMapping("/finalizar")
    public ResponseEntity<ResponseAlquileresDTO> finalizarAlq(@RequestParam Integer idAlq, @RequestParam Integer idEstacionDestino, @RequestParam String moneda) {
        return ResponseEntity.ok(alquileresService.finalizarAlquiler(moneda, idAlq, idEstacionDestino));
    }


}
