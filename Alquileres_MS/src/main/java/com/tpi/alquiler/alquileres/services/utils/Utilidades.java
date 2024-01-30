package com.tpi.alquiler.alquileres.services.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utilidades {
    public  double calcularDistancia(double latitud1, double longitud1, double latitud2, double longitud2){
        latitud2 *= 110000;
        longitud2 *= 110000;
        longitud1 *= 110000;
        latitud1 *= 110000;
        return Math.sqrt(Math.pow((longitud2 - longitud1),2)+Math.pow((latitud2-latitud1),2));

    }




}
