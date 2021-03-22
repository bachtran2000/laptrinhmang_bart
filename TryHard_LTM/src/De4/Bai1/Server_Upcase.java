package De4.Bai1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class Server_Upcase {

//    public static String upCase(DatagramSocket server) throws IOException {
//
//        byte[] receiveData = new byte[1024];
//
//        DatagramPacket packetReceive= new DatagramPacket(receiveData,receiveData.length);
//        server.receive(packetReceive);
//        String str_receive = new String(packetReceive.getData(),0, packetReceive.getLength());
//
//        String result = str_receive.toUpperCase();
////        System.out.println(result);
//        return result;
//    }

    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(2349);
        byte[] receiveData = new byte[1024];

        DatagramPacket packetReceive= new DatagramPacket(receiveData,receiveData.length);
        server.receive(packetReceive);
        String str_receive = new String(packetReceive.getData(),0, packetReceive.getLength());

        String result = str_receive.toUpperCase();
        System.out.println(result);

        byte[] sendData = new byte[1024];
        sendData = result.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packetSend = new DatagramPacket(sendData, sendData.length, packetReceive.getAddress(),packetReceive.getPort() );
        server.send(packetSend);
        System.out.println("Thanh cong!");

    }


}
