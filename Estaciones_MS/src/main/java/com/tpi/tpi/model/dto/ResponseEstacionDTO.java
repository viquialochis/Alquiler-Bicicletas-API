package com.tpi.tpi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEstacionDTO {

    private int estacionId;

    private String nombre;

    private String fechaHoraCreacion;

    private double latitud;

    private double longitud;
}
