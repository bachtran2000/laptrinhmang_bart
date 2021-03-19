package De7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void menu() {
        System.out.println("1. Them cau thu moi");
        System.out.println("2. Tinh tien luong");
        System.out.print("Chon: ");
    }

    public static void Nhap(Socket client, DataOutputStream dos, DataInputStream dis) throws IOException {

        System.out.print("Nhap ma cau thu: ");
        String str_send = new Scanner(System.in).nextLine();
        dos.writeUTF(str_send);

        System.out.print("Nhap ten: ");
        str_send = new Scanner(System.in).nextLine();
        dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF(str_send);

        System.out.print("Nhap nam sinh: ");
        str_send = new Scanner(System.in).nextLine();
        dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF(str_send);

        System.out.print("Nhap vi tri da: (1/2/3/4/5)");
        str_send = new Scanner(System.in).nextLine();
        dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF(str_send);

        System.out.print("Nhap luong: ");
        str_send = new Scanner(System.in).nextLine();
        dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF(str_send);
    }

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 2349);
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        DataInputStream dis = new DataInputStream(client.getInputStream());

        while (true) {
            menu();
            int n = new Scanner(System.in).nextInt();
            dos.writeInt(n);
            switch (n) {
                case 1:
                    System.out.print("Nhap vao so luong can nhap: ");
                    n = new Scanner(System.in).nextInt();
                    dos.writeInt(n);
                    System.out.println("Nhap vao thong tin cau thu: ");
                    for (int i = 0; i < n; i++) {
                        Nhap(client,dos,dis);
                    }

                    System.out.println(dis.readUTF());
                    break;
            }
        }
    }
}
