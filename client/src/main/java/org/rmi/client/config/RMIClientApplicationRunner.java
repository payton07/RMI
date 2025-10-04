package org.rmi.client.config;
import org.rmi.client.OtherAnim;
import org.rmi.commons.impl.Animal;
import org.rmi.commons.impl.Species;
import org.rmi.commons.impl.TrackingFile;
import org.rmi.commons.interfaces.IAnimal;
import org.rmi.commons.interfaces.ICabinet;
import org.rmi.commons.interfaces.ISpecies;
import org.rmi.commons.interfaces.ITrackingFile;
import org.springframework.boot.ApplicationRunner;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationArguments;

@Component
public class RMIClientApplicationRunner implements ApplicationRunner {
    private RmiProxyFactoryBean proxy;

    public RMIClientApplicationRunner(RmiProxyFactoryBean proxy) {
        this.proxy = proxy;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // PARTIE 1 :
/*
                // Récupération de l'instance de IAnimal via le proxy :
                IAnimal service = (IAnimal) proxy.getObject();

                // Invocation de la methode getCompleName de la classe Animal : Nom complet de l'animal
                System.out.println(service.getCompletName());

                // Affichage du suivi medical :

                TrackingFile track =  service.getTrackingFile(); // Recuperation du suivi medical par reference
                System.out.println("Suivi medical : "+ track.getFileContent());
                track.setFileContent("Nouveau suivi medical "); // Ici je modifie le dossier de suiv de l'animal
                System.out.println("Suivi medical  apres set: "+ track.getFileContent());

                // FIn suivi medical :

                System.out.println("Suivi medical last: "+ track.getFileContent());

                // Recuperation de l'espece :

                Species species = service.getSpecie(); // Recupere l'espece de l'animal
                System.out.println("L'espece de l'animal : "+service.getCompletName()+" est : "+species.getName()+ " et vie en moyenne "+species.getLifeSpan()+" ans");
*/
        // PARTIE 2 + 3:

        ICabinet service = (ICabinet) proxy.getObject();
        System.out.println("le nom du cabinet : "+ service.getName());

        // Creation de nouveaux patients + Recherche de patients

        ISpecies species1 =  new Species("Chien",12);
        ITrackingFile trackingFile1 = new TrackingFile("La trackingfile est vide");
        IAnimal animal1 = new Animal("Chien","Moi1",species1,"Berger Allemand",trackingFile1);

        ISpecies species2 = new Species("Chat",12);
        ITrackingFile trackingFile2 = new TrackingFile("La trackingfile est vide");
        IAnimal animal2 = new Animal("Chat","Moi2",species2,"Le Bengal",trackingFile2);


        // Ajout de patient :
        service.addAnimal(animal1);
        service.addAnimal(animal2);

        System.out.println("le nom du patient1 creé : "+ animal1.getName());
        System.out.println("le nom du patient2 creé : "+ animal2.getName());

        IAnimal patient1 = service.getAnimal(0);
        IAnimal patient2 = service.recherche("Chien");

        System.out.println("animal1 :"+patient1.toString());
        System.out.println("animal2 :"+patient2.toString());

        // Modifions le dossier d'un animal creé :
        System.out.println("Dossier animal1 avant modif : "+ animal1.getTrackingFile().getFileContent());
        animal1.getTrackingFile().setFileContent("Cet animal est ajour sur ces vaccins");

        // verifions la modification
        System.out.println("Dossier animal1 apres modif: "+ animal1.getTrackingFile().getFileContent());


      // PARTIE 4 : CODEBASE
        ISpecies species3 =  new Species("Hibou",12);
        ITrackingFile trackingFile3 = new TrackingFile("La trackingfile est vide");
        IAnimal animal3 = new OtherAnim("Hibou","Moi3","Oiseau",species3,trackingFile3);

        // Ajout pour la codebase: Pour ça j'ai crée la classe OtherAnim, pas connu par le serveur
        service.addAnimal( animal3);

        IAnimal patient3 = service.recherche("Hibou");
        System.out.println("animal3 :"+patient3.toString());

        // Les Alertes

        for(int i = 0;i<100;i++){
            IAnimal animal4 = new Animal("Chat","Moi2",species2,"Le Bengal",trackingFile2);
            service.addAnimal(animal4);
        }

    }

}
