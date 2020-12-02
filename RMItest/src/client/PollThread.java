package client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.Scanner;

import common.ServeurIntf;

public class PollThread extends Thread{
	
	private Client c;
	private ServeurIntf Serveur;
	public int compteur;
	public PollThread(Client c, ServeurIntf Serveur) throws Exception{
	this.c=c; 
	this.Serveur=Serveur;
	
	}

	

	public void run() {
		this.c.afficher("début du chat");
		try {
			
			for(String message: Serveur.renvoieList()) {c.renvoieListe().add(message);}
	}catch (RemoteException e) {e.printStackTrace();}
		
		while(true) {
			try {
				/*on récupère le message du client, on le transmet à la liste de message du serveur
				*/
				String msg = this.c.entrer();
				if(!(msg.equals("quit"))) {
					Serveur.envoieMsg(msg);
				}
				else {
					this.c.stop();}
				
				
				//on compare la taille de la liste de message serveur et la taille de la liste de message client
				//si il ya une différence de taille, on affiche les messages du serveurs à partir de message manquant
			
				if(c.renvoieListe().size()<Serveur.renvoieList().size()) {
					for(int i = c.renvoieListe().size(); i<Serveur.renvoieList().size(); i++) {
						this.c.afficher(Serveur.renvoieList().get(i));
						this.c.renvoieListe().add(Serveur.renvoieList().get(i));
					}
				}
				/*String msg = this.c.entrer();
				if(!(msg.equals("quit"))) {
					Serveur.sendMessage(msg);
				}
				else {
					this.c.stop();}*/
			} catch (RemoteException e) {
				e.printStackTrace();
		}
			
	
	}
		}}
	
