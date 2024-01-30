package com.tpi.alquiler.alquileres.configuration;


import com.tpi.alquiler.alquileres.model.Alquileres;
import com.tpi.alquiler.alquileres.repositories.AlquileresRepository;
import com.tpi.alquiler.alquileres.repositories.TarifasRepository;
import com.tpi.alquiler.alquileres.services.AlquileresImpl;
import com.tpi.alquiler.alquileres.services.AlquileresService;
import com.tpi.alquiler.alquileres.services.TarifasImpl;
import com.tpi.alquiler.alquileres.services.TarifasService;
import com.tpi.alquiler.alquileres.services.mappers.AlquileresDTOMapper;
import com.tpi.alquiler.alquileres.services.mappers.TarifasDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public TarifasService tarifasService(TarifasRepository tarifasRepository, TarifasDTOMapper tarifasDTOMapper){
        return new TarifasImpl(tarifasRepository, tarifasDTOMapper);
    }

    @Bean
    public AlquileresService alquileresService(AlquileresRepository alquileresRepository, AlquileresDTOMapper alquileresDTOMapper, TarifasRepository tarifasRepository){
        return new AlquileresImpl(alquileresRepository, alquileresDTOMapper, tarifasRepository);
    }

}


