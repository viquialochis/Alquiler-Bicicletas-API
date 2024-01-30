package com.tpi.alquiler.alquileres.services;
import com.google.common.base.Objects;
import com.tpi.alquiler.alquileres.model.Alquileres;
import com.tpi.alquiler.alquileres.model.Estaciones;
import com.tpi.alquiler.alquileres.model.Resultado;
import com.tpi.alquiler.alquileres.model.Tarifas;
import com.tpi.alquiler.alquileres.model.dto.RequestUpdateAlquileresDTO;
import com.tpi.alquiler.alquileres.model.dto.ResponseAlquileresDTO;

import com.tpi.alquiler.alquileres.model.dto.RequestAlquileresDTO;
import com.tpi.alquiler.alquileres.repositories.AlquileresRepository;
import com.tpi.alquiler.alquileres.repositories.TarifasRepository;
import com.tpi.alquiler.alquileres.services.mappers.AlquileresDTOMapper;
import com.tpi.alquiler.alquileres.services.utils.Utilidades;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class AlquileresImpl implements AlquileresService {

    private final AlquileresRepository alquileresRepository;
    private final AlquileresDTOMapper alquileresDTOMapper;
    private final TarifasRepository tarifasRepository;

    public AlquileresImpl(AlquileresRepository alquileresRepository, AlquileresDTOMapper alquileresDTOMapper, TarifasRepository tarifasRepository) {
        this.alquileresRepository = alquileresRepository;
        this.alquileresDTOMapper = alquileresDTOMapper;
        this.tarifasRepository = tarifasRepository;
    }

    @Override
    public ResponseAlquileresDTO add(RequestAlquileresDTO alquilerDTO) {
        Alquileres alquiler = new Alquileres();

        alquiler.setIdCliente(alquilerDTO.getIdCliente());
        alquiler.setEstado(1);
        alquiler.setFechaHoraRetiro(LocalDateTime.now().toString());

        //seteo de la tarifa
        Tarifas tarifaFounded = tarifasRepository.findById(alquilerDTO.getIdTarifa())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada con el id: " + alquilerDTO.getIdTarifa()));

        alquiler.setIdTarifa(tarifaFounded);

        //seteo de la estacion de retiro
        PeticionEstacion peticion = new PeticionEstacion();
        Estaciones estacionRetiro = peticion.getEstacion(alquilerDTO.getEstacionRetiro());

        if (estacionRetiro == null) {
            // La estación de retiro no existe, lanzar un Bad Request
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La estación de retiro no existe.");
        }

        alquiler.setEstacionRetiro(estacionRetiro.getEstacionId());

        alquileresRepository.save(alquiler);
        return  alquileresDTOMapper.apply(alquiler);
    }

    @Override
    public ResponseAlquileresDTO update(RequestAlquileresDTO entity) {
        return null;
    }

    @Override
    public ResponseAlquileresDTO updateAlquiler(RequestUpdateAlquileresDTO alquilerDTO) {
        Alquileres alquilerToUpdate = alquileresRepository.findById(alquilerDTO.getIdAlquiler())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alquiler no encontrado con el id: " + alquilerDTO.getIdAlquiler()));

        alquilerToUpdate.setIdCliente(alquilerDTO.getIdCliente());
        alquilerToUpdate.setMonto(alquilerDTO.getMonto());
        //seteo de la tarifa
        Tarifas tarifaFounded = tarifasRepository.findById(alquilerDTO.getIdTarifa())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada con el id: " + alquilerDTO.getIdTarifa()));

        alquilerToUpdate.setIdTarifa(tarifaFounded);

        //seteo de estaciones
        PeticionEstacion peticion = new PeticionEstacion();
        Estaciones estacionRetiro = peticion.getEstacion(alquilerDTO.getEstacionRetiro());
        Estaciones estacionDevolucion = peticion.getEstacion(alquilerDTO.getEstacionDevolucion());

        if (estacionRetiro == null || estacionDevolucion==null) {
            // La estación de retiro no existe, lanzar un Bad Request
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La estación no existe.");
        }

        alquilerToUpdate.setEstacionRetiro(estacionRetiro.getEstacionId());
        alquilerToUpdate.setEstacionDevolucion(estacionDevolucion.getEstacionId());


        alquileresRepository.save(alquilerToUpdate);
        return  alquileresDTOMapper.apply(alquilerToUpdate);
    }

    @Override
    public ResponseAlquileresDTO delete(Integer id) {
        Alquileres alquileresToDelete= alquileresRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alquileres no encontrado con el id: " + id));

        alquileresRepository.delete(alquileresToDelete);

        return  alquileresDTOMapper.apply(alquileresToDelete);
    }

    @Override
    public ResponseAlquileresDTO getById(Integer id) {
        Alquileres alquilerFounded= alquileresRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estación no encontrada con el ID: " + id));
        return  alquileresDTOMapper.apply(alquilerFounded);
    }

    @Override
    public List<ResponseAlquileresDTO> getAll() {
        List<Alquileres> alquileresFounded = alquileresRepository.findAll();

        return  alquileresFounded.stream()
                .map(alquileresDTOMapper) // Aplica el mapeo a cada elemento
                .toList(); // Convierte el flujo en una lista
    }

    @Override
    public List<ResponseAlquileresDTO> alquileresEnCurso(){
        List<Alquileres> todosAlquileres = alquileresRepository.findAll();
        List<Alquileres> alquileresCurso = todosAlquileres.stream()
                    .filter(c -> Objects.equal(c.getEstado(), 1))
                    .toList(); // Convierte el flujo en una lista

        return alquileresCurso.stream()
                .map(alquileresDTOMapper)
                .toList();
    }


    @Transactional
    @Override
    public ResponseAlquileresDTO finalizarAlquiler(String moneda, Integer id, Integer estacionDevId){

        Alquileres alquilerSeleccionado= alquileresRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estación no encontrada con el ID: " + id));


        PeticionEstacion peticion = new PeticionEstacion();


    alquilerSeleccionado.setFechaHoraDevolucion(LocalDateTime.now().toString());
    Estaciones estacionDevolucion = peticion.getEstacion(estacionDevId);
    Estaciones estacionRetiro = peticion.getEstacion(alquilerSeleccionado.getEstacionRetiro());
        if (estacionDevolucion == null) {
            // La estación de devolucion no existe, lanzar un Bad Request
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La estación de retiro no existe.");
        }

    Utilidades utilidad = new Utilidades();
    double distancia_KM = (utilidad.calcularDistancia(estacionDevolucion.getLatitud(),estacionDevolucion.getLongitud(),estacionRetiro.getLatitud(),estacionRetiro.getLongitud()))/1000;
    Tarifas tarifa = alquilerSeleccionado.getIdTarifa();


        // Definir el formato que tiene la cadena
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

        // Convertir la cadena a LocalDateTime
        LocalDateTime fechaRetiroComoLocalDateTime = LocalDateTime.parse(alquilerSeleccionado.getFechaHoraRetiro(), formatter);
        LocalDateTime fechaDevolucionComoLocalDateTime = LocalDateTime.parse(alquilerSeleccionado.getFechaHoraDevolucion(), formatter);

        // Restar las fechas de devolucion y de retiro para sacar el tiempo de alquiler
        Duration duration = Duration.between(fechaRetiroComoLocalDateTime, fechaDevolucionComoLocalDateTime);

        // Obtener la diferencia en minutos
        long minutos = duration.toMinutes();

        double monto_tiempo = 0;
        if (minutos <= 31){
            monto_tiempo = minutos*tarifa.getMontoMinutoFraccion();
        }else if (minutos > 31 && minutos <= 60){
            monto_tiempo = 1*tarifa.getMontoHora();
        }else{
            monto_tiempo = minutos/60 * tarifa.getMontoHora();
        }

    double monto = tarifa.getMontoFijoAlquiler() + tarifa.getMontoKm() * distancia_KM + monto_tiempo ;

        try {
            moneda = validarMoneda(moneda);
            if (moneda.equals("ARS")) {
                // No necesitas llamar a la API si la moneda es ARS
                alquilerSeleccionado.setMonto(monto);
            } else {
                // Llamar a la API para convertir la moneda
                Resultado res = peticion.crearConversion(moneda, monto);
                monto = res.getImporte();
                alquilerSeleccionado.setMonto(monto);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        alquilerSeleccionado.setEstado(2);
        alquilerSeleccionado.setEstacionDevolucion(estacionDevId);

        alquileresRepository.save(alquilerSeleccionado);

        return alquileresDTOMapper.apply(alquilerSeleccionado);
    }


    private static String validarMoneda(String moneda) {
        if (moneda == null) {
            return "ARS";
        }
        List<String> monedasAceptadas = Arrays.asList("EUR", "CLP", "BRL", "COP", "PEN", "GBP", "ARS");

        if (!monedasAceptadas.contains(moneda)) {
            throw new IllegalArgumentException("Moneda no válida");
        }
        return moneda;
    }



}
