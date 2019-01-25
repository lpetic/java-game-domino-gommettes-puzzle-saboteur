import java.util.*;

public class Puzzle{

	protected LinkedList<PiecePuzzle> plateau;
	protected ArrayList<PiecePuzzle> tab;//Est null
	protected Player joueur;
	protected VueGenerale vue;
	
	//Constructeur pour un joueur et un ArrayList de PiecePuzzle
	public Puzzle(Player j){
		createPieces();
		plateau = new LinkedList<>();
		joueur = j;
		if(tab != null)
			distributionOfPieces();

		vue = new VueGenerale(this);
		vue.printPlayer();
	}

	public void createPieces(){
		tab = DeckPuzzle.remplirDeck();
	}
	@SuppressWarnings("unchecked")
	public void distributionOfPieces(){
		for(int k = 0; k < tab.size(); k++)
			joueur.addPieceToPlayer(tab.get(k));
	}

	//Renvoie true si il existe une piece adjacente a la positon x,y
	public boolean possedeAdjacentFromPos(int posX, int posY){

		if(getPieceAtPos(posX+1,posY) != null)
			return true;
		if(getPieceAtPos(posX-1,posY) != null) 
			return true;
		if(getPieceAtPos(posX,posY+1) != null)
			return true;
		if(getPieceAtPos(posX,posY-1) != null)
			return true;

		return false;
	}

	//Affiche le plateau, avec des indications si PieceDonnee non null
	public void afficherPlateau(PiecePuzzle PieceDonnee){
		vue.afficherPlateauPuzzle(PieceDonnee, plateau);		
	}

	//Getter plateau
	public LinkedList<PiecePuzzle> getPlateau(){
		return plateau;
	}

	//Renvoie la PiecePuzzle a la position x,y
	public PiecePuzzle getPieceAtPos(int posX, int posY){
		for(int k = 0; k < plateau.size(); k++){
			if(plateau.get(k).getX() == posX && plateau.get(k).getY() == posY)
				return plateau.get(k);
		} return null;
	}

	//Renvoie true avec un message si le joueur ne possede plus de piece
	public boolean plusDePieces(){
		if(joueur.getPieces().isEmpty() == true && noPieceSeule() == true){
			System.out.println(joueur.getPieces().isEmpty()+" "+noPieceSeule());
			afficherPlateau(null);
			vue.victoire();
			return true;
		}
		return false;
	}

	public boolean noPieceSeule(){
		for(int k = 0; k < plateau.size(); k++){
			if(possedeAdjacentFromPos(plateau.get(k).getX(),plateau.get(k).getY()) == false)
				return false;
		}
		return true;
	}

	//Retire une piece p du plateau et l ajoute a la main du joueur
	@SuppressWarnings("unchecked")
	private void removeFromPlateau(PiecePuzzle p){
		if(p != null){
			plateau.remove(p);
			joueur.addPieceToPlayer(p);
		} else
			vue.erreur(5);
		
	}

	//Ajoute une piece p au plateau a la position x,y
	public void addPieceToPlateau(PiecePuzzle p, int posX, int posY){
		p.setX(posX);
		p.setY(posY);
		plateau.add(p);
	}


	//Affiche le tableau, appelle PlacePiece(piece,X,Y), renvoie true et enleve la piece de la main si la piece a ete placee avec succes
	@SuppressWarnings("unchecked")
	public boolean jouerPiece(){
		Scanner sc = new Scanner(System.in);
		int choix, x, y;


		while(true){
			joueur.listeCarte();
			vue.demandeCarte(0);
			choix = sc.nextInt();

			PiecePuzzle piece = (PiecePuzzle)joueur.getPieces().get(choix);
			afficherPlateau(piece);

			while(true){
				vue.coordonne(0);
				x = sc.nextInt();
				if(x < 10 && x > -10)
					break;
				else{
					vue.erreur(6);
				}
			}
			while(true){
				vue.coordonne(1);
				y = sc.nextInt();
				if(y < 10 && y > -10)
					break;
				else{
					vue.erreur(6);
				}
			}
			

			try{
				if(placePiece(piece,x,y) == true){
					joueur.removePieceToPlayer(piece);
					return true;
				} else {
					vue.erreur(1);
				}
			} catch (Exception e){
				vue.erreur(3);
				return false;
			}
		}
	}

