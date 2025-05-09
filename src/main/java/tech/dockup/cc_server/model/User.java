package tech.dockup.cc_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

//The @Data annotation is used to generate getters, setters, equals, hashCode, and toString methods
@Data
//It is annotated with @Entity, which is a Spring annotation that marks the class as an entity. This means that this class will be mapped to a table in the database.
@Entity
@Table(name = "users")
//The @Builder annotation is used to generate a builder for the class (see usage of it in the UserController)
@Builder
//The @NoArgsConstructor annotation is used to generate a constructor with no parameters
@NoArgsConstructor
//The @AllArgsConstructor annotation is used to generate a constructor with all fields as parameters
@AllArgsConstructor
//This is the model class for the User table
//You have to make sure that this class has the same fields as the table in the database.
//It is also annotated with @Table, which is a Spring annotation that maps the class to a specific table in the database
public class User {

    //The @Id annotation is used to mark the field as the primary key of the table
    @Id
    //The @GeneratedValue annotation is used to generate a value for the field
    //The strategy is set to UUID, which means that the value will be a UUID. This means that when creating a new user, the id will be generated automatically
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String username;
    private String displayName;
    private String password;
} 