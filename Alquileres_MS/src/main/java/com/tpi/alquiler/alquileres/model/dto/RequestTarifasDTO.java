package com.tpi.alquiler.alquileres.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestTarifasDTO {

        private int idTarifa;
        private int tipoTarifa;
        private char definicion;
        private Integer diaSemana;
        private Integer diaMes;
        private Integer mes;
        private Integer anio;
        private double montoFijoAlquiler;
        private double montoMinutoFraccion;
        private double montoHora;
        private double montoKm;

}
