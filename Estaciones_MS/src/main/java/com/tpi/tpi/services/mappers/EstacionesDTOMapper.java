package com.tpi.tpi.services.mappers;

import com.tpi.tpi.model.Estaciones;
import com.tpi.tpi.model.dto.ResponseEstacionDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EstacionesDTOMapper implements Function<Estaciones, ResponseEstacionDTO> {

    @Override
    public ResponseEstacionDTO apply(Estaciones estaciones) {
        return new ResponseEstacionDTO(
                estaciones.getEstacionId(),
                estaciones.getNombre(),
                estaciones.getFechaHoraCreacion(),
                estaciones.getLatitud(),
                estaciones.getLongitud()
        );
    }
}
