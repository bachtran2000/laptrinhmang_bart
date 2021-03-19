package File_UDP;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client_File {
    public static void menu() {
        System.out.println("1. Viet to English");
        System.out.println("2. English to Viet");
        System.out.println("3. Show tu dien");
        System.out.println("4. Thoat");
        System.out.print("Chon: ");
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();
        byte[] sendData;
        InetAddress ip = InetAddress.getByName("localhost");
        DatagramPacket packetSend;
        while (true) {
            menu();
            String str_send = new Scanner(System.in).nextLine();

            switch (str_send) {
                case "1": // V to E
                    sendData = new byte[1024];
                    sendData = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(sendData, sendData.length, ip, 1001);
                    client.send(packetSend);

                    System.out.print("Nhap tu can dich: ");

                    str_send = new Scanner(System.in).nextLine();
                    sendData = new byte[1024];
                    sendData = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(sendData, sendData.length, ip, 1001);
                    client.send(packetSend);


                    byte[] receiveData = new byte[1024];
                    DatagramPacket packetReceive = new DatagramPacket(receiveData, receiveData.length);
                    client.receive(packetReceive);
                    String str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
                    System.out.println(str_receive);
                    break;
                case "2":
                    sendData = new byte[1024];
                    sendData = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(sendData, sendData.length, ip, 1001);
                    client.send(packetSend);

                    System.out.print("Nhap tu can dich: ");

                    str_send = new Scanner(System.in).nextLine();
                    sendData = new byte[1024];
                    sendData = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(sendData, sendData.length, ip, 1001);
                    client.send(packetSend);


                    receiveData = new byte[1024];
                    packetReceive = new DatagramPacket(receiveData, receiveData.length);
                    client.receive(packetReceive);
                    str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
                    System.out.println(str_receive);
                    break;
                case "3":
                    //gui case 3
                    sendData = new byte[1024];
                    sendData = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(sendData, sendData.length, ip, 1001);
                    client.send(packetSend);

                    //nhan so n
                    int n;
                    receiveData = new byte[1024];
                    packetReceive = new DatagramPacket(receiveData, receiveData.length);
                    client.receive(packetReceive);
                    str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
                    n = Integer.parseInt(str_receive);


                    //nhan data tu server
                    for (int i = 0; i < n; i++) {
                        receiveData = new byte[1024];
                        packetReceive = new DatagramPacket(receiveData, receiveData.length);
                        client.receive(packetReceive);
                        str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
                        System.out.println("|_____"+str_receive+"_________|");
                    }
                    System.out.println();
                    break;
                case "4":
                    sendData = new byte[1024];
                    sendData = str_send.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(sendData, sendData.length, ip, 1001);
                    client.send(packetSend);
                    exit(0);
            }
        }
    }
}
