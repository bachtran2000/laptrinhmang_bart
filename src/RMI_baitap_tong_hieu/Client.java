package RMI_baitap_tong_hieu;

import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public Client() throws Exception {

        menu();
        int c = new Scanner(System.in).nextInt();

        System.out.print("Nhap vao so a: ");
        int a = new Scanner(System.in).nextInt();
        System.out.print("Nhap vao so b: ");
        int b = new Scanner(System.in).nextInt();
        Registry client = LocateRegistry.getRegistry("localhost", 2349);
        interface_rmi regis = (interface_rmi) client.lookup("Server");
        switch (c) {
            case 1:
                System.out.println("Gia tri phep toan la: a + b =" + regis.tong(a, b));
                break;
            case 2:
                System.out.println("Gia tri phep toan la: a - b = " + regis.hieu(a, b));
        }

    }

    public void menu() {
        System.out.println("1. tinh tong");
        System.out.println("2. tinh hieu");
        System.out.println("Chon: ");
    }

    public static void main(String[] args) throws Exception, NotBoundException {
        Client c = new Client();
    }
}
