package com.tpi.alquiler.alquileres.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estaciones {
    @Getter
    @Id
    @GeneratedValue(generator = "estaciones")
    @TableGenerator(
            name = "estaciones",
            table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue = "ID",
            initialValue = 19, allocationSize = 1)

    @Column(name = "ID")
    private int estacionId;

    @Column(name = "NOMBRE")
    private String Nombre;


    @Column(name = "FECHA_HORA_CREACION")
    private String fechaHoraCreacion;

    @Column(name = "LATITUD")
    private double latitud;

    @Column(name = "LONGITUD")
    private double longitud;

    public int getEstacionId() {
        return estacionId;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}
