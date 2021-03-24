package De7.Bai2;

public class Cauthu {
    protected String MCT, HT, NS, VTD;
    protected float LuongMD;

    public Cauthu() {
    }

    public Cauthu(String MCT, String HT, String NS, String VTD, float luongMD) {
        this.MCT = MCT;
        this.HT = HT;
        this.NS = NS;
        this.VTD = VTD;
        LuongMD = luongMD;
    }

    public String getMCT() {
        return MCT;
    }

    public void setMCT(String MCT) {
        this.MCT = MCT;
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

    public String getVTD() {
        return VTD;
    }

    public void setVTD(String VTD) {
        this.VTD = VTD;
    }

    public float getLuongMD() {
        return LuongMD;
    }

    public void setLuongMD(float luongMD) {
        LuongMD = luongMD;
    }

    @Override
    public String toString() {
        return MCT+"$"+HT+"$"+NS+"$"+VTD+"$"+LuongMD;
    }
}
