package com.tpi.alquiler.alquileres.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarifas")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Tarifas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int idTarifa;

    @Column(name = "TIPO_TARIFA")
    private int tipoTarifa;

    @Column(name = "DEFINICION")
    private char definicion;

    @Column(name = "DIA_SEMANA")
    private Integer diaSemana;

    @Column(name = "DIA_MES")
    private Integer diaMes;

    @Column(name = "MES")
    private Integer mes;

    @Column(name = "ANIO")
    private Integer anio;

    @Column(name = "MONTO_FIJO_ALQUILER")
    private double montoFijoAlquiler;

    @Column(name = "MONTO_MINUTO_FRACCION")
    private double montoMinutoFraccion;

    @Column(name = "MONTO_HORA")
    private double montoHora;

    @Column(name = "MONTO_KM")
    private double montoKm;
}
