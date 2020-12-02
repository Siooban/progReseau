package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Msg extends Remote {
void message(String msg) throws RemoteException;

}
