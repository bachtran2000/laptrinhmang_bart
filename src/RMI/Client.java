package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.PseudoColumnUsage;
import java.sql.SQLOutput;

public class Client {
    public Client() throws Exception {
        Registry client = LocateRegistry.getRegistry("localhost",7 );
        interface_rmi regis = (interface_rmi) client.lookup("Server");
        System.out.println("Client: "+regis.add(10,20));
    }

    public static void main(String[] args) throws Exception , NotBoundException{
        Client c = new Client();
    }
}
