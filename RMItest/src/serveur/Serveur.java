package serveur;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import client.Client;
import common.ServeurIntf;
public class Serveur extends UnicastRemoteObject implements ServeurIntf {
private static final long serialVersionUID=1L;
public String message;
private List<String> chatClients;

public Serveur() throws RemoteException{
	chatClients=new ArrayList<String>();
	
}
public String messageBienvenue() throws RemoteException{
return "Bienvenue";}


public static void main(String args[]) throws Exception{
	try {
		LocateRegistry.createRegistry(1099);
	}
	catch (RemoteException e) {}
	Serveur chatServeur=new Serveur();
	Naming.rebind("//localhost/Rmiserveur", chatServeur);
	System.out.println("serveur pret");
}



public void envoieMsg(String msg) throws RemoteException {
	this.chatClients.add(msg);
}
	

public List<String> renvoieList() throws RemoteException{
	return chatClients;
}

}




