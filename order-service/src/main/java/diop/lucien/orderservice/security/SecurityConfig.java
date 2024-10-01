package diop.lucien.orderservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration @EnableWebSecurity @EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private JwtAuthConverter jwtAuthConverter;

    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf->csrf.disable())
                .headers(h->h.frameOptions(fo-> fo.disable()))
                .authorizeHttpRequests(ar-> ar.requestMatchers("/h2-console/**", "/api/orders/**", "/swagger-ui.html", "/v3/**", "/swagger-ui/**").permitAll())
                // .authorizeHttpRequests(ar-> ar.requestMatchers("/api/products/**").hasAuthority("ADMIN"))
                .authorizeHttpRequests(ar-> ar.anyRequest().authenticated())
                .oauth2ResourceServer(o2->o2.jwt(jwt-> jwt.jwtAuthenticationConverter(jwtAuthConverter)))
                .build();
    }
   /* @Bean
    CorsConfigurationSource corsConfiguration () {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Spécifiez les origines autorisées
        configuration.setAllowedOrigins(List.of("*"));
        // Spécifiez les méthodes autorisées
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        // Spécifiez les en-têtes autorisés
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        // Spécifiez les en-têtes à exposer
        configuration.setExposedHeaders(List.of("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}