
package org.rmi.commons.impl;
import org.rmi.commons.interfaces.IAnimal;
import org.rmi.commons.interfaces.ISpecies;
import org.rmi.commons.interfaces.ITrackingFile;
import java.io.Serializable;
import java.rmi.RemoteException;

public class Animal implements IAnimal, Serializable {
    private String name;
    private String masterName;
    private ISpecies specie;
    private String race;
    private ITrackingFile trackingFile;

    public Animal(String name, String masterName, ISpecies specie, String race) throws RemoteException {
        this.name = name;
        this.masterName = masterName;
        this.race = race;
        this.specie = specie;
        this.trackingFile = new TrackingFile("Tracking File is empty");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public void setSpecie(ISpecies specie) {
        this.specie = specie;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setTrackingFile(String trackingFile) throws RemoteException {
        this.trackingFile.setFileContent(trackingFile);
    }

    public String getCompletName() {
        return name+" "+masterName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getMasterName(){
        return masterName;
    }

    public String getRace() {
        return race;
    }

    @Override
    public Species getSpecie() {
        return  new Species(this.specie.getName(),this.specie.getLifeSpan());
    }

    @Override
    public ITrackingFile getTrackingFile() {
        return this.trackingFile;
    }

    public String toString(){
        return "L'animal  "+this.name+" a pour maitre  "+this.masterName+", est de la  race "+this.race+" et de l'espece : "+this.specie.getName()+"\n\n";
    }
}
