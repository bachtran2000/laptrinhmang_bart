package De2.Bai1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class Server_tich {
    public static void main(String[] args) throws IOException, NumberFormatException {
        DatagramSocket server = new DatagramSocket(2349);

        //nhan a
        byte[] dataReceive = new byte[1024];
        DatagramPacket packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
        server.receive(packetReceive);
        String str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
        String a = str_receive;
        System.out.println(a);

        //nhan b
        dataReceive = new byte[1024];
        packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
        server.receive(packetReceive);
        str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
        String b = str_receive;
        System.out.println(b);

        int c = Integer.parseInt(a)*Integer.parseInt(b);

        String mul = c+"";

        String str_send = String.valueOf(mul);
        byte[] dataSend = new byte[1024];
        dataSend = str_send.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packetSend = new DatagramPacket(dataSend, dataSend.length, packetReceive.getAddress(), packetReceive.getPort());
        server.send(packetSend);

    }
}
