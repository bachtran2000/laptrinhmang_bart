package De2.Bai2;

import java.io.Serializable;

public class Student implements Serializable {
    protected String HT, NS, MSV,QQ;

    public Student() {
    }

    @Override
    public String toString() {
        return "HT: " + HT + '\'' +
                ", NS: " + NS + '\'' +
                ", MSV: " + MSV + '\'' +
                ", QQ: " + QQ;
    }

    public String getHT() {
        return HT;
    }

    public void setHT(String HT) {
        this.HT = HT;
    }

    public String getNS() {
        return NS;
    }

    public void setNS(String NS) {
        this.NS = NS;
    }

    public String getMSV() {
        return MSV;
    }

    public void setMSV(String MSV) {
        this.MSV = MSV;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }
}
