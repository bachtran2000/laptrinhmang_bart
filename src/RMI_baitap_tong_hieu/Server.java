package RMI_baitap_tong_hieu;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements interface_rmi {

    public int tong(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public int hieu(int a, int b) throws Exception {
        return a - b;
    }

    public Server() throws RemoteException {
        Registry regis = LocateRegistry.createRegistry(2349);
        regis.rebind("Server", this);
    }

    public static void main(String[] args) throws RemoteException {
        Server server = new Server();
    }
}
