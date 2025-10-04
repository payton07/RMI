package org.rmi.commons.interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICabinet extends Remote {
    String getName() throws RemoteException;
    void setName(String name) throws RemoteException;
    IAnimal getAnimal(int i) throws RemoteException;
    void addAnimal(IAnimal animal) throws RemoteException;
    IAnimal recherche(String nom) throws RemoteException;
}