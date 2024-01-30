package com.tpi.tpi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estaciones {
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


    public  double calcularDistancia(double latitud1, double longitud1){
       double latitud0 = latitud * 110000;
       double longitud0 = longitud * 110000;
       longitud1 *= 110000;
       latitud1 *= 110000;
       return Math.sqrt(Math.pow((longitud0 - longitud1),2)+Math.pow((latitud0-latitud1),2));


    }

}
