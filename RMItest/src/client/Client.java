package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.ServeurIntf;

public class Client {
	
public List<String> messagesClient=new ArrayList<>();
public Scanner sc=new Scanner(System.in);

public Client() throws RemoteException{}

public void afficher(String message)
{System.out.println(message);
}

public String entrer() {
	return sc.nextLine();
}
public List<String> renvoieListe(){
	return messagesClient;
}
public void stop() {
	System.exit(0);
}


public static void main(String args[]) {
	try {
	ServeurIntf Serveur=(ServeurIntf)Naming.lookup("//localhost/Rmiserveur");
	
	
		Serveur.messageBienvenue();
		Client c=new Client();
		
		new PollThread(c, Serveur).start();

	
	}catch(Exception e) {
		e.printStackTrace();}
	}
	
}
