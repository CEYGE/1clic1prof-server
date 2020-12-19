package fr.clic1prof.serverapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerAppApplication {

	public static void main(String[] args) {
		System.out.println("test");
		SpringApplication.run(ServerAppApplication.class, args);
	}
}
