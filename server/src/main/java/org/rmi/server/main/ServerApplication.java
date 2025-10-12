package org.rmi.server.main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Paths;
import java.security.AccessControlException;
import java.security.AccessController;

// Classe principale de l'application
@SpringBootApplication(scanBasePackages = {"org.rmi.server.impl", // Package pour l'implémentation du service
		"org.rmi.server.config" // Package pour la configuration
})
public class ServerApplication {

	public static void main(String[] args) {
		String res = Paths.get("").toFile().getAbsolutePath().toString();

		String codebase = "file:///"+res.split("server")[0]+"codebase/target/classes/";
		System.setProperty("java.rmi.server.codebase", codebase);

		String policy  = res+"/target/classes/server.policy";
		System.setProperty("java.security.policy",policy);

		if(System.getSecurityManager()==null){
			System.setSecurityManager(new SecurityManager());
		}
		System.out.println("Security Manager: " + System.getSecurityManager());

		try {
			AccessController.checkPermission(
					new javax.management.MBeanServerPermission("findMBeanServer")
			);
			System.out.println("Permission JMX granted!");
			AccessController.checkPermission(
					new RuntimePermission("accessClassInPackage.com.sun.jmx.mbeanserver")
			);
			System.out.println("Permission Runtime pour JMX granted!");
			AccessController.checkPermission(
					new RuntimePermission("createMBeanServer")
			);
			System.out.println("Permission createMBeanServer granted!");
		} catch (AccessControlException e) {
			System.out.println("Permission JMX denied!");
		}
		System.out.println("La codebase : "+System.getProperty("java.rmi.server.codebase"));
		SpringApplication.run(ServerApplication.class, args);
	}

}
