package org.rmi.client.main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Paths;
import java.security.AccessControlException;
import java.security.AccessController;

@SpringBootApplication(scanBasePackages = {"org.rmi.client.impl", // Package pour l'implémentation du service
		"org.rmi.client.config" // Package pour la configuration
})
public class ClientApplication {
	public static void main(String[] args) {
		String res = Paths.get("").toFile().getAbsolutePath().toString();
		res = res+"/target/classes/client.policy";
		System.setProperty("java.security.policy",res);
		if(System.getSecurityManager()==null){
			System.setSecurityManager(new SecurityManager());
		}
		System.out.println("Security Manager: " + System.getSecurityManager());

		System.out.println("La codebase : "+System.getProperty("java.rmi.server.codebase"));
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

		SpringApplication.run(ClientApplication.class, args);
	}

}
