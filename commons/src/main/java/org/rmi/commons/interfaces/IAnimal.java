package org.rmi.commons.interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote {
    String getCompletName() throws RemoteException;
    String getMasterName() throws RemoteException;
    ITrackingFile getTrackingFile() throws RemoteException;
    ISpecies getSpecie() throws RemoteException;
    String getName() throws RemoteException;
    String getRace() throws RemoteException;
    void Alert(String message) throws RemoteException;
}
