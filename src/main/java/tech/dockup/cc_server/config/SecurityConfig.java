package tech.dockup.cc_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//This is the security configuration class for the application2
//It is annotated with @Configuration, which is a Spring annotation that marks the class as a configuration class
//It is also annotated with @EnableWebSecurity, which is a Spring annotation that enables web security
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //This is a bean that creates a security filter chain
    //The securityFilterChain method is called when the application is started
    //It is used to configure the security filter chain
    //More info about the securityFilterChain method: https://docs.spring.io/spring-security/reference/servlet/configuration/java.html#servlet-configuration-java-securityfilterchain
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //This is a method that disables the CSRF protection
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                //This is a method that permits all requests to the /api/users/hello endpoint
                .requestMatchers("/api/users/hello").permitAll()
                .requestMatchers("/api/users/").permitAll()
                .requestMatchers("/api/users/{id}").permitAll()
                //every other request has to be authenticated
                .anyRequest().authenticated()
            );
        
        return http.build();
    }
} 