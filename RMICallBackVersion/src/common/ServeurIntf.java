package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServeurIntf extends Remote{

	
	public String messageBienvenue() throws RemoteException;
	void ajouteMessage(Msg Msg) throws RemoteException;
	void envoieMessage(String message) throws RemoteException;
}
