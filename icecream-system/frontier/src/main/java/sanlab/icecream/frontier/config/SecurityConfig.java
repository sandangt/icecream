package sanlab.icecream.frontier.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import sanlab.icecream.frontier.service.security.JwtAuthConverter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import sanlab.icecream.frontier.service.security.StorefrontAuthorizationManager;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;
    private final StorefrontAuthorizationManager authorizationManager;

//    private static final String[] ANON_URL_PATTERNS = {
//        "/swagger-ui/**", "/swagger-resources", "/swagger-resources/**",
//        "/actuator/**", "/graphql"
//    };

//    @Bean
//    public WebSecurityCustomizer anonUrl() {
//        return web -> web.ignoring().requestMatchers(ANON_URL_PATTERNS);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .cors(withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .oauth2ResourceServer(auth -> auth.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)))
            .authorizeHttpRequests(req -> req.anyRequest().permitAll())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(
        @Value("${app.cors.allowed-origins}") List<String> allowedOrigins) {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(allowedOrigins);
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
