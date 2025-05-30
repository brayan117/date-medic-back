package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Permitir solicitudes desde cualquier origen (en producción, especifica los orígenes permitidos)
        config.addAllowedOrigin("*");
        
        // Permitir todos los métodos (GET, POST, PUT, DELETE, etc.)
        config.addAllowedMethod("*");
        
        // Permitir todos los encabezados
        config.addAllowedHeader("*");
        
        // Permitir credenciales (si es necesario para tu aplicación)
        // config.setAllowCredentials(true);
        
        // Configurar el tiempo máximo que los resultados de una solicitud previa pueden almacenarse en caché
        config.setMaxAge(3600L);
        
        // Aplicar esta configuración a todas las rutas
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}
