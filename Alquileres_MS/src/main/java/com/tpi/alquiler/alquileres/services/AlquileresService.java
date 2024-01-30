package com.tpi.alquiler.alquileres.services;

import com.tpi.alquiler.alquileres.model.dto.RequestUpdateAlquileresDTO;
import com.tpi.alquiler.alquileres.model.dto.ResponseAlquileresDTO;
import com.tpi.alquiler.alquileres.model.dto.RequestAlquileresDTO;

import java.util.List;

public interface AlquileresService extends Service<ResponseAlquileresDTO, RequestAlquileresDTO, Integer>{
    ResponseAlquileresDTO updateAlquiler(RequestUpdateAlquileresDTO alquilerDTO);

    List<ResponseAlquileresDTO> alquileresEnCurso();

    ResponseAlquileresDTO finalizarAlquiler(String moneda, Integer id, Integer estacionDevId);
}
