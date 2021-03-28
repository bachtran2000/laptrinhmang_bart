package De2.Bai2;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client_QLSV {
    static ArrayList<Student> listST = new ArrayList<>();

    public static void menu(){
        System.out.println("1.Xem danh sach sinh vien");
        System.out.println("2.Them sinh vien moi");
        System.out.println("3.Xem thong tin sinh vien");
        System.out.println("4.Xem sinh vien theo nhom que hoac nam sinh");
        System.out.print("Chon: ");
    }

    public static void menu_2(){
        System.out.println("1. Theo que quan");
        System.out.println("2. Theo nam sinh");
        System.out.print("Chon: ");
    }

    public static void AddST(){
        Student st = new Student();
        System.out.print("Nhap ten: ");
        st.setHT(new Scanner(System.in).nextLine());
        System.out.print("Nhap Ngay sinh: ");
        st.setNS(new Scanner(System.in).nextLine());
        System.out.print("Nhap MSV: ");
        st.setMSV(new Scanner(System.in).nextLine());
        System.out.print("Nhap que quan: ");
        st.setQQ(new Scanner(System.in).nextLine());
        System.out.println(st.toString());
        listST.add(st);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket client = new Socket("127.0.0.1",2349);
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());

        while (true){
            menu();
            int n = new Scanner(System.in).nextInt();
            switch (n){
                case 1:
                    dos.writeInt(n);
                    listST = (ArrayList<Student>) ois.readObject();
                    for (int i = 0; i < listST.size(); i++) {
                        System.out.println(listST.get(i).toString());
                    }
                    break;
                case 2:
                    dos.writeInt(n);

                    System.out.print("Nhap so luong sinh vien can them: ");
                    n = new Scanner(System.in).nextInt();

                    for (int i = 0; i < n; i++) {
                        AddST();
                    }
                    oos.writeObject(listST);
                    break;
                case 3:
                    dos.writeInt(n);
                    break;
                case 4:
                    dos.writeInt(n);
                    exit(0);

            }
        }
    }
}
