package QLNH_UDP;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static java.lang.System.exit;

public class client {
    public static void menu() {
        System.out.println("1. Nap tien");
        System.out.println("2. Rut tien");
        System.out.println("3. Xem so du");
        System.out.println("4. Xem lich su giao dich");
        System.out.println("5. Thoat");
        System.out.print("Chon: ");
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");

        while (true) {
            menu();
            int n = new Scanner(System.in).nextInt();
            switch (n) {
                case 1:
                    byte[] dataSend = new byte[1024];
                    String str_send = n + "";
                    dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                    DatagramPacket packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    System.out.println("Nhap vao so tien: ");
                    int sotien = new Scanner(System.in).nextInt();

                    dataSend = new byte[1024];
                    str_send = sotien + "";
                    dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);
                    System.out.println("Nap thanh cong!");
                    break;
                case 2:
                    dataSend = new byte[1024];
                    str_send = n + "";
                    dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    System.out.println("Nhap vao so tien: ");
                    sotien = new Scanner(System.in).nextInt();

                    dataSend = new byte[1024];
                    str_send = sotien + "";
                    dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    byte[] dataReceive = new byte[1024];
                    DatagramPacket packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
                    client.receive(packetReceive);
                    String str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
                    System.out.println(str_receive);
                    break;
                case 3:
                    dataSend = new byte[1024];
                    str_send = n + "";
                    dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    dataReceive = new byte[1024];
                    packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
                    client.receive(packetReceive);
                    str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
                    System.out.println("So du cua tai khoan la: "+str_receive);
                    break;
                case 4:
                    dataSend = new byte[1024];
                    str_send = n + "";
                    dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    dataReceive = new byte[1024];
                    packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
                    client.receive(packetReceive);
                    str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
                    System.out.println("Lich su giao dich cua tai khoan la: "+str_receive);
                    break;
                case 5:
                    dataSend = new byte[1024];
                    str_send = n + "";
                    dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    exit(0);
            }
        }
    }
}
