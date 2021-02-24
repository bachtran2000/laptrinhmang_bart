package TCP_check_SNT;

import java.util.Scanner;

public class test {
    static String ssnt="";
    public static boolean checknt(int a){
        if (a <= 1) return false;
        else{
            for (int i = 2; i < a ; i++) {
                if (a % i == 0) return false;
            }
        }
        return true;
    }

    public static String  List_SNT(int a) {
        for (int i = 0; i < a; i++) {
            if (checknt(i))
                ssnt = ssnt+" "+i;
        }
        return ssnt;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 11;
        if (checknt(n))
            System.out.println("SNT");
        else System.out.println("Non");
        System.out.println(List_SNT(n));
    }
}
