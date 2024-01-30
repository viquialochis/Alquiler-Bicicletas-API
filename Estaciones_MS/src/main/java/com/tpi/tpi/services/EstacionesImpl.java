package com.tpi.tpi.services;

import com.tpi.tpi.model.Estaciones;
import com.tpi.tpi.model.dto.RequestEstacionDTO;
import com.tpi.tpi.model.dto.ResponseEstacionDTO;
import com.tpi.tpi.repositories.EstacionesRepository;
import com.tpi.tpi.services.mappers.EstacionesDTOMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

public class EstacionesImpl implements EstacionesService {


    private final EstacionesRepository estacionesRepository;
    private final EstacionesDTOMapper estacionesDTOMapper;

    public EstacionesImpl(EstacionesRepository estacionesRepository, EstacionesDTOMapper estacionesDTOMapper) {
        this.estacionesRepository = estacionesRepository;
        this.estacionesDTOMapper = estacionesDTOMapper;
    }

    @Override
    public ResponseEstacionDTO add(RequestEstacionDTO estacionDTO) {
        Estaciones estacionesToAdd = new Estaciones();

        estacionesToAdd.setNombre(estacionDTO.getNombre());
        estacionesToAdd.setFechaHoraCreacion(LocalDateTime.now().toString());
        estacionesToAdd.setLatitud(estacionDTO.getLatitud());
        estacionesToAdd.setLongitud(estacionDTO.getLongitud());

        estacionesRepository.save(estacionesToAdd);

        return estacionesDTOMapper.apply(estacionesToAdd);
    }

    @Override
    public ResponseEstacionDTO update(RequestEstacionDTO estacionDTO) {

        Estaciones estacionesToUpdate = estacionesRepository.findById(estacionDTO.getEstacionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Estación no encontrada con el ID: " + estacionDTO.getEstacionId()));

        estacionesToUpdate.setNombre(estacionDTO.getNombre());
        estacionesToUpdate.setLongitud(estacionDTO.getLongitud());
        estacionesToUpdate.setLatitud(estacionDTO.getLatitud());

        estacionesRepository.save(estacionesToUpdate);

        return estacionesDTOMapper.apply(estacionesToUpdate);

    }

    @Override
    public ResponseEstacionDTO delete(Integer id) {
        Estaciones estacionesToDelete = estacionesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Estación no encontrada con el ID: " + id));

        estacionesRepository.delete(estacionesToDelete);

        return estacionesDTOMapper.apply(estacionesToDelete);
    }

    @Override
    public ResponseEstacionDTO getById(Integer id) {
        Estaciones estacionesToLook = estacionesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Estación no encontrada con el ID: " + id));
        return estacionesDTOMapper.apply(estacionesToLook);
    }

    @Override
    public List<ResponseEstacionDTO> getAll() {
        List<Estaciones> estacionesToLook = estacionesRepository.findAll();

        return estacionesToLook.stream()
                .map(estacionesDTOMapper) // Aplica el mapeo a cada elemento
                .toList(); // Convierte el flujo en una lista

    }

    public ResponseEstacionDTO getEstacionMasCercana(Double latitud, Double longitud) {
        List<Estaciones> todasLasEstaciones = estacionesRepository.findAll();

        Estaciones estacionesMasCercana = null;
        double distanciaMinima = Double.MAX_VALUE;

        for (Estaciones estaciones : todasLasEstaciones) {
            double distancia = estaciones.calcularDistancia(latitud, longitud);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                estacionesMasCercana = estaciones;
            }
        }

        if (estacionesMasCercana != null) {
            return estacionesDTOMapper.apply(estacionesMasCercana);
        } else {
            throw new EntityNotFoundException("No se encontró ninguna estación cercana a la" +
                    " ubicación proporcionada.");
        }
    }


}
