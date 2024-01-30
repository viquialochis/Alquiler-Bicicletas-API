package com.tpi.alquiler.alquileres.services.mappers;


import com.tpi.alquiler.alquileres.model.Alquileres;
import com.tpi.alquiler.alquileres.model.dto.ResponseAlquileresDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AlquileresDTOMapper implements Function<Alquileres, ResponseAlquileresDTO> {

    @Override
    public ResponseAlquileresDTO apply(Alquileres alquileres) {
        return new ResponseAlquileresDTO(
                alquileres.getIdAlquiler(),
                alquileres.getIdCliente(),
                alquileres.getEstado(),
                alquileres.getEstacionRetiro(),
                alquileres.getEstacionDevolucion(),
                alquileres.getFechaHoraRetiro(),
                alquileres.getFechaHoraDevolucion(),
                alquileres.getMonto(),
                alquileres.getIdTarifa()
        );
    }
}
