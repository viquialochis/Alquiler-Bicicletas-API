package com.gw.gw.Configuration;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class GWConfig {

    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder,
                                        @Value("${gw-bicicletas.url-microservicio-alquileres}") String uriAlquileres,
                                        @Value("${gw-bicicletas.url-microservicio-estaciones}") String uriEstaciones) {
        return builder.routes()
                // Ruteo al Microservicio de Alquileres
                .route(p -> p.path("/alquileres/**").uri(uriAlquileres))
                // Ruteo al Microservicio de Estaciones
                .route(p -> p.path("/estaciones/**").uri(uriEstaciones))
                .build();
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {
        http.authorizeExchange(exchanges -> exchanges
                        // Esta ruta puede ser accedida por el cliente y el admin
                        .pathMatchers(HttpMethod.GET, "/estaciones/**")
                        .hasAnyRole("CLIENTE")

                        // Esta ruta puede ser accedida por el admin
                        .pathMatchers(HttpMethod.POST, "/estaciones/**")
                        .hasRole("ADMINISTRADOR")

                        // Esta ruta puede ser accedida por el admin
                        .pathMatchers(HttpMethod.GET, "/alquileres/**")
                        .hasRole("ADMINISTRADOR")

                        // Esta ruta puede ser accedida por el cliente

                        .pathMatchers(HttpMethod.PUT, "/alquileres/**")
                        .hasRole("CLIENTE")

                        // Esta ruta puede ser accedida por el cliente
                        .pathMatchers(HttpMethod.POST, "/alquileres/**")
                        .hasRole("CLIENTE")

                        // Cualquier otra petición será rechazada
                        .anyExchange().denyAll()

                ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .csrf(ServerHttpSecurity.CsrfSpec::disable);

        return http.build();
    }

    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
        var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        // Se especifica el nombre del claim a analizar
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        // Se agrega este prefijo en la conversión por una convención de Spring
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        // Se asocia el conversor de Authorities al Bean que convierte el token JWT a un objeto Authorization
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
                new ReactiveJwtGrantedAuthoritiesConverterAdapter(grantedAuthoritiesConverter));
        // También se puede cambiar el claim que corresponde al nombre que luego se utilizará en el objeto
        // Authorization
        // jwtAuthenticationConverter.setPrincipalClaimName("user_name");

        return jwtAuthenticationConverter;
    }
}