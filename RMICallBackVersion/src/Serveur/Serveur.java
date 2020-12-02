package Serveur;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.Msg;
import common.ServeurIntf;


public class Serveur extends UnicastRemoteObject implements ServeurIntf{
private static final long serialVersionUID = 1L;
//on a une liste de client coté Serveur
private ArrayList<Msg> clients= new ArrayList<>();
public Serveur() throws RemoteException{
	super(0);
}

public String messageBienvenue() throws RemoteException{
return "Bienvenue";}

public static void main(String args[]) throws Exception {
	
	try {
		LocateRegistry.createRegistry(1099);}
	catch(RemoteException e) {}
		
		Serveur serveur = new Serveur();
		Naming.rebind("//localhost/RmiServer", serveur);
		
		System.out.println("Serveur pret");
	
	}

@Override
public void ajouteMessage(Msg Msg) throws RemoteException {
clients.add(Msg);
	
}

@Override
public void envoieMessage(String test) throws RemoteException {
	//tant qu'il ya des clients dans la liste on envoie le message, broadcast
	for(Msg client: clients) {
		try {
			client.message(test);
		} catch(RemoteException e) {
			e.printStackTrace();
		}
	}
	
}
}
