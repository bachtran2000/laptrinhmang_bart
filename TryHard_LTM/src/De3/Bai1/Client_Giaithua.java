package De3.Bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client_Giaithua {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1",2349);
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        DataInputStream dis = new DataInputStream(client.getInputStream());

        System.out.print("Nhap vao 1 so bat ki: ");
        int n = new Scanner(System.in).nextInt();
        dos.writeInt(n);

        System.out.println(n+" la so "+ dis.readUTF());
    }
}
