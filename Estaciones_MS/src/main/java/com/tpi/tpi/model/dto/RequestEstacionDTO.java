package com.tpi.tpi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RequestEstacionDTO {

    private int estacionId;

    private String nombre;

    private double latitud;

    private double longitud;

}
