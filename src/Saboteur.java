import java.util.*;

public class Saboteur extends Puzzle{

	protected JoueurSaboteur joueur;
	protected ArrayList<PieceSaboteur> deck;
	protected ArrayList<JoueurSaboteur> players;
	protected VueGenerale vue;

	protected static final int minjoueurs = 5;
	protected static final int maxjoueurs = 7;

	//Constructeur pour un ArrayList de JoueurSaboteur et un ArrayList de PieceSaboteur
	public Saboteur(ArrayList<JoueurSaboteur> p){
		super(null);
		if((p.size() < minjoueurs) || (p.size() > maxjoueurs)) {
			throw new RuntimeException(
				"Exception: Il faut au minimum " + minjoueurs + " joeurs et au maximum " + maxjoueurs + ".");
		} else {
			createPieces();
			plateau = new LinkedList<PiecePuzzle>();
			addPieceToPlateau(new PieceRoute(1,1,1,1,"Depart"),0,0);
			PieceRoute depart = (PieceRoute)getPieceAtPos(0,0);
			depart.setDepart();
			players = p;
			vue = new VueGenerale(this);
		}
	}

	//Affiche le plateau, avec des indications si PieceDonnee non null
	public void afficherPlateau(PiecePuzzle PieceDonnee){
		vue.afficherPlateauSaboteur(PieceDonnee, plateau);
	}

	public String toString(){
		return "C'est le tour de "+joueur+", qui"; 
	}

	//Rends un nombre de joueurs nb saboteurs, selection aleatoire
	public void pickSaboteurs(int nb){
		int nbSaboteur = 0;
		Random r = new Random();
		while(nbSaboteur <= nb){
			int nbRand = r.nextInt((4-2)+2);
			JoueurSaboteur joueurDesigne = players.get(nbRand);
			if(joueurDesigne.saboteur == false){
				joueurDesigne.saboteur = true;
				nbSaboteur++;
			}	
		}
	}

	//Ajoute 3 cartes tresors au terrain, selection du vrai tresor aleatoire
	public void addTresors(){
		Random r = new Random();
		ArrayList<PieceTresor> liste = new ArrayList<>();
		liste.add(new PieceTresor(0,1,1,0,"Tresor",false));
		liste.add(new PieceTresor(0,1,0,1,"Tresor",false));
		liste.add(new PieceTresor(1,1,1,1,"Tresor",true));

		for(int k = 0; k < liste.size(); k++){

			int valX = r.nextInt(2);
			int valY = r.nextInt(2);
			int posX = r.nextInt(4)+3;;//9  r.nextInt(7-3)+4;
			int posY = r.nextInt(4)+3;//11

			if(valX == 0)
				posX = -posX;
			if(valY == 0)
				posY = -posY;

			if(getPieceAtPos(posX,posY) != null || possedeAdjacentFromPos(posX,posY))
				k--;
			else
				addPieceToPlateau(liste.get(k),posX,posY);
		}
	}

	//Affiche dans le terminal la liste des joueurs
	public void listeJoueurs(){
		for(int k = 0; k < players.size(); k++){
			System.out.println(k+" - "+players.get(k));
		}
		System.out.println();
	}

	//Renvoie true si un joueur a correctement defausse une de ses cartes
	//Utilise removePieceToPlayer
	@SuppressWarnings("unchecked")
	public boolean defausserCarte(){

		joueur.listeCarte();
		Scanner sc = new Scanner(System.in);
		vue.demandeCarte(1);
		try{
			joueur.removePieceToPlayer((Piece)joueur.getPieces().get(sc.nextInt()));
			return true;
		} catch(Exception e){
			vue.erreur(3);
			return false;
		}
	}

	//1-jouerPiece(), 2-enleverPiece() 3-turnPiece()
	//Renvoie true et enleve la carte de la main du joueur si celui ci a correctement joue une carte action
	@SuppressWarnings("unchecked")
	public boolean jouerAction(){
		
		LinkedList<PieceAction> mainAction = joueur.listeCarteAction();		
		Scanner sc = new Scanner(System.in);
		vue.demandeCarte(0);

		PieceAction carte = mainAction.get(sc.nextInt());

		vue.choixJoueur();
		listeJoueurs();

		if(carte.jouerCarteActionSur(players.get(sc.nextInt())) == true){
			joueur.removePieceToPlayer(carte);
			return true;
		}

		return false;
	}

	//Renvoie true et enleve la carte de la main du joueur si celui ci a correctement joue une carte route
	@SuppressWarnings("unchecked")
	public boolean jouerRoute(){
		
		LinkedList<PieceRoute> mainRoute = joueur.listeCarteRoute();		
		Scanner sc = new Scanner(System.in);

		vue.demandeCarte(0);

		PieceRoute carte = mainRoute.get(sc.nextInt());
		afficherPlateau(carte);

		vue.tourner(180);
		
		String in = sc.next();

		if(in.equals("yes")  || in.equals("oui")  || in.equals("y") || in.equals("o")){
			carte.turnPiece();
			vue.tourner(true);
			afficherPlateau(carte);
		} else {
			vue.tourner(false);
		}

		vue.coordonne(0);
		int x = sc.nextInt();
		vue.coordonne(1);
		int y = sc.nextInt();

		if(possedeAdjacentFromPos(x,y) == true && placePiece(carte,x,y) == true){
			joueur.removePieceToPlayer(carte);
			return true;
		}

		vue.erreur(1);
		return false;
	}

	//Appelle emplacementValideSaboteur(p) et renvoie son boolean
	//Remplacement Puzzle.emplacementValide(p)
	public boolean emplacementValide(PiecePuzzle p){
		return emplacementValideSaboteur(p);
	}

