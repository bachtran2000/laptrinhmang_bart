package QLNH;

public class User {
    protected String name,hisTrad,pass;
    protected int balance;

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHisTrad() {
        return hisTrad;
    }

    public void setHisTrad(String hisTrad) {
        this.hisTrad = hisTrad;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public User() {
    }

    @Override
    public String toString() {
        return name+"$"+pass+"$"+balance+"$"+hisTrad;
    }
}
