package Server;

public class Grille {
public int [][] tableau;
public int etat; //0 pour en cours 1 pour terminer
public int joueur;


public Grille(int joueur) {
this.tableau= new int[10][10];
this.etat=0;
this.joueur=joueur;
for(int i=0;i<10;i++) {// initialiser la grille de jeu en plaçant les bateaux, 0 pour une case vide, 1 pour une case pleine
 for (int j=0;j<10;j++) {
	 tableau[i][j]=0;
 }
}


}

public void placeC(int bateau, int colonne, int ligne) {// méthode pour placer un bateau sur une colonne, on ne gère pas les exeptions on considère que  cet méthode est appelée proprement
	
	for(int i=ligne; i<bateau+ligne;i++) {tableau[i][colonne]=1;}
}
//méthode qui verifie qu'on a bien la place de placer le bateau dans la colonne choisit
public boolean VerifieC(int bateau, int colonne, int ligne) {
	if (ligne+bateau>=10) {return false;}
	else {return true;}
	
};

public void placeL(int bateau, int colonne, int ligne ) {//méthode pour placer un bateau sur une ligne
	for (int j=colonne;j<bateau+colonne;j++) {
		this.tableau[ligne][j]=1;
	}
}
//méthode qui verifie qu'on a bien la place de placer le bateau dans la colonne choisit
public boolean VerifieL(int bateau, int colonne, int ligne) {
	if (colonne+bateau>=10) {return false;}
	else return true;
}
//méthode qui verifie que l'emplacement demandé pour placer
//un bateau en colonne est bien disponible, cette méthode
//ne vérifie pas que le placement est valide

public boolean VerifiePlacementC(int bateau, int colonne, int ligne) {
	
	if (colonne-1>=0) {
		int i=1;
		while((i<bateau)&&(ligne+i<10)) {
			if(this.tableau[ligne+i][colonne-1]==1) {return false;}
			i++;
		}
	}
	if(colonne+1<10) {
		int j=1;
		while((j<bateau)&&(ligne+j<10)) {
			if(this.tableau[ligne+j][colonne+1]==1) {return false;}
			j++;
		}
	}
	if((ligne-1>0) && (this.tableau[ligne-1][colonne]==1)) {return false;}
	if((ligne+bateau<10) && (this.tableau[ligne+bateau][colonne]==1)) {return false;}
	return true;
}



//methode qui verifie qu'il n'y pas de bateau à proximité du placement demandé
//pour un bateau placé en ligne
public boolean VerifiePlacementL(int bateau, int colonne, int ligne) {
	
	if (ligne-1>=0) {
		int i=1;
		while((i<bateau)&&(colonne+i<10)) {
			if(this.tableau[ligne-1][colonne+i]==1) {return false;}
			i++;
		}
	}
	if(ligne+1<10) {
		int j=1;
		while((j<bateau)&&colonne+j<10) {
			if(this.tableau[ligne+1][colonne+j]==1) {return false;}
			j++;
		}
	}
	if((colonne-1>=0) && (this.tableau[ligne][colonne-1]==1)) {return false;}
	if((colonne+bateau<10) && (this.tableau[ligne][colonne+bateau]==1)) {return false;}
	return true;
}

//méthode qui verifie l'emplacement demandé pour placer un bateau est bien disponible 
public boolean VerifieP(String indice, int colonne, int ligne) {
	if(indice.equals("l")) {
		for (int l=ligne;l<10;l++) {
			if(this.tableau[l][colonne]==1) {return false;}
		}
	}
	if(indice.equals("c")) {
		for(int c=colonne;c<10;c++) {
			if(this.tableau[ligne][c]==1) {return false;}
		}
	}
	
	return true;}



public String afficher() {//méthode pour afficher la grille du joueurs auquelle elle appartient 
	String s="";
	for (int y=0;y<this.tableau.length;y++) {
		for (int x=0; x<this.tableau[y].length;++x) {
			/*System.out.print(tableau[y][x]);*/
			s+=tableau[y][x];
			s+="|";
		}
		/*System.out.println();*/
		s+="\n";
	}
	return s;
}

public void afficher2() {//méthode pour afficher la grille 
	
	
	for (int y=0;y<this.tableau.length;y++) {
		for (int x=0; x<this.tableau[y].length;++x) {
			System.out.print(tableau[y][x]);
			
		}
		System.out.println();
		
	}
	
}


public String joue(int[] coup) {
	if (this.tableau[coup[0]][coup[1]]==2) {
		return "raté";}
	else if (this.tableau[coup[0]][coup[1]]==1) {
		this.tableau[coup[0]][coup[1]]=2;
		
	
		String resultat=this.EstCoule(coup);//méthode qui vérifie dans le cas ou on touche un bateau si ce bateau est coulé ou pas
		return resultat;
	}
	else {
			return "raté";
		}
	}


public boolean check(){// on verifie si la grille correspond à une condition de défaite
 boolean condition=true;//la valeur 0 indique que la partie est finis
	for(int i=0;i<this.tableau.length;i++) {
		 for (int j=0;j<this.tableau[i].length;j++) {
			 if (tableau[i][j]==1) {// on parcours toute la grille , et si un bateau n'est pas encore coulé, on change la valeur de condition
				 condition=false;
			 }
		 }		 
		 
}
return condition;// ce boolean servira a annoncé un vainqueur, si la valeur de sortie est "true" alors la partie doit se terminer
}

public String test() {
	// TODO Auto-generated method stub
	return "test";
}
//methode pour vérifier si un bateau est placé en ligne ou en colonne

public boolean bateauEnLigne(int l, int c) {
	if ((c-1>=0) && (this.tableau[l][c-1]==1 || this.tableau[l][c-1]==2)){
		return true;}
		else if ((c+1<10) && (this.tableau[l][c+1]==1 || this.tableau[l][c+1]==2)){
			return true;}
	return false;
		}
//verifie si un coup qui a touché coule
public String EstCoule(int[] coup) {
	int l=coup[0];
	int c=coup[1];
	if (bateauEnLigne(coup[0],coup[1])) {
		int i=1;
		while (c-i>=0) {
			if (this.tableau[l][c-i]==1) {return "touché";}
			else if (this.tableau[l][c-i]==0) {break;}
			i++;
		}
		int j=1;
		while(c+j<10) {
			if (this.tableau[l][c+j]==1) {return "touché";}
			else if (this.tableau[l][c+j]==0) {break;}
				}
		}
	
	
	else { 
	int k=1;
	while(l-k>=0) {
	if (this.tableau[l-k][c]==1) {return "touché";}
	else if (this.tableau[l-k][c]==0) {break;}
	k++;
	}
	int h=1;
	while(l+h<10) {
		if (this.tableau[l+h][c]==1) {return "touché";}
		else if (this.tableau[l+h][c]==0) {break;}
		h++;}
	}
		
	return "coulé";
}
	
}
