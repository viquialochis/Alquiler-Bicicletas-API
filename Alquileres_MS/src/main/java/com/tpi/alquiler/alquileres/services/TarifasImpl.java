package com.tpi.alquiler.alquileres.services;

import com.tpi.alquiler.alquileres.model.Tarifas;
import com.tpi.alquiler.alquileres.model.dto.ResponseTarifasDTO;
import com.tpi.alquiler.alquileres.model.dto.RequestTarifasDTO;
import com.tpi.alquiler.alquileres.repositories.TarifasRepository;
import com.tpi.alquiler.alquileres.services.mappers.TarifasDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class TarifasImpl implements TarifasService {
    private final TarifasRepository tarifasRepository;
    private final TarifasDTOMapper tarifasDTOMapper;

    public TarifasImpl(TarifasRepository tarifasRepository, TarifasDTOMapper tarifasDTOMapper) {
        this.tarifasRepository = tarifasRepository;
        this.tarifasDTOMapper = tarifasDTOMapper;
    }


    @Override
    public ResponseTarifasDTO add(RequestTarifasDTO tarifaDTO) {
        Tarifas tarifa = new Tarifas();
        tarifa.setTipoTarifa(tarifaDTO.getTipoTarifa());
        tarifa.setMes(tarifaDTO.getMes());
        tarifa.setAnio(tarifaDTO.getAnio());
        tarifa.setDefinicion(tarifaDTO.getDefinicion());
        tarifa.setDiaMes(tarifaDTO.getDiaMes());
        tarifa.setDiaSemana(tarifaDTO.getDiaSemana());
        tarifa.setMontoFijoAlquiler(tarifaDTO.getMontoFijoAlquiler());
        tarifa.setMontoHora(tarifaDTO.getMontoHora());
        tarifa.setMontoKm(tarifaDTO.getMontoKm());
        tarifa.setMontoMinutoFraccion(tarifaDTO.getMontoMinutoFraccion());

        tarifasRepository.save(tarifa);
        return  tarifasDTOMapper.apply(tarifa);
    }

    @Override
    public ResponseTarifasDTO update(RequestTarifasDTO tarifaDTO) {
        Tarifas tarifaToUpdate = tarifasRepository.findById(tarifaDTO.getIdTarifa())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada con el id: " + tarifaDTO.getIdTarifa()));

        tarifaToUpdate.setIdTarifa(tarifaDTO.getIdTarifa());
        tarifaToUpdate.setTipoTarifa(tarifaDTO.getTipoTarifa());
        tarifaToUpdate.setMes(tarifaDTO.getMes());
        tarifaToUpdate.setAnio(tarifaDTO.getAnio());
        tarifaToUpdate.setDefinicion(tarifaDTO.getDefinicion());
        tarifaToUpdate.setDiaMes(tarifaDTO.getDiaMes());
        tarifaToUpdate.setDiaSemana(tarifaDTO.getDiaSemana());
        tarifaToUpdate.setMontoFijoAlquiler(tarifaDTO.getMontoFijoAlquiler());
        tarifaToUpdate.setMontoHora(tarifaDTO.getMontoHora());
        tarifaToUpdate.setMontoKm(tarifaDTO.getMontoKm());
        tarifaToUpdate.setMontoMinutoFraccion(tarifaDTO.getMontoMinutoFraccion());

        tarifasRepository.save(tarifaToUpdate);
        return  tarifasDTOMapper.apply(tarifaToUpdate);
    }

    @Override
    public ResponseTarifasDTO delete(Integer id) {
        Tarifas tarifasToDelete= tarifasRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada con el id: " + id));

        tarifasRepository.delete(tarifasToDelete);
        return  tarifasDTOMapper.apply(tarifasToDelete);
    }

    @Override
    public ResponseTarifasDTO getById(Integer id) {
        Tarifas tarifaFounded= tarifasRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada con el id: " + id));
        return tarifasDTOMapper.apply(tarifaFounded);
    }

    @Override
    public List<ResponseTarifasDTO> getAll() {
        List<Tarifas> tarifas = tarifasRepository.findAll();

        return  tarifas.stream()
                .map(tarifasDTOMapper) // Aplica el mapeo a cada elemento
                .toList(); // Convierte el flujo en una lista
    }
}
