package De4.Bai2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.exit;

public class Server_Thi {
    public static int Question(DataInputStream dis, DataOutputStream dos) throws IOException {
        int flag = 0;

        dos.writeUTF("Cau 1: 1+1 = ?");
        if (dis.readUTF().equals("2"))
            flag++;

        dos.writeUTF("Cau 2: 2+2 = ?");
        if (dis.readUTF().equals("4"))
            flag++;

        dos.writeUTF("Cau 3: 4*4 = ?");
        if (dis.readUTF().equals("16"))
            flag++;

        return flag;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2349);
        Socket my_client = server.accept();
        DataInputStream dis = new DataInputStream(my_client.getInputStream());
        DataOutputStream dos = new DataOutputStream(my_client.getOutputStream());

        int flag=0;
        while (true) {
            int n = dis.readInt();
            switch (n) {
                case 1:
                    flag = Question(dis, dos);
                    if (flag >= 2)
                        dos.writeUTF("Chuc mung ban da thi do");
                    else dos.writeUTF("Tach roi ban oi");
                    break;
                case 2:
                    dos.writeUTF("Ket qua thi cua ban la: " +flag +"/3 cau dung");
                    break;
                case 3:
                    exit(0);
            }
        }
    }
}
