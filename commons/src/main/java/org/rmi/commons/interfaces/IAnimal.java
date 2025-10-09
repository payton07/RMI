package org.rmi.commons.interfaces;

import java.rmi.Remote;

public interface IAnimal {
    String getCompletName();
    String getMasterName();
    ITrackingFile getTrackingFile();
    ISpecies getSpecie();
    String getName();
    String getRace();
    void setName(String name);
    void setRace(String race);
    void setMasterName(String masterName);
}
