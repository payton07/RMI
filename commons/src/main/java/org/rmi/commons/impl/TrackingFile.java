package org.rmi.commons.impl;
import org.rmi.commons.interfaces.ITrackingFile;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TrackingFile extends UnicastRemoteObject implements ITrackingFile {
    private String fileContent;

    public TrackingFile(String fileContent) throws RemoteException {
        this.fileContent = fileContent;
    }
    @Override
    public String getFileContent() throws RemoteException {
        return this.fileContent;
    }

    @Override
    public void setFileContent(String fileContent) throws RemoteException {
        this.fileContent = fileContent;
    }

}
