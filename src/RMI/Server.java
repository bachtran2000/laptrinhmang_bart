package RMI;

import javax.management.remote.rmi.RMIServer;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public  class Server extends UnicastRemoteObject implements interface_rmi {

    public int add(int a, int b) throws RemoteException {
        return a+b;
    }
    public Server() throws RemoteException{
        Registry regis = LocateRegistry.createRegistry(7);
        regis.rebind("Server",this);
    }

    public static void main(String[] args) throws RemoteException {
            Server server = new Server();
    }
}
