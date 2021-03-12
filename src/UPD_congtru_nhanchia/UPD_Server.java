package UPD_congtru_nhanchia;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UPD_Server {
    public int tt(int a, int b){
        return a+b;
    }

    public int th(int a, int b){
        return a-b;
    }

    public int tn(int a, int b){
        return a*b;
    }
    public int tthuong(int a, int b){
        return a/b;
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(2349);
        byte[] receiveData1 = new byte[1024];
        DatagramPacket packetReceive1 = new DatagramPacket(receiveData1,receiveData1.length);
        server.receive(packetReceive1);
        String s = new String(packetReceive1.getData(), 0, packetReceive1.getLength());
        int a = Integer.parseInt(s);

        byte[] receiveData2 = new byte[1024];
        DatagramPacket packetReceive2 = new DatagramPacket(receiveData2,receiveData2.length);
        server.receive(packetReceive2);
        String s2 = new String(packetReceive2.getData(), 0, packetReceive2.getLength());
        int b = Integer.parseInt(s2);


    }
}
