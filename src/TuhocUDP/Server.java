package TuhocUDP;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void ReadFile(DatagramSocket server, byte[] receiveData, DatagramPacket packetReceive) {
        String str_send;

        try {
            //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
            File f = new File("/Users/trancaominhbach/Desktop/laptrinhmang_bart/src/TuhocUDP/nhanvien.dat");
            FileReader fr = new FileReader(f);
            //Bước 2: Đọc dữ liệu
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                sendData(line,server,receiveData,packetReceive);
            }
            //Bước 3: Đóng luồng
            fr.close();
            br.close();
        } catch (Exception ex) {
            System.out.println("Loi doc file: " + ex);
        }
    }

    public static void sendData( String str_send,DatagramSocket server, byte[] receiveData, DatagramPacket packetReceive) throws IOException {
        receiveData = str_send.getBytes();
        DatagramPacket packetSend = new DatagramPacket(receiveData, receiveData.length, packetReceive.getAddress(), packetReceive.getPort());
        server.send(packetSend);
    }


    public static void main(String[] args) throws IOException {
        //tạo buffer để nhận từ client
        DatagramSocket sever = new DatagramSocket(2349);
        byte[] receiveData = new byte[1024];
        DatagramPacket packetReceive = new DatagramPacket(receiveData, receiveData.length);
        //giải nén packet nhận từ client
        sever.receive(packetReceive);
        String s = new String(packetReceive.getData(), 0, packetReceive.getLength());

//        if (s.equals("xem")) {
//            ReadFile(sever, receiveData, packetReceive);
//        } else System.out.println("error");
        String c=s;

        switch (c){
            case "1":
                ReadFile(sever,receiveData,packetReceive);
                break;
            default:
                System.out.println("Sai roi!");
        }

//        receiveData = str_send.getBytes();
//
//        //đóng gói packet
//        DatagramPacket packetSend = new DatagramPacket(receiveData, receiveData.length, packetReceive.getAddress(), packetReceive.getPort());
//        //gửi qua client
//        sever.send(packetSend);

        sever.close();

    }
}
