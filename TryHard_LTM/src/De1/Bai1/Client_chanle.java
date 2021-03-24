package De1.Bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client_chanle {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1",2349);
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());

        System.out.print("Nhap vao 1 so nguyen bat ki: ");
        int n = new Scanner(System.in).nextInt();
        dos.writeInt(n);

        String str_receive = dis.readUTF();
        System.out.println(n+" la so "+str_receive);
    }
}
