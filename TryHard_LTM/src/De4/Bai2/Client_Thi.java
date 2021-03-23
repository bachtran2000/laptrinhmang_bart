package De4.Bai2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client_Thi {
    public static void menu(){
        System.out.println("1. Bat dau thi");
        System.out.println("2. Xem ket qua thi");
        System.out.println("3. Thoat");
        System.out.print("Chon: ");
    }

    public static void SendBaithi(DataInputStream dis, DataOutputStream dos, int n) throws IOException {

        System.out.println("Bat dau thi: ");
        //1
        System.out.println(dis.readUTF());
        String check = new Scanner(System.in).nextLine();
        dos.writeUTF(check);

        //2
        System.out.println(dis.readUTF());
        check = new Scanner(System.in).nextLine();
        dos.writeUTF(check);

        //3
        System.out.println(dis.readUTF());
        check = new Scanner(System.in).nextLine();
        dos.writeUTF(check);

        System.out.println(dis.readUTF());
    }
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1",2349);
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        while (true){
            menu();
            int n = new Scanner(System.in).nextInt();

            switch (n){
                case 1:
                    dos.writeInt(n);
                    SendBaithi(dis,dos,n);
                    break;
                case 2:
                    dos.writeInt(n);
                    System.out.println(dis.readUTF());
                    break;
                case 3:
                    dos.writeInt(n);
                    exit(0);
            }
        }


    }

}
