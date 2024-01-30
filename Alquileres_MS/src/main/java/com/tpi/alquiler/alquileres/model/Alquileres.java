package com.tpi.alquiler.alquileres.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alquileres")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alquileres {
    @Id
    @GeneratedValue(generator = "alquileres")
    @TableGenerator(
            name = "alquileres",
            table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue = "ID_ALQUILER",
            initialValue = 1, allocationSize = 1)

    @Column(name = "ID")
    private int idAlquiler;

    @Column(name = "ID_CLIENTE")
    private int idCliente;

    @Column(name = "ESTADO")
    private int estado;

    @Column(name = "ESTACION_RETIRO")
    private int estacionRetiro;

    @Column(name = "ESTACION_DEVOLUCION")
    private int estacionDevolucion;

    @Column(name = "FECHA_HORA_RETIRO")
    private String fechaHoraRetiro;

    @Column(name = "FECHA_HORA_DEVOLUCION")
    private String fechaHoraDevolucion;

    @Column(name = "MONTO")
    private double monto;

    @OneToOne
    @JoinColumn(name = "ID_TARIFA")
    private Tarifas idTarifa;


}
