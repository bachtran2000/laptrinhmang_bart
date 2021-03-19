package Timtufile_UDP;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client {
//    static ArrayList<Nhanvien> dsnv = new ArrayList<>();

    public static void menu() {
        System.out.println("1. Tim kiem sinh vien");
        System.out.println("2. Xem danh sach nhan vien");
        System.out.println("Chon: ");
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        DatagramSocket client = new DatagramSocket();
        byte[] sendData = new byte[1024];
        InetAddress ip = InetAddress.getByName("localhost");
        DatagramPacket packetSend;
        while (true) {
            menu();
            String c = new Scanner(System.in).nextLine();

            switch (c) {
                case "2": //show

                    sendData = c.getBytes(StandardCharsets.UTF_8);

                    //đóng gói dữ liệu
                    packetSend = new DatagramPacket(sendData, sendData.length, ip, 2349);
                    client.send(packetSend);

                    System.out.println("Danh sach nhan vien: ");
                    while (true) {
                        //tạo buffer mới để nhận data từ server
                        byte[] receive_data = new byte[1024];
                        DatagramPacket receiveData = new DatagramPacket(receive_data, receive_data.length);
                        //giải nén packet nhận được
                        client.receive(receiveData);

                        System.out.println(new String(receiveData.getData()));
                    }
                case "1"://tim kiem
                    sendData = c.getBytes(StandardCharsets.UTF_8);

                    //đóng gói dữ liệu
                    packetSend = new DatagramPacket(sendData, sendData.length, ip, 2349);
                    client.send(packetSend);

                    System.out.println("Nhap vao ten can tim: ");
                    String f_str = in.nextLine();

                    byte[] sendData2 = new byte[1024];
                    sendData2 = f_str.getBytes(StandardCharsets.UTF_8);
                    packetSend = new DatagramPacket(sendData2, sendData2.length, ip, 2349);

                    client.send(packetSend);

                    byte[] receive_data = new byte[1024];
                    DatagramPacket receiveData = new DatagramPacket(receive_data, receive_data.length);
                    //giải nén packet nhận được
                    client.receive(receiveData);
                    System.out.println(new String(receiveData.getData()));
                    break;
                case "3":
                    exit(0);
            }
        }
    }
}
