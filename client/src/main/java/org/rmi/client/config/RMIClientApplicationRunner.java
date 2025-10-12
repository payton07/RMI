package org.rmi.client.config;
import org.rmi.client.impl.AnimalSpecial;
import org.rmi.commons.impl.Veterinaire;
import org.rmi.commons.interfaces.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationArguments;

import java.util.Scanner;

@Component
public class RMIClientApplicationRunner implements ApplicationRunner {
    private RmiProxyFactoryBean proxy;

    public RMIClientApplicationRunner(RmiProxyFactoryBean proxy) {
        this.proxy = proxy;
    }

    private String enregisterAnimal(Scanner sc,ICabinet service,int opt) throws Exception {
        System.out.println("\t Veuillez entrer le nom de l'animal  \n");
        String nom = sc.next();
        System.out.println("\t Veuillez entrer le nom de son maitre \n");
        String maitre = sc.next();
        System.out.println("\t Veuillez entrer sa race \n");
        String race = sc.next();
        System.out.println("\t Veuillez entrer son  espece \n");
        String espece = sc.next();
        System.out.println("\t Veuillez entrer le temps de vie moyen de cette espece \n");
        int temps = sc.nextInt();
        String response = "";
        if(opt==0){
            response = service.addAnimal(nom,maitre,race,espece,temps);
        }
        else if(opt==1){
            IAnimal animalSpe = new AnimalSpecial(nom,maitre,race,espece,temps);
            response = service.addAnimalForCodeBase(animalSpe);
        }
        return response;
    }

    private String supprimerAnimal(Scanner sc,ICabinet service) throws Exception {
        System.out.println("Veuillez entrer le nom de l'animal  \n");
        String nom = sc.next();
        return service.deleteAnimal(nom);
    }

    private String rechercherAnimal(Scanner sc,ICabinet service) throws Exception {
        System.out.println("Veuiller entrer le nom de l'animal \n");
        String nom = sc.next();
        String response = "";
        IAnimal animal = service.recherche(nom);
        if(animal != null){
            response = animal.toString();
        }
        else {
            response = "Aucun animal ayant ce nom n'a été enregistrer dans ce cabinet \n";
        }
        return response;
    }

    private void RecupererInfos(Scanner sc,ICabinet service) throws Exception {
        System.out.println("Veuillez entrer le nom de l'animal\n");
        String nom = sc.next();
        IAnimal response = service.recherche(nom);
        System.out.println("1- Nom \n2- Nom du maitre \n3- La race \n4- L'espece \n5- Dossier de suivi\nAutre - pour tout voir\n");
        int casee = sc.nextInt();
        switch (casee){
            case 1:
                System.out.println("Le nom est : "+response.getName());
                break;
            case 2:
                System.out.println("Nom du maitre est : "+response.getMasterName());
                break;
            case 3:
                System.out.println("La race  est : "+response.getRace());
                break;
            case 4:
                System.out.println("L'espece est : "+response.getSpecie().toString());
                break;
            case 5:
                System.out.println("Le dossier de suivi est : "+response.getTrackingFile().getFileContent());
                break;
            default:
                System.out.println("Le nom est : "+response.getName()+"\nNom du maitre est : "+response.getMasterName()+"\nLa race  est : "+response.getRace()+"\nL'espece est : "+response.getSpecie().getName()+"\nDossier de suivi : "+response.getTrackingFile().getFileContent());
                break;
        }
    }

    private void ModifierInfos(Scanner sc,ICabinet service) throws Exception {
        System.out.println("Veuillez entrer le nom de l'animal \n");
        String nom = sc.next();
        IAnimal response = service.recherche(nom);
        if(response != null){
            System.out.println("1- Nom \n2- Nom du maitre \n3- Dossier de suivi");
            int casee = sc.nextInt();
            switch (casee){
                case 1:
                    System.out.println("Entrez le nouveau nom (En un mot avec '-' si obligé) : ");
                    String nvNom = sc.next();
                    System.out.println(service.setAnimalname(nom, nvNom));
                    break;
                case 2:
                    System.out.println("Entrez le nom du nouveau maitre (En un mot avec '-' si obligé) : ");
                    String nvmaitre = sc.next();
                    service.setAnimalMastername(nom, nvmaitre);
                    break;
                case 3:
                    System.out.println("Entrez le nouveau contenu du dossier : ");
                    String nvcontenu = sc.nextLine();
                    service.setTrackingFile(nom,nvcontenu);
                    break;

            }
        }
        else {
            System.out.println("L'animal n'existe pas \n");
        }

    }

    private void Whiledoer(ICabinet service, Scanner sc){
        while(true){
            System.out.println("Veuillez choisir parmis les actions (1 - 6) suivantes : ");
            System.out.println("1- Ajouter un animal \n2- Supprimer un animal\n3- rechercher un animal\n4- Ajouter un animal Special\n5- Recuperer les infos d'un animal\n6- Modifier les infos d'un animal\n");
            int choix = 0;
            try{
                choix = sc.nextInt();
            }catch (Exception e){
                System.out.println("Erreur : veuillez entrez un chiffre entre 1 - 6 \n");
                sc.nextLine();
            }
            switch (choix) {
                case 1:
                    try{
                        System.out.println("\n"+enregisterAnimal(sc,service,0));
                    }
                    catch(Exception e){
                        System.out.println("Erreur lors de l'ajout de l'animal. Veuiller reessayer ! \n");
                    }
                    break;
                case 2:
                    try{
                        System.out.println("\n"+supprimerAnimal(sc,service));
                    }
                    catch (Exception e){
                        System.out.println("Erreur lors de la suppression de l'animal. Veuiller reessayer ! \n");
                    }
                    break;
                case 3:
                    try {
                        System.out.println("\n"+rechercherAnimal(sc,service));
                    }catch (Exception e){
                        System.out.println("Veuillez reesayer votre recherche \n");
                    }
                    break;
                case 4:
                    try{
                        System.out.println("\n"+enregisterAnimal(sc,service,1));
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                        System.out.println("Erreur lors de l'ajout de l'animal. Veuiller reessayer ! \n");
                    }
                    break;
                case 5: // Recuperer infos de l'animal
                    try {
                        RecupererInfos(sc,service);
                    }
                    catch (Exception e){
                        System.out.println("Erreur de recuperation. Veuiller reessayer ! \n");
                    }
                    break;
                case 6:
                    try {
                        ModifierInfos(sc,service);
                    }
                    catch (Exception e){
                        System.out.println("Erreur lors de la modification. Veuiller reessayer ! \n");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // PARTIE 1 :
        ICabinet service = (ICabinet) proxy.getObject();

        System.out.println("Bonjour bienvenue sur le site du cabinet : "+ service.getName()+" !");
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez Entrez votre nom: ");
        String nom = sc.next();
        IVeterinaire vet = new Veterinaire(nom);
        service.SaveVeterianire(vet);

        System.out.println("Pour un bon fonctionnement de l'application veuillez à entrer les infos en UN mot , utilisez un '-' ou '_' si obligé !");

        // On effectue le while : boucle infini pour run l'application
        Whiledoer(service,sc);
    }
}
