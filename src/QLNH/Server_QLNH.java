package QLNH;

import UPD.UDPServer;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Server_QLNH {
    static ArrayList<User> listUS = new ArrayList<>();
    static String str_user, str_pass,str_balance;

    public static void ReadFile() {
        try {
            File f = new File("/Users/trancaominhbach/Desktop/laptrinhmang_bart/src/QLNH/DataUser.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String result[] = new String[10];
                result = line.split("\\$");
                User user = new User();

                user.setName(result[0]);
                user.setPass(result[1]);
                user.setBalance(Integer.parseInt(result[2]));
                user.setHisTrad(result[3]);

                listUS.add(user);
            }
            fr.close();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkValid(DatagramSocket server, byte[] dataSend, byte[] dataReceive) throws IOException {
        //nhan user
        DatagramPacket packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
        server.receive(packetReceive);
        String str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
        str_user = str_receive;

        //nhan pass
        packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
        server.receive(packetReceive);
        str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
        str_pass = str_receive;

        for (int i = 0; i < listUS.size(); i++) {
            if (str_user.equals(listUS.get(i).getName())) {
                if (str_pass.equals(listUS.get(i).getPass())) {

                    return true;
                }
            }
        }
        return false;
    }

    public static void withdrawal(DatagramSocket server, byte[] dataReceive) throws IOException {
        DatagramPacket packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
        server.receive(packetReceive);
        String str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());

        for (int i = 0; i < listUS.size(); i++) {
            if (listUS.get(i).getName().equals(str_user)) {
                listUS.get(i).setBalance(listUS.get(i).getBalance() + Integer.parseInt(str_receive));
                WriteFile();
            }
        }
    }

    public static void drawal(DatagramSocket server, byte[] dataReceive) throws IOException {
        DatagramPacket packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
        server.receive(packetReceive);
        String str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());

        for (int i = 0; i < listUS.size(); i++) {
            if (listUS.get(i).getName().equals(str_user)) {
                listUS.get(i).setBalance(listUS.get(i).getBalance() - Integer.parseInt(str_receive));
                WriteFile();
            }
        }
    }

    public static void showBalance(DatagramSocket server, byte[] dataReceive) throws IOException {
        DatagramPacket packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
        server.receive(packetReceive);
        String str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());

        for (int i = 0; i < listUS.size(); i++) {
            if (listUS.get(i).getName().equals(str_user)) {
                System.out.println(listUS.get(i).toString());
                str_balance = String.valueOf(listUS.get(i).getBalance());
            }
        }
        byte[] dataSend = new byte[1024];
        dataSend = str_balance.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packetSend = new DatagramPacket(dataSend,dataSend.length,packetReceive.getAddress(),packetReceive.getPort());
        server.send(packetSend);
    }

    /*public static void checkLogin(DatagramSocket server, byte[] dataReceive) throws IOException {
        DatagramPacket packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
        server.receive(packetReceive);
        String str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
        String str_user, str_pass;
        str_user = str_receive;
        for (int i = 0; i < listUS.size(); i++) {
            if (str_receive.equals(listUS.get(i).getName())) {
                packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
                server.receive(packetReceive);
                str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
                str_pass = str_receive;
                if (str_receive.equals(listUS.get(i).getPass())) {
                    packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
                    server.receive(packetReceive);
                    str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());
                    listUS.get(i).setBalance(listUS.get(i).getBalance() + Integer.parseInt(str_receive));
                    WriteFile();
                }
            }
        }
    }*/

    public static void WriteFile() {
        try {
            File f = new File("/Users/trancaominhbach/Desktop/laptrinhmang_bart/src/QLNH/DataUser.txt");
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < listUS.size(); i++) {
                fw.write(listUS.get(i).toString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(2349);
        byte[] dataReceive = new byte[1024];
        byte[] dataSend = new byte[1024];
        ReadFile();

        while (true) {
            DatagramPacket packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
            server.receive(packetReceive);
            String str_receive = new String(packetReceive.getData(), 0, packetReceive.getLength());

            switch (str_receive) {
                case "1":
                    if (checkValid(server, dataSend, dataReceive)) {
                        dataSend = new byte[1024];
                        dataSend = "1".getBytes(StandardCharsets.UTF_8);
                        DatagramPacket packetSend = new DatagramPacket(dataSend, dataSend.length, packetReceive.getAddress(), packetReceive.getPort());
                        server.send(packetSend);

                        withdrawal(server, dataReceive);
                    } else {
//                        String str_send = "1";
                        dataSend = new byte[1024];
                        dataSend = "Khong tim thay".getBytes(StandardCharsets.UTF_8);
                        DatagramPacket packetSend = new DatagramPacket(dataSend, dataSend.length, packetReceive.getAddress(), packetReceive.getPort());
                        server.send(packetSend);
                    }
                    break;
                case "2":
                    if (checkValid(server, dataSend, dataReceive)) {
                        dataSend = new byte[1024];
                        dataSend = "1".getBytes(StandardCharsets.UTF_8);
                        DatagramPacket packetSend = new DatagramPacket(dataSend, dataSend.length, packetReceive.getAddress(), packetReceive.getPort());
                        server.send(packetSend);

                        drawal(server, dataReceive);
                    } else {
//                        String str_send = "1";
                        dataSend = new byte[1024];
                        dataSend = "Khong tim thay".getBytes(StandardCharsets.UTF_8);
                        DatagramPacket packetSend = new DatagramPacket(dataSend, dataSend.length, packetReceive.getAddress(), packetReceive.getPort());
                        server.send(packetSend);
                    }
                    break;
                case "3":
                    if (checkValid(server, dataSend, dataReceive)) {
                        dataSend = new byte[1024];
                        dataSend = "1".getBytes(StandardCharsets.UTF_8);
                        DatagramPacket packetSend = new DatagramPacket(dataSend, dataSend.length, packetReceive.getAddress(), packetReceive.getPort());
                        server.send(packetSend);

                        showBalance(server, dataReceive);
                    } else {
//                        String str_send = "1";
                        dataSend = new byte[1024];
                        dataSend = "Khong tim thay".getBytes(StandardCharsets.UTF_8);
                        DatagramPacket packetSend = new DatagramPacket(dataSend, dataSend.length, packetReceive.getAddress(), packetReceive.getPort());
                        server.send(packetSend);
                    }
                    break;
                case "4":
                    break;
                case "5":
                    exit(0);
            }
        }
    }
}
