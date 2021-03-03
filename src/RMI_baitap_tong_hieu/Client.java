package RMI_baitap_tong_hieu;

import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client {
    public Client() throws Exception {
        while (true) {
            menu();
            int c = new Scanner(System.in).nextInt();
            int a, b;

            Registry client = LocateRegistry.getRegistry("localhost", 2349);
            interface_rmi regis = (interface_rmi) client.lookup("Server");
            switch (c) {
                case 1:
                    System.out.print("Nhap vao so a: ");
                    a = new Scanner(System.in).nextInt();
                    System.out.print("Nhap vao so b: ");
                    b = new Scanner(System.in).nextInt();
                    System.out.println("Gia tri phep toan la: a + b = " + regis.tong(a, b));
                    break;
                case 2:
                    System.out.print("Nhap vao so a: ");
                    a = new Scanner(System.in).nextInt();
                    System.out.print("Nhap vao so b: ");
                    b = new Scanner(System.in).nextInt();
                    System.out.println("Gia tri phep toan la: a - b = " + regis.hieu(a, b));
                default:
                    System.out.println("Chon sai! Yeu cau chon lai!");
            }
        }
    }


    public void menu() {
        System.out.println("1. tinh tong");
        System.out.println("2. tinh hieu");
        System.out.print("Chon: ");
    }

    public static void main(String[] args) throws Exception, NotBoundException {
        Client c = new Client();
    }
}
