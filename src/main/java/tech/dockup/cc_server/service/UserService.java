package tech.dockup.cc_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.dockup.cc_server.dao.UserDAO;
import tech.dockup.cc_server.model.User;

//This is the service class for the User table
//It is annotated with @Service, which is a Spring annotation that marks the class as a service
//A service is a class that contains the business logic of the application
@Service
public class UserService {

  //The @Autowired annotation is used to inject the UserDAO bean into the UserService bean
  @Autowired
  private UserDAO userDAO;

  //This is a method that creates a new user
  public User createUser(User user) {
    return userDAO.save(user);
  }

  //This is a method that updates a user
  public User updateUser(User user) {
    return userDAO.save(user);
  }

  //This is a method that deletes a user  
  public void deleteUser(String id) {
    userDAO.deleteById(id);
  }

  //This is a method that finds a user by their id
  public User findById(String id) {
    return userDAO.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
  }

  //This is a method that finds a user by their username
  public User findByUsername(String username) {
    return userDAO.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
  }
}