package org.rmi.server.config;
import org.rmi.commons.interfaces.ICabinet;
import java.rmi.Naming;
import java.util.logging.Logger;
import org.rmi.server.impl.Cabinet;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Component
public class RmiServerApplicationRunner implements ApplicationRunner {
    // private Animal animal;
    private Logger logger = Logger.getLogger(this.getClass().getName());


    private ICabinet cabinet;

    public RmiServerApplicationRunner(Cabinet cabinet) {
        // this.animal = animal;
         this.cabinet = cabinet;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (String serviceName : Naming.list("rmi://localhost:1099")) {
            System.out.println("Service RMI enregistré : " + serviceName);
        }
    }

    @Bean
    RmiServiceExporter CabinetExporter() {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName(ICabinet.class.getSimpleName()); // Nom du service
        exporter.setServiceInterface(ICabinet.class); // Interface du service
        exporter.setService(cabinet); // Implémentation du service
        exporter.setRegistryPort(1099); // Port pour le registre RMI
        logger.info("Serveur RMI démarré sur le port 1099");// Journalisation du démarrage du serveur
        return exporter; // Retourne l'exportateur de service RMI
    }
}
