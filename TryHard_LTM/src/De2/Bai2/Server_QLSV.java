package De2.Bai2;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Server_QLSV implements Serializable{
    static ArrayList<Student> listST = new ArrayList<>();

    public static void ReadFile(){
        try {
            File f = new File("/Users/trancaominhbach/Desktop/laptrinhmang_bart/TryHard_LTM/src/De2/Bai2/data_student.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine())!=null){
                Student st = new Student();
                String result[] = new String[4];
                result = line.split("\\$");
                st.setHT(result[0]);
                st.setNS(result[1]);
                st.setMSV(result[2]);
                st.setQQ(result[3]);
                listST.add(st);
            }
            fr.close();
            br.close();
            System.out.println("Doc file thanh cong!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Doc file khong thanh cong!");
        }
    }

    public static void Showdata(ObjectOutputStream oos) throws IOException {
        oos.writeObject(listST);
    }

    public static void AddST(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        listST = (ArrayList<Student>) ois.readObject();
        for (int i = 0; i < listST.size(); i++) {
            System.out.println(listST.get(i).toString());
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket server = new ServerSocket(2349);
        Socket my_client = server.accept();
        DataInputStream dis = new DataInputStream(my_client.getInputStream());
        DataOutputStream dos = new DataOutputStream(my_client.getOutputStream());
        ObjectOutputStream oos = new ObjectOutputStream(my_client.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(my_client.getInputStream());
        ReadFile();

        while (true) {
            int n = dis.readInt();
            switch (n){
                case 1:
                    Showdata(oos);
                    break;
                case 2:
                    AddST(ois);
                    System.out.println("Server da nhan!");
                    break;
                case 3:
                    break;
                case 4:
                    exit(0);
            }
        }
    }

}
