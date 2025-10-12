package org.rmi.commons.impl;

import org.rmi.commons.interfaces.IVeterinaire;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Veterinaire extends UnicastRemoteObject implements IVeterinaire {
    private String name;
    public Veterinaire(String name) throws RemoteException {
        this.name = name;
    }
    @Override
    public void AlertClient(String s) throws RemoteException {
        System.out.println("Alert : "+s);
    }
}
