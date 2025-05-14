package tech.dockup.cc_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.dockup.cc_server.model.User;
import tech.dockup.cc_server.service.DockerService;
import tech.dockup.cc_server.service.UserService;


//This is the controller that will be used to handle the requests
//It is annotated with @RestController, which is a Spring annotation that marks the class as a controller where every method returns a response
//It is also annotated with @RequestMapping, which is a Spring annotation that maps the controller to a specific URL
@RestController
@RequestMapping("/api/users")
public class UserController {

    //The @Autowired annotation is used to inject the UserService bean into the UserController bean
    @Autowired
    private UserService userService;

    @Autowired
    private DockerService dockerService;

    //This is a test endpoint to check if the server is running
    //It is annotated with @GetMapping, which is a Spring annotation that maps the method to the GET method of the URL
    //So the overall URL will be .../api/users/hello (The /api/users is the base URL of the controller and the /hello is the endpoint)
    @GetMapping("/hello")
    //The methods return type is the type of the object that will be converted to a JSON response or a plain String
    public User hello() {

        //Everything returned from the method will be converted to a JSON response except it is a plain String
        //Here we create a new User object using the builder pattern as defined in the User class
        return User.builder().email("John@gmail.com").enabled(true).build();
    }

    //This is a method that creates a new user
    //It is annotated with @PostMapping, which is a Spring annotation that maps the method to the POST method of the URL
    //So the overall URL will be .../api/users/create (The /api/users is the base URL of the controller and the /create is the endpoint)
    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    //This is a method that finds a user by their id
    //It is annotated with @GetMapping, which is a Spring annotation that maps the method to the GET method of the URL
    //So the overall URL will be .../api/users/{id} (The /api/users is the base URL of the controller and the {id} is the id of the user)
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    //This is a method that updates a user
    //It is annotated with @PutMapping, which is a Spring annotation that maps the method to the PUT method of the URL
    //So the overall URL will be .../api/users/{id} (The /api/users is the base URL of the controller and the {id} is the id of the user)
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        user.setId(id);
        return userService.updateUser(user);
    }

    //This is a method that deletes a user
    //It is annotated with @DeleteMapping, which is a Spring annotation that maps the method to the DELETE method of the URL
    //So the overall URL will be .../api/users/{id} (The /api/users is the base URL of the controller and the {id} is the id of the user)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
} 