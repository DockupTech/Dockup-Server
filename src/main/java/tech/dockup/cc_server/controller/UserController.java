package tech.dockup.cc_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.vavr.control.Option;
import io.vavr.control.Try;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import tech.dockup.cc_server.model.User;
import tech.dockup.cc_server.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }
} 