package TCP_check_SNT;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 7);

        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());

        while (true) {
            System.out.println("Nhap vao 1 so nguyen: ");
            int a = new Scanner(System.in).nextInt();
            dos.writeInt(a);

            System.out.println(dis.readUTF());
        }

    }
}
