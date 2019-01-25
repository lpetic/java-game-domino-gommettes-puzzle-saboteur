public class PieceRoute extends PieceSaboteur{

	private boolean depart;

	public PieceRoute(int h, int b, int g, int d, String v){
		super(h, b, g, d, v);
		depart = false;
	}

	public void turnPiece(){//On tourne a 180 degres
		int temp1 = haut;
		int temp2 = gauche;

		haut = bas; 
		bas = temp1;
		gauche = droite; 
		droite = temp2;
	}

	public void setDepart(){
		depart = true;
	}

	public boolean isDepart(){
		return depart;
	}

	public String toString(){
		return ("Route : (haut = "+haut+" ; bas = "+bas+"; gauche = "+gauche+" ; droite = "+droite+")");
	}
}