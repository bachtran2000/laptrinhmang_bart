package QLNH_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

import static java.lang.System.exit;

public class server {
    static int soDu = 0;
    static String hisTrad = "";

    public static void Nap(int sotien) {
        soDu += sotien;
        hisTrad += "+" + sotien + "\t";
    }

    public static void Rut(int sotien) {
        soDu -= sotien;
        hisTrad += "-" + sotien + "\t";
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(2349);
        byte[] dataReceive = new byte[1024];

        while (true) {
            dataReceive = new byte[1024];
            DatagramPacket packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
            server.receive(packetReceive);
            String str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
            switch (str_receive) {
                case "1":
                    System.out.println(str_receive);
                    dataReceive = new byte[1024];
                    packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
                    server.receive(packetReceive);
                    str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());

                    int sotien = Integer.parseInt(str_receive);
                    Nap(sotien);
                    break;
                case "2":
                    System.out.println(str_receive);
                    dataReceive = new byte[1024];
                    packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
                    server.receive(packetReceive);
                    str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
                    sotien = Integer.parseInt(str_receive);

                    if (sotien > soDu) {
                        byte[] dataSend = new byte[1024];
                        String str_send = "Khong du so du de thuc hien rut";
                        dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                        DatagramPacket packetSend = new DatagramPacket(dataSend, dataSend.length, packetReceive.getAddress(), packetReceive.getPort());
                        server.send(packetSend);
                    } else {
                        Rut(sotien);
                        byte[] dataSend = new byte[1024];
                        String str_send = "Rut tien thanh cong";
                        dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                        DatagramPacket packetSend = new DatagramPacket(dataSend, dataSend.length, packetReceive.getAddress(), packetReceive.getPort());
                        server.send(packetSend);
                    }
                    break;
                case "3":
                    System.out.println(soDu);
                    byte[] dataSend = new byte[1024];
                    String str_send = soDu + "";
                    System.out.println(str_send);
                    dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                    DatagramPacket packetSend = new DatagramPacket(dataSend, dataSend.length, packetReceive.getAddress(), packetReceive.getPort());
                    server.send(packetSend);
                    break;
                case "4":
                    System.out.println(soDu);
                    dataSend = new byte[1024];
                    System.out.println(hisTrad);
                    dataSend = hisTrad.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, packetReceive.getAddress(), packetReceive.getPort());
                    server.send(packetSend);
                    break;
                case "5":
                    exit(0);
            }
        }
    }
}
