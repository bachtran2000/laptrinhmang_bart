package TCP_check_SNT;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static boolean Check_SNT(int a) {
        if (a <= 1) return false;
        else {
            for (int i = 2; i < a; i++) {
                if (a % i == 0) return false;
            }
        }
        return true;
    }

    public static int List_SNT(int a) {
        int c = 0;
        for (int i = 0; i <= a; i++) {
            if (Check_SNT(i))
                c++;
        }
        return c;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(7);
        Socket my_client = server.accept();
        DataInputStream dis = new DataInputStream(my_client.getInputStream());
        DataOutputStream dos = new DataOutputStream(my_client.getOutputStream());
        while (true) {
            int n = dis.readInt();

            if (Check_SNT(n)) {
                dos.writeUTF(n + " la SNT, co " + List_SNT(n) + " so tinh tu " + n);
            } else {
                dos.writeUTF("Khong phai SNT");
            }
        }

    }
}
