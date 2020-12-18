package Server;

import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
	public static void main(String[] args) {
		
	
	try {
		ServerSocket ecoute = new ServerSocket(1500);
		System.out.println("Serveur lancé!");
		int id=0;	
		while(true) {
		Socket client1 = ecoute.accept();
		Grille grille1= new Grille(1);
		Socket client2 = ecoute.accept();
		Grille grille2=new Grille(2);
		new ThreadChat(id,client1,client2, grille1, grille2).start();
		id++;
		}
		} catch(Exception e) {
		// Traitement d�erreur
		}

}

}
