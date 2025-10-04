package org.rmi.server.impl;

import org.rmi.commons.interfaces.IAnimal;
import org.rmi.commons.interfaces.ICabinet;
import org.springframework.stereotype.Service;
import java.rmi.RemoteException;
import java.util.ArrayList;

@Service
public class Cabinet implements ICabinet {
    private String name = "MyCabinet";
    private ArrayList<IAnimal> animals ;
    private int nbPatient = 0;
    private final int[] SEUILS = {100, 500, 1000};

    public Cabinet() throws RemoteException {
        this.animals = new ArrayList<>();
    }
    @Override
    public IAnimal getAnimal(int i) throws RemoteException{
        return animals.get(i);
    }

    @Override
    public void addAnimal(IAnimal animal) throws RemoteException{
        int oldnbPatient = nbPatient;
        nbPatient ++;
        animals.add(animal);
        CheckForAlert(oldnbPatient,nbPatient);
    }

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
                notifierTous("Seuil HAUT atteint : " + seuil + " patients (total = " + newValue + ")");
            }
            // Passage à la baisse
            if (oldValue >= seuil && newValue < seuil) {
                notifierTous("Seuil BAS franchi : " + seuil + " patients (total = " + newValue + ")");
            }
        }
    }

    private void notifierTous(String message) {
        for (IAnimal patient : animals) {
            try {
                patient.Alert(message);
            } catch (Exception e) {
                System.err.println("Impossible de notifier un client : " + e);
            }
        }
    }
}
