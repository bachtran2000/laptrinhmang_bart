package De3.Bai2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Server_chat {
    static ArrayList<UserData> lsus = new ArrayList<>();

    public static void ReadFile() {
        try {
            File f = new File("/Users/trancaominhbach/Desktop/laptrinhmang_bart/TryHard_LTM/src/De3/Bai2/LoginData.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                UserData dt = new UserData();
                String result[] = new String[10];
                result = line.split("\\$");
                dt.username = result[0];
                dt.pass = result[1];
                lsus.add(dt);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int Login(DatagramSocket server) throws IOException {
        int flag = 0;
        //get username
        byte[] dataReceive1 = new byte[1024];
        DatagramPacket packetReceive1 = new DatagramPacket(dataReceive1, dataReceive1.length);
        server.receive(packetReceive1);
        String str_Receive1 = new String(packetReceive1.getData(), 0, packetReceive1.getLength());

        //get pass
        byte[] dataReceive2 = new byte[1024];
        DatagramPacket packetReceive2 = new DatagramPacket(dataReceive2, dataReceive2.length);
        server.receive(packetReceive2);
        String str_Receive2 = new String(packetReceive2.getData(), 0, packetReceive2.getLength());
        for (int i = 0; i < lsus.size(); i++) {
            if (lsus.get(i).getUsername().equals(str_Receive1))
                if (lsus.get(i).getPass().equals(str_Receive2))
                    flag++;
        }
        return flag;
    }

    public static void main(String[] args) throws IOException {
        ReadFile();
        DatagramSocket server = new DatagramSocket(2349);
        byte[] data_send = new byte[1024];
        byte[] data_receive = new byte[1024];

        DatagramPacket packetReceive = null;

//      int flag = Login(server);
        int flag = 0;
        //get username
        byte[] dataReceive1 = new byte[1024];
        DatagramPacket packetReceive1 = new DatagramPacket(dataReceive1, dataReceive1.length);
        server.receive(packetReceive1);
        String str_Receive1 = new String(packetReceive1.getData(), 0, packetReceive1.getLength());
        System.out.println(str_Receive1);


        //get pass
        byte[] dataReceive2 = new byte[1024];
        DatagramPacket packetReceive2 = new DatagramPacket(dataReceive2, dataReceive2.length);
        server.receive(packetReceive2);
        String str_Receive2 = new String(packetReceive2.getData(), 0, packetReceive2.getLength());
        System.out.println(str_Receive2);

        for (int i = 0; i < lsus.size(); i++) {
            if (lsus.get(i).getUsername().equals(str_Receive1))
                if (lsus.get(i).getPass().equals(str_Receive2))
                    flag++;
        }

        if (flag==1){
            String str_Send = "Chuc mung ban dang nhap thanh cong!";
            data_send = str_Send.getBytes(StandardCharsets.UTF_8);

            DatagramPacket packetSend = new DatagramPacket(data_send,data_send.length,packetReceive1.getAddress(),packetReceive1.getPort());
            server.send(packetSend);
        }
        else {
            String str_Send = "Sai thong tin dang nhap!";
            data_send = str_Send.getBytes(StandardCharsets.UTF_8);

            DatagramPacket packetSend = new DatagramPacket(data_send,data_send.length,packetReceive1.getAddress(),packetReceive1.getPort());
            server.send(packetSend);
        }

        Thread thread = new Thread();
        thread.start();

    }
}
