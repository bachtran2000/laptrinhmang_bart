package De3.Bai2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client_chat {
    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        byte[] str_send = new byte[1024];

        System.out.println("Dang nhap: ");
        //gui username
        System.out.print("Username: ");
        String username = new Scanner(System.in).nextLine();

        str_send = username.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packetSend = new DatagramPacket(str_send,str_send.length,ip,2349);
        client.send(packetSend);

        //gui pass
        System.out.print("Password: ");
        String pass = new Scanner(System.in).nextLine();

        str_send = pass.getBytes(StandardCharsets.UTF_8);
        packetSend = new DatagramPacket(str_send,str_send.length,ip,2349);
        client.send(packetSend);

//      Thong bao dang nhap
        byte[] dataReceive = new byte[1024];
        DatagramPacket packetReceive = new DatagramPacket(dataReceive,dataReceive.length);
        client.receive(packetReceive);
        String str_receive = new String(packetReceive.getData(),0,packetReceive.getLength());

        System.out.println(str_receive);
    }
}
