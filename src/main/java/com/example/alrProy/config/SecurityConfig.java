package com.example.alrProy.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers(
                headersConfigurer -> headersConfigurer
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.authorizeHttpRequests(auth -> auth
                // Como las reglas de seguridad se evaluan por orden,
                // dejamos las reglas más restrictivas al principio para evitar accesos a mappings que no deben
                .requestMatchers("/usuarios/**", "/compras", "/compras/nuevo/**", "/compras/borrar/**").hasRole("ADMIN")
                .requestMatchers("/productos/nuevo/**", "/productos/borrar/**", "/productos/editar/**", "/categorias/nuevo/**", "/categorias/borrar/**").hasRole("ADMIN")
                .requestMatchers("/detallesCompra/**", "/productos/comprar/**", "/compras/confirmar/**").hasAnyRole("ADMIN", "USUARIO")
                .requestMatchers("/home", "/aboutUs" , "/productos/**", "/categorias").permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // para rutas: /css, /js /images
                .anyRequest().permitAll())
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/signin") // mapping para mostrar formulario de login
                        .loginProcessingUrl("/login") // ruta post de '/signin', no hace falta tratarla en el controlador
                        .failureUrl("/signin")
                        .defaultSuccessUrl("/home", true).permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/home")
                        .permitAll())
                // .csrf(csrf -> csrf.disable())
                .rememberMe(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults()); //org.springframework.security.config.Customizer
        http.exceptionHandling(exceptions -> exceptions.accessDeniedPage("/accessError"));
        return http.build();
    }
}
