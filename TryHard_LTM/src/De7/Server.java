package De7;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Server {
    static ArrayList<Cauthu> dsct = new ArrayList<>();

    public static int NhapFile_n(int n, Socket my_client, DataInputStream dis, DataOutputStream dos) throws IOException {
        int flag = 0;
        for (int i = 0; i < n; i++) {
            WriteFile_CT(my_client, dis, dos);
            flag++;
        }
        return flag;
    }

    public static void WriteFile_CT(Socket my_client, DataInputStream dis, DataOutputStream dos) throws IOException {

        Cauthu ct = new Cauthu();
        dis = new DataInputStream(my_client.getInputStream());
        String s = dis.readUTF();
        ct.setMCT(s);


        dis = new DataInputStream(my_client.getInputStream());
        s = dis.readUTF();
        ct.setHT(s);


        dis = new DataInputStream(my_client.getInputStream());
        s = dis.readUTF();
        ct.setNS(s);

        dis = new DataInputStream(my_client.getInputStream());
        s = dis.readUTF();
        ct.setVTD(s);

        dis = new DataInputStream(my_client.getInputStream());
        ct.setLuongMD(Float.parseFloat(dis.readUTF()));

        dsct.add(ct);

        File f = new File("/Users/trancaominhbach/Desktop/laptrinhmang_bart/TryHard_LTM/src/De7/info_CT.txt");
        FileWriter fw = new FileWriter(f, true);

        fw.write(ct.toString());
        fw.write("\n");
        fw.close();
    }

    public static void ReadFile() throws IOException {
        File f = new File("/Users/trancaominhbach/Desktop/laptrinhmang_bart/TryHard_LTM/src/De7/info_CT.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null) {
            Cauthu ct = new Cauthu();
            String[] result = line.split("\\$");

            ct.setMCT(result[0]);
            ct.setHT(result[1]);
            ct.setNS(result[2]);
            ct.setVTD(XD_VTD(result[3]));

            ct.setLuongMD(Float.parseFloat(result[4]));
            dsct.add(ct);
        }
        fr.close();
        br.close();
    }

    public static String XD_VTD(String a) {
        return switch (a) {
            case "1" -> "tien dao";
            case "2" -> "tien ve";
            case "3" -> "trung ve";
            case "4" -> "hau ve";
            case "5" -> "thu mon";
            default -> "";
        };
    }

    public static void Tinhluong(DataOutputStream dos, DataInputStream dis) throws IOException {
        //doc goi tin thong tin ma cau thu
        String str_rcv = dis.readUTF();

        float luongThucNhan = 0;
        float n = dis.readFloat();
        for (int i = 0; i < dsct.size(); i++) {
            if (dsct.get(i).getMCT().equalsIgnoreCase(str_rcv)){
                dos.writeUTF("Tim thay cau thu ma so "+str_rcv+"! \nNhap so luong tran da tham gia: ");
                n = dis.readFloat();
                luongThucNhan = Sum_Luong(n,dsct.get(i).getLuongMD(),Float.parseFloat(dsct.get(i).getVTD()));
            }
        }
        dos.writeFloat(luongThucNhan);
    }

    public static float Sum_Luong(float n,float lmd, float vtd){
        switch ((int) vtd){
            case 1:
                return (float) (lmd+n*lmd*(2.5/100));
            case 2:
            case 3:
                return lmd+n*lmd*(2/100);
            case 4:
                return (float) (lmd+n*lmd*(1.5/100));
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        ReadFile();
        ServerSocket server = new ServerSocket(2349);
        Socket my_client = server.accept();
        DataInputStream dis = new DataInputStream(my_client.getInputStream());
        DataOutputStream dos = new DataOutputStream(my_client.getOutputStream());

        while (true) {
            int n = dis.readInt();
            switch (n) {
                case 1:
                    n = dis.readInt();
                    System.out.println("Da chon: " + n);
                    int flag = NhapFile_n(n, my_client, dis, dos);
                    if (flag != 0) dos.writeUTF("Ghi thanh cong");
                    else dos.writeUTF("Ghi that bai!");
                    break;
                case 2:
                    n = dis.readInt();
                    System.out.println("Da chon: " + n);
                    break;
                case 3:
                    exit(0);
            }
        }
    }
}
