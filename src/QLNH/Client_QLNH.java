package QLNH;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client_QLNH {

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
        byte[] dataSend = new byte[1024];
        byte[] dataReceive = new byte[1024];

        while (true) {
            menu();
            int c = new Scanner(System.in).nextInt();
            switch (c) {
                case 1:
                    //gui yeu cau
                    dataSend = String.valueOf(c).getBytes(StandardCharsets.UTF_8);
                    DatagramPacket packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    System.out.print("Nhap vao ten: ");
                    String str_send = new Scanner(System.in).nextLine();
                    //gui name
                    dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    System.out.print("Nhap vao pass: ");
                    str_send = new Scanner(System.in).nextLine();
                    //gui pass
                    dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    //check valid
                    dataReceive = new byte[1024];
                    DatagramPacket packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
                    client.receive(packetReceive);
                    String str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());

                    if (str_receive.equals("1")) {
                        System.out.print("Nhap vao so tien can nap: ");
                        str_send = new Scanner(System.in).nextLine();
                        //gui so tien
                        dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                        packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                        client.send(packetSend);
                    } else {
                        dataReceive = new byte[1024];
                        packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
                        //client.receive(packetReceive);
                        str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());

                        System.out.println("khong tim thay du lieu");
                    }

                    break;
                case 2:
                    dataSend = String.valueOf(c).getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    System.out.print("Nhap vao ten: ");
                    str_send = new Scanner(System.in).nextLine();
                    //gui name
                    dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    System.out.print("Nhap vao pass: ");
                    str_send = new Scanner(System.in).nextLine();
                    //gui pass
                    dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    //check valid
                    dataReceive = new byte[1024];
                    packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
                    client.receive(packetReceive);
                    str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());

                    if (str_receive.equals("1")) {
                        System.out.print("Nhap vao so tien can rut: ");
                        str_send = new Scanner(System.in).nextLine();
                        //gui so tien
                        dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                        packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                        client.send(packetSend);
                    } else {
                        dataReceive = new byte[1024];
                        packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
                        //client.receive(packetReceive);
                        str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());

                        System.out.println("khong tim thay du lieu");
                    }
                    break;
                case 3:
                    dataSend = String.valueOf(c).getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    dataSend = String.valueOf(c).getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    System.out.print("Nhap vao ten: ");
                    str_send = new Scanner(System.in).nextLine();
                    //gui name
                    dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    System.out.print("Nhap vao pass: ");
                    str_send = new Scanner(System.in).nextLine();
                    //gui pass
                    dataSend = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    //check valid
                    dataReceive = new byte[1024];
                    packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
                    client.receive(packetReceive);
                    str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());

                    if (str_receive.equals("1")) {
                        dataReceive = new byte[1024];
                        packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
                        client.receive(packetReceive);
                        str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());

                        System.out.println(str_receive);
                    } else {
                        dataReceive = new byte[1024];
                        packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
                        //client.receive(packetReceive);
                        str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());

                        System.out.println("khong tim thay du lieu");
                    }
                    break;
                case 4:
                    dataSend = String.valueOf(c).getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);
                    break;
                case 5:
                    dataSend = String.valueOf(c).getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(dataSend, dataSend.length, ip, 2349);
                    client.send(packetSend);

                    exit(0);
            }
        }
    }
}
