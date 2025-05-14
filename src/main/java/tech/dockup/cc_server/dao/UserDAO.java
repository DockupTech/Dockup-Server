package tech.dockup.cc_server.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.dockup.cc_server.model.User;

//This is the interface (Data Access Object, short DAO) that will be used to access the User table in the database
//It extends JpaRepository, which is a Spring Data JPA interface for generic CRUD operations on a repository for a specific type
//The type is User and the id type is String
//You can find the documentation of JpaRepository here: https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
public interface UserDAO extends JpaRepository<User, String> {
  //These functions will be generated automagically by Spring Data JPA
  //It is often as easy as just defining the function name and return type and it will be implemented automatically
  //Make sure to read the documentation of Java regarding the Optional class: https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
  Optional<User> findByEmail(String email);
  boolean existsByEmail(String email);
} 