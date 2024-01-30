package com.tpi.alquiler.alquileres.services.mappers;


import com.tpi.alquiler.alquileres.model.Tarifas;
import com.tpi.alquiler.alquileres.model.dto.ResponseTarifasDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TarifasDTOMapper implements Function<Tarifas, ResponseTarifasDTO> {

    @Override
    public ResponseTarifasDTO apply(Tarifas tarifas) {
        return new ResponseTarifasDTO(
                tarifas.getIdTarifa(),
                tarifas.getTipoTarifa(),
                tarifas.getDefinicion(),
                tarifas.getDiaSemana(),
                tarifas.getDiaMes(),
                tarifas.getMes(),
                tarifas.getAnio(),
                tarifas.getMontoFijoAlquiler(),
                tarifas.getMontoMinutoFraccion(),
                tarifas.getMontoHora(),
                tarifas.getMontoKm()
        );
    }
}
