package org.rmi.client.impl;
import org.rmi.commons.impl.Species;
import org.rmi.commons.impl.TrackingFile;
import org.rmi.commons.interfaces.IAnimal;
import org.rmi.commons.interfaces.ISpecies;
import org.rmi.commons.interfaces.ITrackingFile;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AnimalSpecial  implements IAnimal , Serializable {
    private ISpecies species;
    private String name;
    private String masterName;
    private ITrackingFile trackingFile;
    private String race;

    public AnimalSpecial(String name, String masterName, String race, String nameSpecie , int lifespan) throws RemoteException {
        this.name = name;
        this.masterName = masterName;
        this.race = race;
        this.species = new Species(nameSpecie,lifespan);
        this.trackingFile = new TrackingFile("Tracking File is empty");
    }

    @Override
    public String getCompletName() {
        return this.name+" "+this.masterName;
    }

    @Override
    public String getMasterName() {
        return this.masterName;
    }

    @Override
    public ITrackingFile getTrackingFile() {
        return this.trackingFile;
    }

    @Override
    public ISpecies getSpecie() {
        return this.species;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getRace() {
        return this.race;
    }

    @Override
    public void setName(String s) {
        this.name = s;
    }

    @Override
    public void setRace(String s) {
        this.race = s;
    }

    @Override
    public void setMasterName(String s) {
        this.masterName = s;
    }

    public String toString(){
        return "L'animal  "+this.name+" a pour maitre  "+this.masterName+", est de la  race "+this.race+" et de l'espece : "+this.species.getName()+"\n\n";
    }
}
