package RMI_chanle;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interface_rmi extends Remote {
    public  boolean check(int a) throws RemoteException, Exception;
}
