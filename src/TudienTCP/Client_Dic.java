package TudienTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.in;

public class Client_Dic {
    public static void menu() {
        System.out.println("1. EN - VN");
        System.out.println("2. VN - EN");
        System.out.println("3. Exit");
        System.out.print("Chon: ");
    }

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 2349);
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        DataInputStream dis = new DataInputStream(client.getInputStream());

        while (true) {
            menu();
            int n = new Scanner(System.in).nextInt();

            switch (n) {
                case 1:
                    dos.writeInt(n);

                    System.out.print("Nhap tu can dich tieng anh: ");
                    String str_send = new Scanner(System.in).nextLine();
                    dos.writeUTF(str_send);
                    int int_receive = dis.readInt();

                    for (int i = 0; i < int_receive; i++) {
                        String str_receive = dis.readUTF();
                        System.out.println(str_send + " = " + str_receive);
                    }
                    if (int_receive == 0)
                        System.out.println(dis.readUTF());
                    break;
                case 2:
                    dos.writeInt(n);

                    System.out.print("Nhap tu can dich tieng viet: ");
                    str_send = new Scanner(System.in).nextLine();
                    dos.writeUTF(str_send);
                    int_receive = dis.readInt();

                    for (int i = 0; i < int_receive; i++) {
                        String str_receive = dis.readUTF();
                        System.out.println(str_send + " = " + str_receive);
                    }
                    if (int_receive == 0)
                        System.out.println(dis.readUTF());
                    break;
                case 3:
                    dos.writeInt(n);
                    exit(0);
            }
        }


    }

}
