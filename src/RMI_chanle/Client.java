package RMI_chanle;

import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public Client() throws Exception {
        System.out.print("Nhap vao 1 so: ");
        int a = new Scanner(System.in).nextInt();
        Registry client = LocateRegistry.getRegistry("localhost",3232 );
        interface_rmi regis = (interface_rmi) client.lookup("Server");
        if (regis.check(a)) System.out.println("Chan");
        else System.out.println("Le");
    }

    public static void main(String[] args) throws Exception , NotBoundException{
        Client c = new Client();
    }
}
