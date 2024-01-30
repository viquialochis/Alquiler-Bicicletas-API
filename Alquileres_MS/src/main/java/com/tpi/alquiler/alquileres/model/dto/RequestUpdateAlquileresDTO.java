package com.tpi.alquiler.alquileres.model.dto;

import com.tpi.alquiler.alquileres.model.Tarifas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateAlquileresDTO {

    private int idAlquiler;

    private int idCliente;

    private int estado;

    private int estacionRetiro;

    private int estacionDevolucion;

    private double monto;

    private int idTarifa;
}
