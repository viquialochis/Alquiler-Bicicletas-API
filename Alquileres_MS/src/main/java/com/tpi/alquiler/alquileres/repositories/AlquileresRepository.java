package com.tpi.alquiler.alquileres.repositories;

import com.tpi.alquiler.alquileres.model.Alquileres;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlquileresRepository extends JpaRepository<Alquileres, Integer> {
}
