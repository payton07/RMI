package org.rmi.server.impl;
import org.rmi.commons.impl.Animal;
import org.rmi.commons.impl.Species;
import org.rmi.commons.interfaces.*;
import org.springframework.stereotype.Service;
import java.rmi.RemoteException;
import java.util.ArrayList;


@Service
public class Cabinet  implements ICabinet {
    private String name = "MyCabinet";
    private ArrayList<IAnimal> animals ;
    private ArrayList<IVeterinaire> vetos ;
    private int nbPatient = 0;
    private final int[] SEUILS = {3, 100, 500, 1000};

    public Cabinet() throws RemoteException {
        this.animals = new ArrayList<>();
        this.vetos = new ArrayList<>();
    }
    @Override
    public IAnimal getAnimal(int i) throws RemoteException{
        return animals.get(i);
    }

    @Override
    public String addAnimal(String name , String mastername , String race, String SpecieName, int lifespan) throws RemoteException{
        ISpecies species = new Species(SpecieName,lifespan);
        IAnimal animal = new Animal(name ,mastername,species,race);
        int oldnbPatient = nbPatient;
        nbPatient ++;
        animals.add(animal);
        CheckForAlert(oldnbPatient,nbPatient);
        return "L'animal : "+animal.getName()+" du maitre : "+animal.getMasterName()+" a bien été enregistré dans le cabinet !\n";
    }

    @Override
    public String addAnimalForCodeBase(IAnimal animal) throws RemoteException{
        int oldnbPatient = nbPatient;
        nbPatient ++;
        if(animal != null){
            animals.add(animal);
        }
        String response = "L'animal special : "+animal.getName()+" du maitre : "+animal.getMasterName()+" a bien été enregistré dans le cabinet !\n";
        CheckForAlert(oldnbPatient,nbPatient);
        return response;
    }

    @Override
    public IAnimal recherche(String nom) throws RemoteException{
        for (IAnimal animal : animals) {
            if(animal.getName().equals(nom)) {
                return animal;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void CheckForAlert(int oldValue, int newValue) {
        for (int seuil : SEUILS) {

            if (oldValue < seuil && newValue >= seuil) {
                notifierTous("Seuil HAUT atteint : " + seuil + " patients (total = " + newValue + ")\n");
            }
            // Passage à la baisse
            if (oldValue >= seuil && newValue < seuil) {
                notifierTous("Seuil BAS franchi : " + seuil + " patients (total = " + newValue + ")\n");
            }
        }
    }

    private void notifierTous(String message) {
        for (IVeterinaire client : vetos) {
            try {
                client.AlertClient(message);
            } catch (Exception e) {
                System.err.println("Impossible de notifier un client : " + e);
            }
        }
    }

    @Override
    public void SaveVeterianire(IVeterinaire vet) throws RemoteException {
        vetos.add(vet);
    }

    @Override
    public String setAnimalname(String nomAnimal, String nvNom) throws RemoteException{
        IAnimal animal = recherche(nomAnimal);
        if (animal != null) {
            animal.setName(nvNom);
            return "Nom de l'animal a bien été modifié !\n";
        }
        return "Animal non existant !\n";
    }

    @Override
    public String setAnimalMastername(String nomAnimal, String mastername) throws RemoteException {
        IAnimal animal = recherche(nomAnimal);
        if (animal != null) {
            animal.setMasterName(mastername);
            return "Nom du maitre de l'animal a bien été modifié !\n";
        }
        return "Animal non existant !\n";
    }

    @Override
    public String setTrackingFile(String nomAnimal, String trackingFileContent) throws RemoteException {
        IAnimal animal = recherche(nomAnimal);
        if (animal != null) {
            ITrackingFile track = animal.getTrackingFile();
            if (track != null) {
                track.setFileContent(trackingFileContent);
            }
            return "Le dossier de suivi de l'animal a bien été modifié !\n";
        }
        return "Animal non existant !\n";
    }

    @Override
    public String deleteAnimal(String name) throws RemoteException{
        int oldnbPatient = nbPatient;
        nbPatient --;
        IAnimal a = recherche(name);
        CheckForAlert(oldnbPatient,nbPatient);
        if (a != null) {
            String response = "L'animal : "+a.getName()+" du maitre : "+a.getMasterName()+" a bien été supprimer !\n";
            animals.remove(a);
            return response;
        }
        return "Animal non existant !\n";
    }
}
