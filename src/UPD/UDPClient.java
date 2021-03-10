package UPD;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPClient {
    //    DatagramSocket mySocket = null;
//    int port = 2349;
//
//    public void connection(){
//        try{
//            mySocket = new DatagramSocket(port);
//
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }
//    }
//    public void send (String str){
//        if (mySocket!=null ){
//            byte[] sendData = new byte[1024];
//            try {
//                InetAddress IPAddress = InetAddress.getByName("localhost");
//                sendData=str.getBytes(StandardCharsets.UTF_8);
//                DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,IPAddress,port);
//                mySocket.send(sendPacket);
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    public String receive(){
//        if (mySocket!=null ){
//            byte[] receiveData = new byte[1024];
//            try{
//                DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
//                mySocket.receive(receivePacket);
//                return new String(receivePacket.getData());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    return null;
//    }
//
//    public void close(){
//        if (mySocket!=null){
//            try {
//                mySocket.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public static void main(String[] args) throws IOException {

        //tao 1 buffer để gửi tin đi
        DatagramSocket client = new DatagramSocket();
        byte[] sendData = new byte[1024];

        System.out.println("Nhap vao so bat ky: ");
        int a = new Scanner(System.in).nextInt();

        sendData = String.valueOf(a).getBytes(StandardCharsets.UTF_8);

        InetAddress ip = InetAddress.getByName("localhost");
        //đóng gói dữ liệu
        DatagramPacket packetSend = new DatagramPacket(sendData, sendData.length, ip, 2349);
        //gửi lên server
        client.send(packetSend);

        //tạo buffer mới để nhận data từ server
        byte[] receive_data = new byte[1024];
        DatagramPacket receiveData = new DatagramPacket(receive_data, receive_data.length);
        //giải nén packet nhận được
        client.receive(receiveData);

        System.out.println(new String(receiveData.getData()));
        client.close();
    }
}
