package Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import common.Msg;
import common.ServeurIntf;


public class Client extends UnicastRemoteObject implements Msg{
	private static final long serialVersionUID=1L;
	
	
	
	
	
	public Client() throws RemoteException{
		
	}
		//Serveur=(ServeurIntf)Naming.lookup("//localhost/RmiServer");

		
public static void main(String args[]) {
	try {
		ServeurIntf Serveur=(ServeurIntf)Naming.lookup("//localhost/RmiServer");
		System.out.println(Serveur.messageBienvenue());
	
		
	
	
	Client client=new Client();
	Serveur.ajouteMessage(client);
	Scanner sc1=new Scanner(System.in);

	
	
	
	String valeur="";
	//tant que le client en demande pas de quitter le chat, on continue
	while(!valeur.equals("quit")) {
		//le message que le client écrit
		valeur=sc1.nextLine();
		if(!valeur.equals("")) {
		Serveur.envoieMessage(valeur);}
	}
	sc1.close();} 
	catch (MalformedURLException | RemoteException | NotBoundException e) {
	e.printStackTrace();}
	
}
@Override
public void message(String msg) throws RemoteException {
	System.out.println(msg);
	
}

}
