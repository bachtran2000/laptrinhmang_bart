package Timtufile_UDP;

public class Nhanvien {
    protected String HT;

    public Nhanvien(String HT) {
        this.HT = HT;
    }

    public Nhanvien() {
    }

    public String getHT() {
        return HT;
    }

    public void setHT(String HT) {
        this.HT = HT;
    }

    @Override
    public String toString() {
        return "Nhanvien: " + HT;
    }
}
