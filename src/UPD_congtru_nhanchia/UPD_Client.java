//package UPD_congtru_nhanchia;
//
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.SocketException;
//import java.nio.charset.StandardCharsets;
//import java.util.Scanner;
//
//public class UPD_Client {
//    public void menu(){
//        System.out.println("1. Cong");
//        System.out.println("2. Tru");
//        System.out.println("3. Nhan");
//        System.out.println("4. Chia");
//        System.out.print("Chon: ");
//    }
//
//    public static void main(String[] args) throws SocketException {
//        DatagramSocket client = new DatagramSocket(2349);
//        byte[] sendData1 = new byte[1024];
//        DatagramPacket packetSend1 = new DatagramPacket(sendData1,sendData1.length);
//        System.out.println("Nhap vao a: ");
//        int a = new Scanner(System.in).nextInt();
//
//        sendData1 = String.valueOf(a).getBytes();
//        client.send(sendData1);
//
//        byte[] sendData2 = new byte[1024];
//        DatagramPacket packetSend2 = new DatagramPacket(sendData2,sendData2.length);
//        System.out.println("Nhap vao a: ");
//        int b = new Scanner(System.in).nextInt();
//
//        sendData2 = String.valueOf(a).getBytes();
//        client.send(sendData2);
//    }
//}
