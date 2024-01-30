package com.tpi.alquiler.alquileres.model.dto;

import com.tpi.alquiler.alquileres.model.Tarifas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAlquileresDTO {
    private int idAlquiler;

    private int idCliente;

    private int estacionRetiro;

    private int idTarifa;
}
