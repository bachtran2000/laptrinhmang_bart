package TuhocUDP;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void menu() {
        System.out.println("1. Xem danh sach nhan vien");
        System.out.println("Chon: ");
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();
        byte[] sendData = new byte[1024];
        menu();

        String a = new Scanner(System.in).nextLine();

        sendData = String.valueOf(a).getBytes(StandardCharsets.UTF_8);

        InetAddress ip = InetAddress.getByName("localhost");
        //đóng gói dữ liệu
        DatagramPacket packetSend = new DatagramPacket(sendData, sendData.length, ip, 2349);

        //gửi lên server
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
    }
}