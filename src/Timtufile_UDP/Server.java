package Timtufile_UDP;

import TuhocUDP.Nhanvien;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    static ArrayList<TuhocUDP.Nhanvien> dsnv = new ArrayList<>();


//    public static void sendData(String str_send, DatagramSocket server, byte[] receiveData, DatagramPacket packetReceive) throws IOException {
//        receiveData = new byte[1024];
//        receiveData = str_send.getBytes();
//        DatagramPacket packetSend = new DatagramPacket(receiveData, receiveData.length, packetReceive.getAddress(), packetReceive.getPort());
//        server.send(packetSend);
//    }
//
//    public static String recieve_data(DatagramSocket server, byte[] recieveData, DatagramPacket packetReveive) throws IOException {
//        server.receive(packetReveive);
//        String s_receive= new String(packetReveive.getData(),0,packetReveive.getLength());
//        return s_receive;
//    }

    public static void find_HT(String f_HT, DatagramSocket server, DatagramPacket packetReceive) throws IOException {
        int flag = 0;

        for (int i = 0; i < dsnv.size(); i++) {
            if (dsnv.get(i).getHT().equals(f_HT)) {
                byte[] receiveData = new byte[1024];
                receiveData = dsnv.get(i).toString().getBytes(StandardCharsets.UTF_8);
                DatagramPacket packetSend = new DatagramPacket(receiveData, receiveData.length, packetReceive.getAddress(), packetReceive.getPort());
                server.send(packetSend);
                flag = flag + 1;
            }
        }
        if (flag == 0) System.out.println("Khong tim thay");
    }

    public static void ReadFile(DatagramSocket server, byte[] receiveData, DatagramPacket packetReceive) {


        try {
            //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
            File f = new File("/Users/trancaominhbach/Desktop/laptrinhmang_bart/src/Timtufile_UDP/nhanvien.dat");
            FileReader fr = new FileReader(f);
            //Bước 2: Đọc dữ liệu
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                receiveData = line.getBytes(StandardCharsets.UTF_8);
                DatagramPacket packetSend = new DatagramPacket(receiveData, receiveData.length, packetReceive.getAddress(), packetReceive.getPort());
                server.send(packetSend);
            }
            //Bước 3: Đóng luồng
            fr.close();
            br.close();
        } catch (Exception ex) {
            System.out.println("Loi doc file: " + ex);
        }
    }

    public static void file_array() {

        try {
            //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
            File f = new File("/Users/trancaominhbach/Desktop/laptrinhmang_bart/src/Timtufile_UDP/nhanvien.dat");
            FileReader fr = new FileReader(f);
            //Bước 2: Đọc dữ liệu
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                Nhanvien nv = new Nhanvien();
                nv.setHT(line);
                dsnv.add(nv);
            }
            //Bước 3: Đóng luồng
            fr.close();
            br.close();
        } catch (Exception ex) {
            System.out.println("Loi doc file: " + ex);
        }
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(2349);
        Scanner in = new Scanner(System.in);
        byte[] receiveData = new byte[1024];
        DatagramPacket packetReceive = new DatagramPacket(receiveData, receiveData.length);
        while (true) {
            server.receive(packetReceive);
            String s_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());

            String c = s_receive;
            file_array();
//        for (int i = 0; i < dsnv.size(); i++) {
//            System.out.println(dsnv.get(i).toString());
//        }

            switch (c) {
                case "1"://tim kiem sinh vien
                    byte[] receiveData2 = new byte[1024];
                    DatagramPacket packetReceive2 = new DatagramPacket(receiveData2, receiveData2.length);
                    server.receive(packetReceive2);
                    String s_receive2 = new String(packetReceive2.getData(), 0, packetReceive2.getLength());
                    System.out.println(s_receive2);
                    find_HT(s_receive2, server, packetReceive2);
                    break;
                case "2": //show
                    ReadFile(server, receiveData, packetReceive);
                    break;
                default:
                    System.out.println("Sai roi!");
            }
            server.close();
        }
    }

}
