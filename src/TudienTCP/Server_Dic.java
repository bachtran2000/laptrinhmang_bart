package TudienTCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Server_Dic {

    static ArrayList<Diction> lsDic = new ArrayList<>();

    public static void ReadFile() throws IOException {
        try {
            File f = new File("/Users/trancaominhbach/Desktop/laptrinhmang_bart/src/TudienTCP/Dictionary.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                Diction dt = new Diction();
                String[] result = line.split("\\$");
                dt.setTV(result[0]);
                dt.setTA(result[1]);
                lsDic.add(dt);
            }
            fr.close();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void ENtoVN(DataInputStream dis, DataOutputStream dos) throws IOException {
        String str_receive = dis.readUTF();
        int flag = 0;
        String result[] = new String[10];

        for (int i = 0, j=0; i < lsDic.size(); i++) {
            if (lsDic.get(i).getTA().equalsIgnoreCase(str_receive)) {
                result[j] = lsDic.get(i).getTV();
                j++;
                flag = flag + 1;
            }
        }

        dos.writeInt(flag);

        if (flag > 0)
            for (int i = 0; i < flag; i++) {
                dos.writeUTF(result[i]);
                System.out.println(result[i]);
            }
        else dos.writeUTF("Khong tim thay tu can tim!");
    }

    public static void VNtoEN(DataInputStream dis, DataOutputStream dos) throws IOException {
        String str_receive = dis.readUTF();
        int flag = 0;
        String result[] = new String[10];

        for (int i = 0,j=0; i < lsDic.size(); i++) {
            if (lsDic.get(i).getTV().equals(str_receive)) {
                result[j] = lsDic.get(i).getTA();
                flag++;
                j++;
            }
        }

        dos.writeInt(flag);

        if (flag > 0)
            for (int i = 0; i < flag; i++) {
                dos.writeUTF(result[i]);
                System.out.println(result[i]);
            }
        else dos.writeUTF("Khong tim thay tu can tim!");
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2349);
        Socket my_client = server.accept();
        DataInputStream dis = new DataInputStream(my_client.getInputStream());
        DataOutputStream dos = new DataOutputStream(my_client.getOutputStream());

        ReadFile();

        while (true) {
            int n = dis.readInt();
            switch (n) {
                case 1:
                    ENtoVN(dis, dos);
                    break;
                case 2:
                    VNtoEN(dis, dos);
                    break;
                case 3:
                    exit(0);
                    break;
            }
        }
    }
}
