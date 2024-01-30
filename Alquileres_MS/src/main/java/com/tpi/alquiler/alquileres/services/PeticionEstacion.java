package com.tpi.alquiler.alquileres.services;

import ch.qos.logback.classic.Logger;
import com.tpi.alquiler.alquileres.model.Estaciones;
import com.tpi.alquiler.alquileres.model.Resultado;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;

public class PeticionEstacion {
    private static Logger log;

    public PeticionEstacion() {
        this.log = (Logger) LoggerFactory.getLogger(getClass());
    }


    public static Estaciones getEstacion(Integer id) {
        // Creación de una instancia de RestTemplate
        try {
            // Creación de la instancia de RequestTemplate
            RestTemplate template = new RestTemplate();
            // Se realiza una petición a http://localhost:8081/estaciones/{id}la
            // respuesta de la petición tendrá en su cuerpo a un objeto del tipo Estacion.
                    ResponseEntity<Estaciones> res = template.getForEntity(
                    "http://localhost:8081/estaciones/{id}", Estaciones.class, id //agregar el el porperties y aca llamo a la variable
            );
            // Se comprueba si el código de repuesta es de la familia 200
            if (res.getStatusCode().is2xxSuccessful()) {
                return res.getBody();
            } else {
                log.warn("Respuesta no exitosa: {}", res.getStatusCode());
            }

        } catch (HttpClientErrorException ex) {
            // La repuesta no es exitosa.
            log.error("Error en la petición", ex);
        }
        return null;
    }

    public Resultado crearConversion(String moneda, double importe) {
        try {
            // Creación de la instancia de RestTemplate
            RestTemplate template = new RestTemplate();

            // Creación de la URL del endpoint
            String uri = "http://34.82.105.125:8080/convertir";

            // Creación del objeto de solicitud que contiene la moneda y el importe
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("moneda_destino", moneda);
            requestBody.put("importe", importe);

            // Realización de la solicitud POST y obtención de la respuesta
            ResponseEntity<Resultado> response = template.postForEntity(uri, requestBody, Resultado.class);

            // Se obtiene el cuerpo de la respuesta
            Resultado resultado = response.getBody();

            // Se comprueba si el código de respuesta es de la familia 2xx (éxito)
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Resultado: " + resultado);
                return resultado;
            } else {
                // Manejar errores si la respuesta no es exitosa
                log.error("La petición no fue exitosa. Código de respuesta: " + response.getStatusCodeValue());
            }
        } catch (HttpClientErrorException ex) {
            // Manejar errores de cliente (por ejemplo, 4xx)
            log.error("Error en la petición", ex);
        } catch (HttpServerErrorException ex) {
            // Manejar errores de servidor (por ejemplo, 5xx)
            log.error("Error en el servidor", ex);
        } catch (Exception ex) {
            // Manejar otros errores no esperados
            log.error("Error inesperado", ex);
        }

        // En caso de error, o si la respuesta no es exitosa, devolver null o manejar de alguna manera
        return null;
    }

}
