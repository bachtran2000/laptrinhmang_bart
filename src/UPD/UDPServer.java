package UPD;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class UDPServer {
    //    DatagramSocket myServer = null;
//    String input;
//    int port = 2349;
//
//    public void openServer() {
//        try {
//            myServer = new DatagramSocket(port);
//        } catch (SocketException e) {
//            System.out.println(e);
//        }
//    }
//
//    public void listening() {
//        byte[] receiveData = new byte[1024];
//        byte[] sendData = new byte[1024];
//        while (true) {
//            try {
//                DatagramPacket recievePacket = new DatagramPacket(receiveData, receiveData.length);
//                myServer.receive(recievePacket);
//                input = new String(recievePacket.getData());
//
//                ReverseString str = new ReverseString(input);
//                str.reverse();
//                InetAddress IPAddress = recievePacket.getAddress();
//                int port = recievePacket.getPort();
//                sendData = str.get_string().getBytes();
//                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
//
//                myServer.send(sendPacket);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public static boolean check_CL(int a) {
        if (a % 2 == 0) return true;
        else return false;
    }

    public static void main(String[] args) throws IOException, SocketException {
        //tạo buffer để nhận từ client
        DatagramSocket sever = new DatagramSocket(2349);
        byte[] receiveData = new byte[1024];
        DatagramPacket packetReceive = new DatagramPacket(receiveData, receiveData.length);
        //giải nén packet nhận từ client
        sever.receive(packetReceive);
        String s = new String(packetReceive.getData(), 0, packetReceive.getLength());

        String str_send = "";
        int n = Integer.parseInt(s);

        if (check_CL(n)) {
            str_send = s + " la so chan";
        } else str_send = s + " la so le";
        System.out.println(str_send);

        receiveData = str_send.getBytes();

        //đóng gói packet
        DatagramPacket packetSend = new DatagramPacket(receiveData, receiveData.length, packetReceive.getAddress(), packetReceive.getPort());
        //gửi qua client
        sever.send(packetSend);

        sever.close();
    }
}
