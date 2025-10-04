
package org.rmi.commons.impl;
import org.rmi.commons.interfaces.IAnimal;
import org.rmi.commons.interfaces.ISpecies;
import org.rmi.commons.interfaces.ITrackingFile;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Animal extends UnicastRemoteObject implements IAnimal {
    private String name;
    private String masterName;
    private ISpecies specie;
    private String race;
    private ITrackingFile trackingFile;

    public Animal(String name, String masterName, ISpecies specie, String race, ITrackingFile trackingFile) throws RemoteException {
        super();
        this.name = name;
        this.masterName = masterName;
        this.race = race;
        this.specie = new Species("Chien",12);
        this.trackingFile = new TrackingFile("Le chien pour l'instant il a rien");
    }

    public String getCompletName() throws RemoteException{
        return name+" "+masterName;
    }

    @Override
    public String getName() throws RemoteException  {
        return name;
    }

    @Override
    public String getMasterName() throws RemoteException {
        return masterName;
    }

    public String getRace() throws RemoteException {
        return race;
    }

    @Override
    public Species getSpecie() throws RemoteException {
        return  new Species(this.specie.getName(),this.specie.getLifeSpan());
    }

    @Override
    public ITrackingFile getTrackingFile() throws RemoteException {
        return this.trackingFile;

    }

    @Override
    public void Alert(String message) throws RemoteException {
        System.out.println("Alert : "+ message);
    }
}
