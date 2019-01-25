public class PiecePuzzle extends Piece{

	final String valeur;
	Integer haut, bas, gauche, droite;
	// 		1	 2		3		4

	public PiecePuzzle(int h, int b, int g, int d, String v){
		super();
		haut = h;
		bas = b;
		gauche = g;
		droite = d;

		valeur = v;
	}

	public void turnPiece(){//On tourne a 90 degres vers la droite
		int tempH = haut;
		int tempB = bas;
		int tempG = gauche;
		int tempD = droite;
	
		gauche = tempB;
		droite = tempH;  
		bas = tempD;
		haut = tempG;
	}

	boolean compatibleA(PiecePuzzle p2, int cote){
		if(cote == 1){
			if(this.haut != 0 && p2.bas != 0 && this.haut == p2.bas)
				return true;
		} if(cote == 2){
			if(this.bas != 0 && p2.haut != 0 && this.bas == p2.haut)
				return true;
		} if(cote == 3){
			if(this.gauche != 0 && p2.droite != 0 && this.gauche == p2.droite)
				return true;
		} if(cote == 4){
			if(this.droite != 0 && p2.gauche != 0 && this.droite == p2.gauche)
				return true;
		} return false;
	}

	public String toString(){
		return ("Puzzle : (haut = "+haut+" ; bas = "+bas+"; gauche = "+gauche+" ; droite = "+droite+")");
	}
}