package De4.Bai1;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client_Upcase {

    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");

        System.out.print("Nhap vao 1 chuoi ki tu: ");
        String str_send = new Scanner(System.in).nextLine();

        byte[] sendData= new byte[1024];
        sendData = str_send.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packetSend = new DatagramPacket(sendData,sendData.length,ip,2349);
        client.send(packetSend);

        byte[] receiveData = new byte[1024];
        DatagramPacket packetReceive = new DatagramPacket(receiveData, receiveData.length);

        client.receive(packetReceive);
        String str_receive = new String(packetReceive.getData(),0,packetReceive.getLength());

        System.out.println(str_receive);

    }
}
