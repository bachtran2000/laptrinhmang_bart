package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interface_rmi extends Remote {
    public  int add(int a, int b) throws RemoteException, Exception;
}
