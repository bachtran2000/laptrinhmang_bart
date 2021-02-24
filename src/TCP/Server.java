package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(7);
        Socket my_client = server.accept();
        DataInputStream dis = new DataInputStream(my_client.getInputStream());
        DataOutputStream dos = new DataOutputStream(my_client.getOutputStream());
        while (true) {
            int n = Integer.parseInt(dis.readUTF());

            if (n % 2 == 0) {
                System.out.println("So chan");
                dos.writeUTF("so Chan");
            } else {
                System.out.println("So le");
                dos.writeUTF("So le");
            }
        }


    }
}
