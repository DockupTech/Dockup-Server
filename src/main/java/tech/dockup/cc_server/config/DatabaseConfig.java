package tech.dockup.cc_server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

//This is the configuration class for the database
//It is annotated with @Configuration, which is a Spring annotation that marks the class as a configuration class
//It is also annotated with @ConfigurationProperties, which is a Spring annotation that binds the properties to the class
@Data
@Configuration
//The prefix is the prefix of the properties in the application.properties file
//The properties are:
//- path: the path to the database file
@ConfigurationProperties(prefix = "dockup.database")
//The class has a private field path of type String
public class DatabaseConfig {
    //The path field is annotated with @Value, which is a Spring annotation that binds the property to the field
    private String path;
} 