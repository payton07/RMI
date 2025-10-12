package org.rmi.commons.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IVeterinaire extends Remote {
        void AlertClient(String message) throws RemoteException;
}
