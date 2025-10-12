package org.rmi.commons.interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICabinet extends Remote {
    String getName() throws RemoteException;
    void setName(String name) throws RemoteException;
    IAnimal getAnimal(int i) throws RemoteException;
    String addAnimal(String name , String mastername , String race, String SpecieName, int lifespan) throws RemoteException;
    String addAnimalForCodeBase(IAnimal animal) throws RemoteException;
    String deleteAnimal(String name) throws RemoteException;
    IAnimal recherche(String name) throws RemoteException;
    void  SaveVeterianire(IVeterinaire veto) throws RemoteException;
    String setAnimalname(String oldname, String nvname) throws RemoteException;
    String setAnimalMastername(String oldname, String nvname) throws RemoteException;
    String setTrackingFile(String nom,String nvcontenu) throws RemoteException;
}