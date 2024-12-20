package sanlab.icecream.frontier.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import sanlab.icecream.frontier.filter.CrossOriginResourceFilter;
import sanlab.icecream.frontier.service.security.JwtAuthConverter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import sanlab.icecream.frontier.service.security.StorefrontAuthorizationManager;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;
    private final StorefrontAuthorizationManager authorizationManager;
    private final CrossOriginResourceFilter corsFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .oauth2ResourceServer(auth -> auth.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)))
//            .authorizeHttpRequests(req -> req.anyRequest().access(authorizationManager))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(corsFilter, RequestHeaderAuthenticationFilter.class)
            .build();
    }

}
