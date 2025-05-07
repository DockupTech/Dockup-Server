package tech.dockup.cc_server.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import io.vavr.control.Option;
import tech.dockup.cc_server.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
  Option<User> findByUsername(String username);
  boolean existsByUsername(String username);
} 