import java.util.LinkedList;

public class VueGenerale{

	Saboteur saboteur;
	Puzzle puzzle;

	VueGenerale(){
	}

	VueGenerale(Saboteur s){
		saboteur = s;
	}

	VueGenerale(Puzzle p){
	  	puzzle = p;
	}

	public void printPlayer(){
		System.out.println("\n Le joueur actuel est : "+puzzle.joueur+"\n");
	}

    public void demandeCarte(int x){
    	if(x == 0)
    		System.out.println("Quelle carte voulez-vous jouer (int)");
    	if(x == 1)
    		System.out.println("Quelle carte souhaitez-vous defausser ?");
    }

    public void choixTour(String s){
    	if(s.equals("Saboteur")){
    		System.out.println("Choissisez une action : 1 - Jouer une carte action");
			System.out.println("			2 - Jeter une carte");
    	}

    	else if(s.equals("Saboteur+")){
    		System.out.println("Choissisez une action : 1 - Jouer une carte action");
			System.out.println("			2 - Jeter une carte");
    		System.out.println("			3 - Poser une route");
    	}

    	else if(s.equals("Puzzle")){
    		System.out.println("Choissisez une action : 1 - Placer une piece");
			System.out.println("			2 - Enlever une piece");
			System.out.println("			3 - Tourner une piece vers la droite");
    	}
    }

    public void choixJoueur(){
    	System.out.println("Sur qui souhaitez-vous utiliser cette carte ? (int)");
    }

    public void tourner(int x){
    	if(x == 180)
    		System.out.println("Souhaitez-vous tourner cette piece a 180 degres");
    	if(x == 90)
    		System.out.println("Selectionnez quelle piece vous souhaitez tourner de 90 degres vers la droite");
    }

    public void tourner(boolean b){
    	if(b == true)
    		System.out.println("\nLa carte a ete tournee");
    	else
    		System.out.println("\nLa carte n'a pas ete tournee");
    }

    public void placementPiece(String s){
    	if(s.equals("route"))
    		System.out.println("Ou souhaitez-vous placer cette route\n");
    }

	public void coordonne(int x){
		if(x == 0)
			System.out.println("Coordonnee X");
		else
			System.out.println("Coordonnee Y");
	}

	public void erreur(int x){
		if(x == 1)
			System.out.println("Placement invalide");
		if(x == 2)
			System.out.println("Votre choix n'est pas valide!");
		if(x == 3)
			System.out.println("Invalide");
		if(x == 4)
			System.out.println("Le deck est vide!");
		if(x == 5)
			System.out.println("\nIl n y a pas de piece a cette position");
		if(x == 6)
			System.out.println("La valeur doit etre inferieur a 10 et superieur a -10");
	}

	public void victoire(){
		System.out.println("Vous avez remporte la partie !");
	}

	public void tresor(boolean b){
		if(b == true)
			System.out.println("Le tresor a ete trouve !");
		else
			System.out.println("Ce n'est pas le tresor...");
	}

	public void nextPlayer(){
		System.out.println("\nPassage au prochain joueur\n");
	}

	public void afficherPlateauSaboteur(PiecePuzzle PieceDonnee,LinkedList<PiecePuzzle> plateau){

		System.out.println();

		int xmin = 0;
		int xmax = 0;
		int ymin = 0;
		int ymax = 0;

		for(PiecePuzzle p : plateau){
			if(p.getX() > xmax)
				xmax = p.getX();
			if(p.getX() < xmin)
				xmin = p.getX();
			if(p.getY() > ymax)
				ymax = p.getY();
			if(p.getY() < ymin)
				ymin = p.getY();
		}

		xmin--;
		xmax++;
		ymin--;
		ymax++;

		for(int y = ymax; y > ymin-1;y--){
			if(y < 0)
				System.out.print(-y+"|");
			else
				System.out.print(y+"|");
			for(int x = xmin; x < xmax+1;x++){
				PieceRoute piece = (PieceRoute)saboteur.getPieceAtPos(x,y);

				if(piece != null){
					if(piece.isDepart())
						System.out.print("D");
					else if(saboteur.isPieceTresor(piece)){
						if(saboteur.toTresor(piece).getVisible() == true)
							System.out.print("F");
						else
							System.out.print("T");
					} else
						System.out.print("0");
				} else if(PieceDonnee != null){

					PieceDonnee.setX(x);
					PieceDonnee.setY(y);
					PieceRoute dummyP = new PieceRoute(1,1,1,1,"dummy");
					dummyP.setX(x);
					dummyP.setY(y);

					if(saboteur.emplacementValide(PieceDonnee))
						System.out.print("X");
					else if(saboteur.emplacementValidePuzzle(dummyP) && saboteur.possedeAdjacentFromPieceTresor(dummyP))
						System.out.print("*");
					else
						System.out.print(" ");
				} else
					System.out.print(" ");
			}
			System.out.println();
		}

		System.out.print(" *");

		for(int k = xmin; k < xmax+1; k++){
			System.out.print("-");
		}

		System.out.print("\n  ");
		
		for(int k = xmin; k < xmax+1; k++){
			if(k < 0)
				System.out.print(-k);
			else
				System.out.print(k);
		}
		System.out.println("\n");
	}

	public void afficherPlateauPuzzle(PiecePuzzle PieceDonnee,LinkedList<PiecePuzzle> plateau){

		System.out.println();

		int xmin = -2;
		int xmax = 2;
		int ymin = -2;
		int ymax = 2;

		for(PiecePuzzle p : plateau){
			if(p.getX() > xmax)
				xmax = p.getX();
			if(p.getX() < xmin)
				xmin = p.getX();
			if(p.getY() > ymax)
				ymax = p.getY();
			if(p.getY() < ymin)
				ymin = p.getY();
		}

		for(int y = ymax; y > ymin-1;y--){
			if(y < 0)
				System.out.print(-y+"|");
			else
				System.out.print(y+"|");
			for(int x = xmin; x < xmax+1;x++){
				PiecePuzzle piece = puzzle.getPieceAtPos(x,y); 

				

				if(piece != null)
					System.out.print("0");
				else if(PieceDonnee != null){

					PieceDonnee.setX(x);
					PieceDonnee.setY(y);

					if(puzzle.emplacementValide(PieceDonnee) && puzzle.possedeAdjacentFromPos(x,y))
						System.out.print("X");
					else
						System.out.print(" ");
				}
				else
					System.out.print(" ");
			}
			System.out.println();
		}

		System.out.print(" *");

		for(int k = xmin; k < xmax+1; k++){
			System.out.print("-");
		}

		System.out.print("\n  ");
		
		for(int k = xmin; k < xmax+1; k++){
			if(k < 0)
				System.out.print(-k);
			else
				System.out.print(k);
		}
		System.out.println("\n");
	}


	public static String dominoString(int x){
		String [] s = {"Choisissez le nombre de joueurs:",
						"Quel est le nom du jouer ?",
						"Exception: Il faut au minimum " + Domino.MIN_PLAYERS + " jouers et au maximum " + Domino.MAX_PLAYERS + ".",
						"Invalide!",
						"Choisissez la piece a placher:",
						"C'est le tour de: ",
						"Piece pas compatible!",
						"Affichage du plateau",
						"--------------------",
						"Piocher!",
						" a gagne la partie",
					};
		return s[x];
	}
}