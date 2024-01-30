package com.tpi.tpi.configuration;

import com.tpi.tpi.repositories.EstacionesRepository;
import com.tpi.tpi.services.EstacionesImpl;
import com.tpi.tpi.services.EstacionesService;
import com.tpi.tpi.services.mappers.EstacionesDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    public EstacionesService estacionesService(EstacionesRepository estacionesRepository, EstacionesDTOMapper estacionesDTOMapper){
        return new EstacionesImpl(estacionesRepository, estacionesDTOMapper);
    }

}
