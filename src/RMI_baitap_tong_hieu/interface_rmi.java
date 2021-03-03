package RMI_baitap_tong_hieu;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interface_rmi extends Remote {
    public  int tong(int a, int b) throws RemoteException, Exception;
    public  int hieu(int a, int b) throws RemoteException, Exception;
}
