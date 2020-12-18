package Server;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.lang.*;

public class ThreadChat extends Thread {
	int id;
	BufferedReader in1;
	PrintWriter out1;
	Grille g1;
	BufferedReader in2;
	PrintWriter out2;
	Grille g2;
	test t;
	
	static PrintWriter[] outs= new PrintWriter[100];
	static int nbid=0;
	
	/*public ThreadChat(int id, Socket client) {
		try {
			this.id=id;
			nbid++;
			in=new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(),true);
			out.println("Id="+id+"\n");
			outs[id]=out;
			}
		catch (Exception e) {}
	}*/
	public ThreadChat(int id,Socket client1, Socket client2, Grille g1, Grille g2) {
		try {
			this.id=id;
			nbid++;
			in1= new BufferedReader(new InputStreamReader(client1.getInputStream()));
			out1 = new PrintWriter(client1.getOutputStream(),true);
			out1.println("Id="+id+"\n");
			outs[id]=out1;
			
			id++;
			in2= new BufferedReader(new InputStreamReader(client2.getInputStream()));
			out2 = new PrintWriter(client2.getOutputStream(),true);
			out2.println("Id="+id+"\n");
			outs[id]=out2;
		
			
			
		//initialisation de la grille
		this.g1= new Grille(1);
		
		
		
		//explication du code utilisé pour placer les bateaux
		//placement du porte avion bateau de taille 5
		out1.println("Décider de la position de votre porte avion");
		out1.println("5 cases de longueurs, choisissez si vous voulez le placer en ligne ou en colonne");
		out1.println("et la case de début de votre bateau");
		out1.println("par exemple pour placer le bateau sur la premiere ligne dès le début du tableau saisir '00l'");
		
		//procédure pour placer le porte avions
		boolean placementNonValide=true;
		String valeur=in1.readLine();
		String v= valeur.substring(2);
		int ligne=Character.getNumericValue(valeur.charAt(0));
		int colonne=Character.getNumericValue(valeur.charAt(1));
		
		if ( (v.equals("c"))&&(this.g1.VerifieC(5, colonne, ligne))&&(this.g1.VerifieP("l", colonne, ligne))&&(this.g1.VerifiePlacementC(5, colonne, ligne))) {
			placementNonValide=false;
		}		
		else if ((v.equals("l"))&&(this.g1.VerifieL(5, colonne, ligne))&&(this.g1.VerifieP("c", colonne, ligne))&&(this.g1.VerifiePlacementL(5,colonne,ligne))) {
			placementNonValide=false;
		}
		System.out.println(placementNonValide);
		while(placementNonValide) {
			out1.println("erreur dans la saisie,veuillez recommencer");
			valeur=in1.readLine();
			v= valeur.substring(2);
			ligne=Character.getNumericValue(valeur.charAt(0));
			colonne=Character.getNumericValue(valeur.charAt(1));
			if ( (v.equals("c"))&&(this.g1.VerifieC(5, colonne, ligne))&&(this.g1.VerifieP("l", colonne, ligne))&&(this.g1.VerifiePlacementC(5,colonne,ligne)) ){
				placementNonValide=false;
			}		
			else if ((v.equals("l"))&&(this.g1.VerifieL(5, colonne, ligne))&&(this.g1.VerifieP("c", colonne, ligne))&&(this.g1.VerifiePlacementL(5,colonne,ligne))) {
				placementNonValide=false;
			}
			}
		
		if(v.equals("l")) {this.g1.placeL(5,colonne, ligne);}
		else {this.g1.placeC(5,colonne,ligne);}
		
		//placement du croiseur, 4 
		out1.println("Décider de la position de votre croiseur, 4 cases de longueur");
		placementNonValide=true;
		out1.println("Veuillez indiquer la position de votre croiseur conformément à la notation précédente");
		valeur=in1.readLine();
		v= valeur.substring(2);
		ligne=Character.getNumericValue(valeur.charAt(0));
		colonne=Character.getNumericValue(valeur.charAt(1));
		
		
		if ( (v.equals("c"))&&(this.g1.VerifieC(5, colonne, ligne))&&(this.g1.VerifieP("l", colonne, ligne))&&(this.g1.VerifiePlacementC(4,colonne,ligne)) ){
			placementNonValide=false;
		}		
		else if ((v.equals("l"))&&(this.g1.VerifieL(5, colonne, ligne))&&(this.g1.VerifieP("c", colonne, ligne))&&(this.g1.VerifiePlacementL(4,colonne,ligne))) {
			placementNonValide=false;
		}
		System.out.println(placementNonValide);
		while(placementNonValide) {
			out1.println("erreur dans la saisie,veuillez recommencer");
			valeur=in1.readLine();
			v= valeur.substring(2);
			ligne=Character.getNumericValue(valeur.charAt(0));
			colonne=Character.getNumericValue(valeur.charAt(1));
			if ( (v.equals("c"))&&(this.g1.VerifieC(4, colonne, ligne))&&(this.g1.VerifieP("l", colonne, ligne))&&(this.g1.VerifiePlacementC(4,colonne,ligne)) ){
				placementNonValide=false;
			}		
			else if ((v.equals("l"))&&(this.g1.VerifieL(4, colonne, ligne))&&(this.g1.VerifieP("c", colonne, ligne))&&(this.g1.VerifiePlacementL(4,colonne,ligne)) ){
				placementNonValide=false;
			}
			}
		
		if(v.equals("l")) {this.g1.placeL(4,colonne, ligne);}
		else {this.g1.placeC(4,colonne,ligne);}
		
		//placement du contre-torpilleur 3 cases
		out1.println("Décider de la position de votre contre torpilleur, 3 cases de longueur");
		placementNonValide=true;
		out1.println("Veuillez indiquer la position de votre contre torpilleur conformément à la notation précédente");
		valeur=in1.readLine();
		v= valeur.substring(2);
		ligne=Character.getNumericValue(valeur.charAt(0));
		colonne=Character.getNumericValue(valeur.charAt(1));
		
		
		if ( (v.equals("c"))&&(this.g1.VerifieC(3, colonne, ligne))&&(this.g1.VerifieP("l", colonne, ligne))&&(this.g1.VerifiePlacementC(3,colonne,ligne)) ){
			placementNonValide=false;
		}		
		else if ((v.equals("l"))&&(this.g1.VerifieL(3, colonne, ligne))&&(this.g1.VerifieP("c", colonne, ligne))&&(this.g1.VerifiePlacementL(3,colonne,ligne))) {
			placementNonValide=false;
		}
		System.out.println(placementNonValide);
		while(placementNonValide) {
			out1.println("erreur dans la saisie,veuillez recommencer");
			valeur=in1.readLine();
			v= valeur.substring(2);
			ligne=Character.getNumericValue(valeur.charAt(0));
			colonne=Character.getNumericValue(valeur.charAt(1));
			if ( (v.equals("c"))&&(this.g1.VerifieC(3, colonne, ligne))&&(this.g1.VerifieP("l", colonne, ligne))&&(this.g1.VerifiePlacementC(3,colonne,ligne)) ){
				placementNonValide=false;
			}		
			else if ((v.equals("l"))&&(this.g1.VerifieL(3, colonne, ligne))&&(this.g1.VerifieP("c", colonne, ligne))&&(this.g1.VerifiePlacementL(3,colonne,ligne))) {
				placementNonValide=false;
			}
			}
		
		if(v.equals("l")) {this.g1.placeL(3,colonne, ligne);}
		else {this.g1.placeC(3,colonne,ligne);}
		
		
		
		//placement du sous marin 3 cases
		out1.println("Décider de la position de votre contre sous marins, 3 case ");
		
		placementNonValide=true;
		out1.println("Veuillez indiquer la position de votre sous marins conformément à la notation précédente");
		valeur=in1.readLine();
		v= valeur.substring(2);
		ligne=Character.getNumericValue(valeur.charAt(0));
		colonne=Character.getNumericValue(valeur.charAt(1));
		
		
		if ( (v.equals("c"))&&(this.g1.VerifieC(3, colonne, ligne))&&(this.g1.VerifieP("l", colonne, ligne))&&(this.g1.VerifiePlacementC(3,colonne,ligne)) ){
			placementNonValide=false;
		}		
		else if ((v.equals("l"))&&(this.g1.VerifieL(3, colonne, ligne))&&(this.g1.VerifieP("c", colonne, ligne))&&(this.g1.VerifiePlacementL(3,colonne,ligne)) ) {
			placementNonValide=false;
		}
		System.out.println(placementNonValide);
		while(placementNonValide) {
			out1.println("erreur dans la saisie,veuillez recommencer");
			valeur=in1.readLine();
			v= valeur.substring(2);
			ligne=Character.getNumericValue(valeur.charAt(0));
			colonne=Character.getNumericValue(valeur.charAt(1));
			if ( (v.equals("c"))&&(this.g1.VerifieC(3, colonne, ligne))&&(this.g1.VerifieP("l", colonne, ligne))&&(this.g1.VerifiePlacementC(3,colonne,ligne)) ){
				placementNonValide=false;
			}		
			else if ((v.equals("l"))&&(this.g1.VerifieL(3, colonne, ligne))&&(this.g1.VerifieP("c", colonne, ligne))&&(this.g1.VerifiePlacementL(3,colonne,ligne))) {
				placementNonValide=false;
			}
			}
		
		if(v.equals("l")) {this.g1.placeL(3,colonne, ligne);}
		else {this.g1.placeC(3,colonne,ligne);}
		
		//placement du torpilleur, 2 cases
out1.println("Décider du placement de votre torpilleur, 2 case ");
		
		placementNonValide=true;
		out1.println("Veuillez indiquer la position de votre torpilleur conformément à la notation précédente");
		valeur=in1.readLine();
		v= valeur.substring(2);
		ligne=Character.getNumericValue(valeur.charAt(0));
		colonne=Character.getNumericValue(valeur.charAt(1));
		
		
		if ( (v.equals("c"))&&(this.g1.VerifieC(2, colonne, ligne))&&(this.g1.VerifieP("l", colonne, ligne))&&(this.g1.VerifiePlacementC(2,colonne,ligne)) ){
			placementNonValide=false;
		}		
		else if ((v.equals("l"))&&(this.g1.VerifieL(2, colonne, ligne))&&(this.g1.VerifieP("c", colonne, ligne))&&(this.g1.VerifiePlacementL(2,colonne,ligne))) {
			placementNonValide=false;
		}
		System.out.println(placementNonValide);
		while(placementNonValide) {
			out1.println("erreur dans la saisie,veuillez recommencer");
			valeur=in1.readLine();
			v= valeur.substring(2);
			ligne=Character.getNumericValue(valeur.charAt(0));
			colonne=Character.getNumericValue(valeur.charAt(1));
			if ( (v.equals("c"))&&(this.g1.VerifieC(2, colonne, ligne))&&(this.g1.VerifieP("l", colonne, ligne))&&(this.g1.VerifiePlacementC(2,colonne,ligne)) ){
				placementNonValide=false;
			}		
			else if ((v.equals("l"))&&(this.g1.VerifieL(2, colonne, ligne))&&(this.g1.VerifieP("c", colonne, ligne))&&(this.g1.VerifiePlacementL(2,colonne,ligne))) {
				placementNonValide=false;
			}
			}
		
		if(v.equals("l")) {this.g1.placeL(2,colonne, ligne);}
		else {this.g1.placeC(2,colonne,ligne);}
		
		
	//avec cette méthode la règle disant que les bateaux ne peuvent pas se toucher n'est pas verifié, il s'agit d'ajouter une méthode
		//supplémentaire qui permet de verifier si le placement est bon
		
		
	
		
		
		
		
		
		
		this.g2= new Grille(2);
		//placement des bateaux du second joueurs
		//explication du code utilisé pour placer les bateaux
				//placement du porte avion bateau de taille 5
				out2.println("Décider de la position de votre porte avion");
				out2.println("5 cases de longueurs, choisissez si vous voulez le placer en ligne ou en colonne");
				out2.println("et la case de début de votre bateau");
				out2.println("par exemple pour placer le bateau sur la premiere ligne dès le début du tableau saisir '00l'");
				
				//procédure pour placer le porte avions
				placementNonValide=true;
				valeur=in2.readLine();
				v= valeur.substring(2);
				ligne=Character.getNumericValue(valeur.charAt(0));
				colonne=Character.getNumericValue(valeur.charAt(1));
				
				if ( (v.equals("c"))&&(this.g2.VerifieC(5, colonne, ligne))&&(this.g2.VerifieP("l", colonne, ligne))&&(this.g2.VerifiePlacementC(5,colonne,ligne)) ){
					placementNonValide=false;
				}		
				else if ((v.equals("l"))&&(this.g2.VerifieL(5, colonne, ligne))&&(this.g2.VerifieP("c", colonne, ligne))&&(this.g2.VerifiePlacementL(5,colonne,ligne))) {
					placementNonValide=false;
				}
				System.out.println(placementNonValide);
				while(placementNonValide) {
					out2.println("erreur dans la saisie,veuillez recommencer");
					valeur=in2.readLine();
					v= valeur.substring(2);
					ligne=Character.getNumericValue(valeur.charAt(0));
					colonne=Character.getNumericValue(valeur.charAt(1));
					if ( (v.equals("c"))&&(this.g2.VerifieC(5, colonne, ligne))&&(this.g2.VerifieP("l", colonne, ligne))&&(this.g2.VerifiePlacementC(5,colonne,ligne)) ){
						placementNonValide=false;
					}		
					else if ((v.equals("l"))&&(this.g2.VerifieL(5, colonne, ligne))&&(this.g2.VerifieP("c", colonne, ligne))&&(this.g2.VerifiePlacementL(5,colonne,ligne))) {
						placementNonValide=false;
					}
					}
				
				if(v.equals("l")) {this.g2.placeL(5,colonne, ligne);}
				else {this.g2.placeC(5,colonne,ligne);}
				
				//placement du croiseur, 4 
				out2.println("Décider de la position de votre croiseur, 4 cases de longueur");
				placementNonValide=true;
				out2.println("Veuillez indiquer la position de votre croiseur conformément à la notation précédente");
				valeur=in2.readLine();
				v= valeur.substring(2);
				ligne=Character.getNumericValue(valeur.charAt(0));
				colonne=Character.getNumericValue(valeur.charAt(1));
				
				
				if ( (v.equals("c"))&&(this.g2.VerifieC(5, colonne, ligne))&&(this.g2.VerifieP("l", colonne, ligne))&&(this.g2.VerifiePlacementC(4,colonne,ligne)) ){
					placementNonValide=false;
				}		
				else if ((v.equals("l"))&&(this.g2.VerifieL(5, colonne, ligne))&&(this.g2.VerifieP("c", colonne, ligne))&&(this.g2.VerifiePlacementL(4,colonne,ligne))) {
					placementNonValide=false;
				}
				System.out.println(placementNonValide);
				while(placementNonValide) {
					out2.println("erreur dans la saisie,veuillez recommencer");
					valeur=in2.readLine();
					v= valeur.substring(2);
					ligne=Character.getNumericValue(valeur.charAt(0));
					colonne=Character.getNumericValue(valeur.charAt(1));
					if ( (v.equals("c"))&&(this.g2.VerifieC(4, colonne, ligne))&&(this.g2.VerifieP("l", colonne, ligne))&&(this.g2.VerifiePlacementC(4,colonne,ligne))){
						placementNonValide=false;
					}		
					else if ((v.equals("l"))&(this.g2.VerifieL(4, colonne, ligne))&(this.g2.VerifieP("c", colonne, ligne))&&(this.g2.VerifiePlacementL(4,colonne,ligne))) {
						placementNonValide=false;
					}
					}
				
				if(v.equals("l")) {this.g2.placeL(4,colonne, ligne);}
				else {this.g2.placeC(4,colonne,ligne);}
				
				//placement du contre-torpilleur 3 cases
				out2.println("Décider de la position de votre contre torpilleur, 3 cases de longueur");
				placementNonValide=true;
				out2.println("Veuillez indiquer la position de votre contre torpilleur conformément à la notation précédente");
				valeur=in2.readLine();
				v= valeur.substring(2);
				ligne=Character.getNumericValue(valeur.charAt(0));
				colonne=Character.getNumericValue(valeur.charAt(1));
				
				
				if ( (v.equals("c"))&&(this.g2.VerifieC(3, colonne, ligne))&&(this.g2.VerifieP("l", colonne, ligne))&&(this.g2.VerifiePlacementC(3,colonne,ligne))) {
					placementNonValide=false;
				}		
				else if ((v.equals("l"))&&(this.g2.VerifieL(3, colonne, ligne))&&(this.g2.VerifieP("c", colonne, ligne))&&(this.g2.VerifiePlacementL(3,colonne,ligne))) {
					placementNonValide=false;
				}
				System.out.println(placementNonValide);
				while(placementNonValide) {
					out2.println("erreur dans la saisie,veuillez recommencer");
					valeur=in2.readLine();
					v= valeur.substring(2);
					colonne=Character.getNumericValue(valeur.charAt(0));
					ligne=Character.getNumericValue(valeur.charAt(1));
					if ( (v.equals("c"))&&(this.g2.VerifieC(3, colonne, ligne))&&(this.g2.VerifieP("l", colonne, ligne))&& (this.g2.VerifiePlacementC(3,colonne,ligne))){
						placementNonValide=false;
					}		
					else if ((v.equals("l"))&&(this.g2.VerifieL(3, colonne, ligne))&&(this.g2.VerifieP("c", colonne, ligne))&&(this.g2.VerifiePlacementL(3,colonne,ligne))) {
						placementNonValide=false;
					}
					}
				
				if(v.equals("l")) {this.g2.placeL(3,colonne, ligne);}
				else {this.g2.placeC(3,colonne,ligne);}
				
				
				
				//placement du sous marin 3 cases
				out2.println("Décider de la position de votre contre sous marin, 3 case ");
				
				placementNonValide=true;
				out2.println("Veuillez indiquer la position de votre sous marin conformément à la notation précédente");
				valeur=in2.readLine();
				v= valeur.substring(2);
				ligne=Character.getNumericValue(valeur.charAt(0));
				colonne=Character.getNumericValue(valeur.charAt(1));
				
				
				if ( (v.equals("c"))&&(this.g2.VerifieC(3, colonne, ligne))&&(this.g2.VerifieP("l", colonne, ligne))&&(this.g2.VerifiePlacementC(3,colonne,ligne)) ){
					placementNonValide=false;
				}		
				else if ((v.equals("l"))&(this.g2.VerifieL(3, colonne, ligne))&(this.g2.VerifieP("c", colonne, ligne))&&(this.g2.VerifiePlacementL(3,colonne,ligne))) {
					placementNonValide=false;
				}
				System.out.println(placementNonValide);
				while(placementNonValide) {
					out2.println("erreur dans la saisie,veuillez recommencer");
					valeur=in2.readLine();
					v= valeur.substring(2);
					ligne=Character.getNumericValue(valeur.charAt(0));
					colonne=Character.getNumericValue(valeur.charAt(1));
					if ( (v.equals("c"))&&(this.g2.VerifieC(3, colonne, ligne))&&(this.g2.VerifieP("l", colonne, ligne))&&(this.g2.VerifiePlacementC(3,colonne,ligne)) ){
						placementNonValide=false;
					}		
					else if ((v.equals("l"))&&(this.g2.VerifieL(3, colonne, ligne))&&(this.g2.VerifieP("c", colonne, ligne))&&(this.g2.VerifiePlacementL(3,colonne,ligne))) {
						placementNonValide=false;
					}
					}
				
				if(v.equals("l")) {this.g2.placeL(3,colonne, ligne);}
				else {this.g2.placeC(3,colonne,ligne);}
				
				//placement du torpilleur, 2 cases
		out2.println("Décider du placement de votre torpilleur, 2 case ");
				
				placementNonValide=true;
				out2.println("Veuillez indiquer la position de votre torpilleur conformément à la notation précédente");
				valeur=in2.readLine();
				v= valeur.substring(2);
				ligne=Character.getNumericValue(valeur.charAt(0));
				colonne=Character.getNumericValue(valeur.charAt(1));
				
				
				if ( (v.equals("c"))&&(this.g2.VerifieC(2, colonne, ligne))&&(this.g2.VerifieP("l", colonne, ligne))&&(this.g2.VerifiePlacementC(2,colonne,ligne)) ){
					placementNonValide=false;
				}		
				else if ((v.equals("l"))&(this.g2.VerifieL(2, colonne, ligne))&(this.g2.VerifieP("c", colonne, ligne))&&(this.g2.VerifiePlacementL(2,colonne,ligne))) {
					placementNonValide=false;
				}
				System.out.println(placementNonValide);
				while(placementNonValide) {
					out2.println("erreur dans la saisie,veuillez recommencer");
					valeur=in2.readLine();
					v= valeur.substring(2);
					ligne=Character.getNumericValue(valeur.charAt(0));
					colonne=Character.getNumericValue(valeur.charAt(1));
					if ( (v.equals("c"))&(this.g2.VerifieC(2, colonne, ligne))&(this.g2.VerifieP("l", colonne, ligne))&&(this.g2.VerifiePlacementC(2,colonne,ligne)) ){
						placementNonValide=false;
					}		
					else if ((v.equals("l"))&(this.g2.VerifieL(2, colonne, ligne))&(this.g2.VerifieP("c", colonne, ligne))&&(this.g2.VerifiePlacementL(2,colonne,ligne))) {
						placementNonValide=false;
					}
					}
				
				if(v.equals("l")) {this.g2.placeL(2,colonne, ligne);}
				else {this.g2.placeC(2,colonne,ligne);}
		
		
		
		
		
		
		
		}
		catch(Exception e) {}
	}


public void run() {
	boolean condition=true;
	try {	

		while (condition) {
			
			String resultat=g1.afficher();
			String resultat2=g2.afficher();
			out1.println("votre plateau de jeu: "+"\n" + resultat);
			out2.println("votre plateau de jeu "+"\n"+resultat2);
			// affichage du plateau de jeu du joueur
			
			
			
			
			
			/*this.t=new test();
			System.out.println(t.Renvoie());*/  
			
			
			System.out.println("tour joueur 1");
			out1.println("votre tour, veuillez saisir le numéro de ligne puis le numero de colonnne (ex23)");
			String message1=in1.readLine();
			//System.out.println(message1);
			
			
			
			// boucle pour vérifier que l'utilisateur ne rentre pas n'importe quoi
			int[] coup1= {Character.getNumericValue(message1.charAt(0)),Character.getNumericValue(message1.charAt(1))};
		
			while(coup1[0]>9 | coup1[0]<0 | coup1[1]>9 |coup1[1]<0) {
				out1.println("votre tour, erreur de saisit");
				message1=in1.readLine();
				coup1[0]= Character.getNumericValue(message1.charAt(0));
				coup1[1]=Character.getNumericValue(message1.charAt(1));
			}
			
			/*System.out.println(coup1[0]);
			System.out.println(coup1[1]);*/ // test pour vérifier les valeurs des coups
			
			//System.out.println(this.t.Renvoie());
			
		
			
			
			//on joue le coup
			String valeur =g2.joue(coup1);
			out1.println(valeur);
			
			
			System.out.println("coup joué");
			out1.println("coup joué");
			
			
			//System.out.println(this.g2.test());
			
			//on verifie si le coup marque une fin de partie
			if (g2.check()) {
				System.out.println("le joueur 1 est vainqueur");
				out1.println("victoire");
				out2.println("défaite");
				break;
			}
			
			
			//System.out.println(message1);*/
			
			//affichage du plateau du second joueur
			//String resultat2=g2.afficher();
			//out2.println("votre plateau de jeu: "+"\n" + resultat2);
			
			
			
			//debut du tour du second joueur
			System.out.println("tour joueur 2");
			out2.println("votre tour, veuillez saisir le numéro de ligne puis le numero de colonnne (ex23)");
			String message2=in2.readLine();
			
			int[] coup2= {Character.getNumericValue(message2.charAt(0)), Character.getNumericValue(message2.charAt(1))};// attention ici on ne vérifie pas que l'utilisateur fait 
			//attention au format il faudra donc ajouter des conditions pour vérifié que le joueur n'a pas ecrit n'importe quoi
			/*this.g1.joue(coup2);// on joue le coup sur la grille2
			if (this.g1.check()) {
				System.out.println("le joueur 2 est vainqueur");
				break;
			}*/
			while(coup2[0]>9 | coup2[0]<0 | coup2[1]>9 |coup2[1]<0) {
				out2.println("votre tour, erreur de saisit");
				message2=in1.readLine();
				coup2[0]= Character.getNumericValue(message2.charAt(0));
				coup2[1]=Character.getNumericValue(message2.charAt(1));
			}
			
			/*System.out.println(coup2[0]);
			System.out.println(coup2[1]);*/
			//g1.afficher();
			String valeur2=g1.joue(coup2);
			out2.println(valeur2);
			System.out.println("coup joué");
			out2.println("coup joué");
			//on vérifie si le coup marque une fin de partie
			if (g1.check()) {
				System.out.println("le joueur 2 est vainqueur");
				out2.println("victoire");
				out1.println("défaite");
				break;}
			/*System.out.println("tour joueur 2");
			String message2=in2.readLine();
			message2=id+":"+message2;
			System.out.println(message2);*/
			/*for (int i=0;i<nbid;i++) {
				if (i!=id)outs[i].println(message);
			}*/
		}
		}catch (Exception e) {}
	}
	
}


//!( (this.g1.verifie() || this.g2.verifie())))