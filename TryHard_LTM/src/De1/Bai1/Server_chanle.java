package De1.Bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_chanle {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2349);
        Socket my_client = server.accept();
        DataInputStream dis = new DataInputStream(my_client.getInputStream());
        DataOutputStream dos = new DataOutputStream(my_client.getOutputStream());

        int n = dis.readInt();
        if (n%2==0) dos.writeUTF("chan");
        else dos.writeUTF("le");
    }
}
