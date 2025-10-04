package org.rmi.client;

import org.rmi.commons.interfaces.IAnimal;
import org.rmi.commons.interfaces.ISpecies;
import org.rmi.commons.interfaces.ITrackingFile;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OtherAnim extends UnicastRemoteObject implements IAnimal {
    private ISpecies species;
    private String name;
    private String masterName;
    private ITrackingFile trackingFile;
    private String race;

    public OtherAnim(String name,String masterName,String race,ISpecies species,ITrackingFile trackingFile) throws RemoteException {
        this.species = species;
        this.name = name;
        this.masterName = masterName;
        this.trackingFile = trackingFile;
        this.race = race;
    }
    @Override
    public String getCompletName() throws RemoteException {
        return this.name + " " + this.masterName;
    }

    @Override
    public String getMasterName() throws RemoteException {
        return this.masterName;
    }

    @Override
    public ITrackingFile getTrackingFile() throws RemoteException {
        return this.trackingFile;
    }

    @Override
    public ISpecies getSpecie() throws RemoteException {
        return this.species;
    }

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    @Override
    public String getRace() throws RemoteException {
        return this.race;
    }

    @Override
    public void Alert(String message) throws RemoteException {
        System.out.println("Alert : "+ message);
    }
}
