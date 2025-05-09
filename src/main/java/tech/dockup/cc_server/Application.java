package tech.dockup.cc_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	//This is the main method that will be run when the application is started
	//It will start the Spring Boot application instanciating everything the application needs like controllers, services, repositories, etc.
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.class.path"));
		SpringApplication.run(Application.class, args);
	}

}
