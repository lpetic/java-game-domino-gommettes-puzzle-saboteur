import java.util.*;

public class DeckSaboteur{

	public static ArrayList<PieceSaboteur> remplirDeck() {
		ArrayList<PieceSaboteur> deck = new ArrayList<PieceSaboteur>();

		//routes
		for(int k = 0; k < 5; k++){
			deck.add(new PieceRoute(0,0,1,1,"Route"));
			deck.add(new PieceRoute(0,1,1,1,"Route"));
			deck.add(new PieceRoute(0,1,0,1,"Route"));
			deck.add(new PieceRoute(0,1,1,0,"Route"));
			deck.add(new PieceRoute(1,1,0,0,"Route"));
			deck.add(new PieceRoute(1,1,1,0,"Route"));
			deck.add(new PieceRoute(1,1,1,1,"Route"));
		}
		deck.add(new PieceRoute(0,1,0,1,"Route"));
		deck.add(new PieceRoute(0,1,1,0,"Route"));
		deck.add(new PieceRoute(1,1,0,0,"Route"));
		deck.add(new PieceRoute(1,0,0,0,"Route"));
		deck.add(new PieceRoute(1,1,0,1,"Route"));
		deck.add(new PieceRoute(1,1,1,1,"Route"));
		deck.add(new PieceRoute(0,0,1,1,"Route"));
		deck.add(new PieceRoute(1,0,1,1,"Route"));
		deck.add(new PieceRoute(1,1,1,1,"Route"));
		//--
		for(int k = 0; k < 4;k++){
			deck.add(new PieceAction(0,0,0,0,"Action positif lampe",1));
			deck.add(new PieceAction(0,0,0,0,"Action negatif lampe",-1));
			deck.add(new PieceAction(0,0,0,0,"Action positif chariot",2));
			deck.add(new PieceAction(0,0,0,0,"Action negatif chariot",-2));
			deck.add(new PieceAction(0,0,0,0,"Action negatif pioche",-3));
			deck.add(new PieceAction(0,0,0,0,"Action positif pioche",3));
		}
		return deck;
	}
	
}