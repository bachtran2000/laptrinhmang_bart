package De2.Bai1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client_tich {
    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");

        byte[] dataSend = new byte[1024];

        System.out.println("Nhap vao so a: ");
        String a = new Scanner(System.in).nextLine();
        dataSend = a.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packetSend  = new DatagramPacket(dataSend,dataSend.length,ip,2349);
        client.send(packetSend);

        System.out.println("Nhap vao so b: ");
        String b = new Scanner(System.in).nextLine();
        dataSend = b.getBytes(StandardCharsets.UTF_8);
        packetSend = new DatagramPacket(dataSend,dataSend.length,ip,2349);
        client.send(packetSend);

        byte[] dataReceive = new byte[1024];
        DatagramPacket packetReceive = new DatagramPacket(dataReceive,0,dataReceive.length);
        client.receive(packetReceive);
        String str_receive = new String(packetReceive.getData(),0,packetReceive.getLength());
        System.out.println("Ket qua "+a+"*"+b+" = "+str_receive);
    }
}
