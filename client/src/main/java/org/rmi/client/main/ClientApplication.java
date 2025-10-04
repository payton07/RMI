package org.rmi.client.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

// Classe principale de l'application
@SpringBootApplication(scanBasePackages = {
		"org.rmi.client.config" // Package pour la configuration du client
})
public class ClientApplication {
	public static void main(String[] args) {
		if (System.getSecurityManager() != null) {
			System.setSecurityManager(new SecurityManager());
		}

		String cp = System.getProperty("java.class.path");
		Path path = Paths.get(cp.split(File.pathSeparator)[0]).toAbsolutePath();
		String codebasePath = path.toUri().toString();
		System.setProperty("java.rmi.server.codebase", codebasePath);
		System.out.println("Codebase dynamique = " + codebasePath);

		SpringApplication.run(ClientApplication.class, args); // Démarre l'application Spring Boot
	}
}