	//Appelle emplacementValidePuzzle(p) et possedeAjacentFromPieceNotTresor(p) et renvoie true si les deux sont true
	//Sert a verifier si un emplacement est valide pour une piece p dans le Saboteur
	public boolean emplacementValideSaboteur(PiecePuzzle p){
		if(possedeAdjacentFromPieceNotTresor(p) == true && emplacementValidePuzzle(p) == true)
			return true;
		return false;
	}

	//Renvoie true si p est une PieceTresor converti en PiecePuzzle
	public boolean isPieceTresor(PiecePuzzle p){
		PieceTresor route;
		try{
			route = toTresor(p);
			return true;
		} catch (Exception e){
			return false;
		}
	}

	//Converti une PiecePuzzle en PieceTresor et la renvoie
	public PieceTresor toTresor(PiecePuzzle p){
		return (PieceTresor)p;
	}

	//Renvoie true si p possede une piece adjacente et que celle ci est un tresor
	public boolean possedeAdjacentFromPieceTresor(PiecePuzzle p){

		if(pieceAGauche(p) != null && isPieceTresor(pieceAGauche(p)))
			return true;

		if(pieceADroite(p) != null && isPieceTresor(pieceADroite(p)))
			return true;

		if(pieceAHaut(p) != null && isPieceTresor(pieceAHaut(p)))
			return true;

		if(pieceABas(p) != null && isPieceTresor(pieceABas(p)))
			return true;
		
		return false;
	}

	//Renvoie true si p possede une piece adjacente et que celle ci n'est PAS un tresor, ou bien que ce tresor est deja visible
	private boolean possedeAdjacentFromPieceNotTresor(PiecePuzzle p){

		if(pieceAGauche(p) != null){
			if(isPieceTresor(pieceAGauche(p)) == true){
				if(toTresor(pieceAGauche(p)).getVisible() == true) 
					return true;
			} else {
				return true;
			}
		}

		if(pieceADroite(p) != null){
			if(isPieceTresor(pieceADroite(p)) == true){
				if(toTresor(pieceADroite(p)).getVisible() == true) 
					return true;
			} else {
				return true;
			}
		}

		if(pieceAHaut(p) != null){
			if(isPieceTresor(pieceAHaut(p)) == true){
				if(toTresor(pieceAHaut(p)).getVisible() == true) 
					return true;
			} else {
				return true;
			}
		}

		if(pieceABas(p) != null){
			if(isPieceTresor(pieceABas(p)) == true){
				if(toTresor(pieceABas(p)).getVisible() == true) 
					return true;
			} else {
				return true;
			}
		}
		
		return false;
	}

	//Renvoie une PieceSaboteur depuis le deck et enleve cette piece du deck
	public PieceSaboteur piocheFromDeck(){
		if(deck.size() == 0){
			vue.erreur(4);
			return null;
		} else {
			Random r = new Random();
			PieceSaboteur pioche = deck.get(r.nextInt(deck.size()-1));
			deck.remove(pioche);
			return pioche;
		}
	}

	//Distribue les pieces aux joueurs en debut de partie
	@SuppressWarnings("unchecked")
	public void distributionOfPieces(){
		for(int k = 0; k < players.size(); k++){
			while(players.get(k).getPieces().size() < 6){
				players.get(k).addPieceToPlayer(piocheFromDeck());
			}
		}
	}

	public void createPieces(){
		deck= DeckSaboteur.remplirDeck();
	}

	//Selectionne le joueur qui commence la partie en changeant joueur
	public void selectStarter(){
		Random r = new Random();
		joueur = players.get(r.nextInt(players.size()));
	}

	//Selectionne le prochain joueur et change joueur pour refleter ce choix
	private void nextInQueue(){
		int indexJoueur = players.indexOf(joueur);
		if(indexJoueur+1 == players.size())
			joueur = players.get(0);
		else
			joueur = players.get(indexJoueur+1);
	}

	//Renvoie true si le veritable tresor a ete trouve
	public boolean tresorTrouve(){
		for(int k = 0; k < plateau.size(); k++){
			if(isPieceTresor((PieceRoute)plateau.get(k)) == true){
				if(possedeAdjacentFromPieceNotTresor((PieceRoute)plateau.get(k)) == true){
					PieceTresor piece = (PieceTresor)plateau.get(k);
					if(piece.getVisible() == false && piece.isTresor() == true){
						vue.tresor(true);
						return true;
					} else if(piece.getVisible() == false && piece.isTresor() == false) {
						vue.tresor(false);
					}
				}
			}
		}

		return false;
	}

	//Joue un tour en affichant plateau et autres choix possibles
	@SuppressWarnings("unchecked")
	public void jouerTour(){

		afficherPlateau(null);
		System.out.println("\n"+joueur+"\n");
		joueur.listeCarte();
		Scanner sc = new Scanner(System.in);
		boolean choix = false;
		int reponse = 0;

		while(choix == false){
			if(joueur.effets.size() == 0)
				vue.choixTour("Saboteur+");
			else
				vue.choixTour("Saboteur");

			try{
				reponse = sc.nextInt();
				if(reponse == 1){
					if(jouerAction() == true){
						choix = true;
					}
				}

				else if(reponse == 2){
					if(defausserCarte() == true){
						choix = true;
					}
				}

				else if(reponse == 3 && joueur.effets.size() == 0){
					if(jouerRoute() == true){
						choix = true;
					}
				}

				if(choix == false)
					vue.erreur(2);
			} catch(Exception e){
				vue.erreur(3);
				reponse = 0;
				sc = new Scanner(System.in);
			}
		}

		joueur.addPieceToPlayer(piocheFromDeck());
		vue.nextPlayer();
		nextInQueue();
	} 
}