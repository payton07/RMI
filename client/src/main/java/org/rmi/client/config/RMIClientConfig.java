package org.rmi.client.config;
import org.rmi.commons.interfaces.ICabinet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import java.util.logging.Logger;

@Configuration
public class RMIClientConfig {
    public final Logger logger = Logger.getLogger(this.getClass().getName());

    @Bean
    RmiProxyFactoryBean proxyFactoryBean() {
        String rmiServerURL = String.format("rmi://localhost:1099/%s",
                // IAnimal.class.getSimpleName()); // URL du serveur RMI
                 ICabinet.class.getSimpleName()); // URL du serveur RMI
        logger.info("URL du serveur RMI : " + rmiServerURL); // Journalisation de l'URL du serveur service
        RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();
        // proxy.setServiceInterface(IAnimal.class); // Définir l'interface du
        proxy.setServiceInterface(ICabinet.class);
        proxy.setServiceUrl(rmiServerURL); // Définir l'URL du service
        proxy.afterPropertiesSet(); // Initialiser le proxy
        return proxy; // Retourner le proxy
    }

}