	//Enleve une piece du plateau et renvoie true si enlevee avec succes
	public boolean enleverPiece(){
		Scanner sc = new Scanner(System.in);
		int x, y;
		vue.coordonne(0);
		x = sc.nextInt();
		vue.coordonne(1);
		y = sc.nextInt();
		try{
			removeFromPlateau(getPieceAtPos(x,y));
			return true;
		} catch (Exception e) {
			vue.erreur(3);
			return false;
		}
	}

	//Appelle emplacementValidePuzzle(p) et renvoie son boolean
	public boolean emplacementValide(PiecePuzzle p){
		return emplacementValidePuzzle(p);
	}

	//Renvoie true si l'emplacement getX(), getY() de la piece p est compatible pour la piece p
	public boolean emplacementValidePuzzle(PiecePuzzle p){
		//Verifie que l'emplacement p.getX(), p.getY() est vide
		if(getPieceAtPos(p.getX(),p.getY()) != null)
			return false;

		//Verifie que p est compatible avec les pieces aux alentours
		if(pieceAGauche(p) != null && p.compatibleA(pieceAGauche(p),3) != true)
			return false;
		if(pieceADroite(p) != null && p.compatibleA(pieceADroite(p),4) != true)
			return false;
		if(pieceAHaut(p) != null && p.compatibleA(pieceAHaut(p),1) != true)
			return false;
		if(pieceABas(p) != null && p.compatibleA(pieceABas(p),2) != true)
			return false;

		return true;
	}

	//Renvoie true si la piece p a ete placee en position x,y
	public boolean placePiece(PiecePuzzle p, int posX, int posY){
		
		//Changement des valeurs de p.X, p.Y
		p.setX(posX);
		p.setY(posY);

		//Verification de l'emplacement
		if(emplacementValide(p) == false)
			return false;

		//Ajout de la piece p au plateau si l'emplacement est valide
		addPieceToPlateau(p,posX,posY);
		return true;
	}

	//Renvoie true si la piece dans la main du joueur a bien ete tournee
	public boolean turnPiece(){
		Scanner sc = new Scanner(System.in);
		vue.tourner(90);
		joueur.listeCarte();
		int choix = sc.nextInt();
		PiecePuzzle piece;

		try{
			//Recupere la piece pour int choix
			piece = (PiecePuzzle)joueur.getPieces().get(choix);
		} catch (Exception e) {
			return false;
		}
		piece.turnPiece();
		return true;
	}

	//Serie de fonctions renvoie la PiecePuzzle situe a la position donnee dans le titre de la fonction par rapport a la piece p
	public PiecePuzzle pieceAGauche(PiecePuzzle p){
		if(getPieceAtPos(p.getX()-1,p.getY()) != null)
			return getPieceAtPos(p.getX()-1,p.getY());
		return null;
	}

	public PiecePuzzle pieceADroite(PiecePuzzle p){
		if(getPieceAtPos(p.getX()+1,p.getY()) != null)
			return getPieceAtPos(p.getX()+1,p.getY());
		return null;
	}

	public PiecePuzzle pieceAHaut(PiecePuzzle p){
		if(getPieceAtPos(p.getX(),p.getY()+1) != null)
			return getPieceAtPos(p.getX(),p.getY()+1);
		return null;
	}

	public PiecePuzzle pieceABas(PiecePuzzle p){
		if(getPieceAtPos(p.getX(),p.getY()-1) != null)
			return getPieceAtPos(p.getX(),p.getY()-1);
		return null;
	}

	//Appelle jouerPiece(), enleverPiece() ou turnPiece() en fonction de int choix
	public void jouerTour(){
		boolean choix = false;
		Scanner sc = new Scanner(System.in);
		int reponse = 0;

		while(choix == false){

			afficherPlateau(null);
			vue.choixTour("Puzzle");

			try{
				reponse = sc.nextInt();
				if(reponse == 1){
					if(jouerPiece() == true){
						choix = true;
					}
				}

				else if(reponse == 2){
					if(enleverPiece() == true){
						choix = true;
					}

				} else if(reponse == 3){
					if(turnPiece() == true){
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
	}
}

	