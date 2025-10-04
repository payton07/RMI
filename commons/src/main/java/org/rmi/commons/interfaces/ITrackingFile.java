package org.rmi.commons.interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITrackingFile extends Remote {
    String getFileContent() throws RemoteException;
    void setFileContent(String fileContent) throws RemoteException;
}
