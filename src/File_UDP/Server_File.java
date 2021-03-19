package File_UDP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Server_File {
    static ArrayList<Diction> listTD = new ArrayList<>();

    public static void file_array() throws IOException {
        File f = new File("/Users/trancaominhbach/Desktop/laptrinhmang_bart/src/File_UDP/Dictionary.txt");
        FileReader fr = new FileReader(f);

        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            Diction td = new Diction();
            String[] result = line.split("\\$");
            td.setTV(result[0]);
            td.setTA(result[1]);
            listTD.add(td);
        }
//        System.out.println("Doc file thanh cong!");
        br.close();
        fr.close();
    }

    public static void VtoE(DatagramSocket server) throws IOException {
        byte[] receiveData = new byte[1024];
        DatagramPacket packetReceive = new DatagramPacket(receiveData, receiveData.length);
        server.receive(packetReceive);

        String str_recieve = new String(packetReceive.getData(), 0, packetReceive.getLength());
        System.out.println(str_recieve);
        int flag = 0;
        String found = "";

        for (int i = 0; i < listTD.size(); i++) {
            if (listTD.get(i).getTV().equalsIgnoreCase(str_recieve)) {
                found = listTD.get(i).getTA();
                System.out.println(found);
                flag++;
            }
        }
        if (flag != 0) {
            byte[] sendData = new byte[1024];
            sendData = found.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packetSend = new DatagramPacket(sendData, sendData.length, packetReceive.getAddress(), packetReceive.getPort());
            server.send(packetSend);
        } else {
            String str_send = "Khong tim thay!";
            byte[] sendData = new byte[1024];
            sendData = str_send.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packetSend = new DatagramPacket(sendData, sendData.length, packetReceive.getAddress(), packetReceive.getPort());
            server.send(packetSend);
        }
    }

    public static void EtoV(DatagramSocket server) throws IOException {
        byte[] receiveData = new byte[1024];
        DatagramPacket packetReceive = new DatagramPacket(receiveData, receiveData.length);
        server.receive(packetReceive);

        String str_recieve = new String(packetReceive.getData(), 0, packetReceive.getLength());
        System.out.println(str_recieve);
        int flag = 0;
        String found = "";

        for (int i = 0; i < listTD.size(); i++) {
            if (listTD.get(i).getTA().equalsIgnoreCase(str_recieve)) {
                found = listTD.get(i).getTV();
                System.out.println(found);
                flag++;
            }
        }
        if (flag != 0) {
            byte[] sendData = new byte[1024];
            sendData = found.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packetSend = new DatagramPacket(sendData, sendData.length, packetReceive.getAddress(), packetReceive.getPort());
            server.send(packetSend);
        } else {
            String str_send = "Khong tim thay!";
            byte[] sendData = new byte[1024];
            sendData = str_send.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packetSend = new DatagramPacket(sendData, sendData.length, packetReceive.getAddress(), packetReceive.getPort());
            server.send(packetSend);
        }
    }

    public static void show(DatagramSocket server, byte[] receiveData, DatagramPacket packetReceive) throws IOException {
        //gui so luong ve client
        String str_send;
        str_send = Integer.toString(listTD.size());
        receiveData = str_send.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packetSend = new DatagramPacket(receiveData, receiveData.length, packetReceive.getAddress(), packetReceive.getPort());
        server.send(packetSend);

        //gui data tu dien
        for (int i = 0; i < listTD.size(); i++) {
            str_send = listTD.get(i).getTV() + " = " + listTD.get(i).getTA();
            receiveData = str_send.getBytes(StandardCharsets.UTF_8);
            packetSend = new DatagramPacket(receiveData, receiveData.length, packetReceive.getAddress(), packetReceive.getPort());
            server.send(packetSend);
        }

    }

    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(1001);
        file_array();

        while (true) {

            byte[] recieveData = new byte[1024];
            DatagramPacket packetReceive = new DatagramPacket(recieveData, recieveData.length);
            server.receive(packetReceive);
            String str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());

            switch (str_receive) {
                case "1":
                    VtoE(server);
                    break;
                case "2":
                    EtoV(server);
                    break;
                case "3":
                    System.out.println("Da chon: " + str_receive);
                    for (int i = 0; i < listTD.size(); i++) {
                        System.out.println(listTD.get(i).getTV() + " = " + listTD.get(i).getTA());
                    }
                    show(server, recieveData, packetReceive);
                    break;
                case "4":
                    exit(0);

            }
        }
    }
}
