import java.util.*;

public class DeckPuzzle{

	public static ArrayList<PiecePuzzle> remplirDeck() {
		ArrayList<PiecePuzzle> deck = new ArrayList<PiecePuzzle>();

		//routes
		deck.add(new PiecePuzzle(0,1,0,4,"Piece hg"));
		deck.add(new PiecePuzzle(0,3,4,0,"Piece hd"));
		deck.add(new PiecePuzzle(1,0,0,2,"Piece bg"));
		deck.add(new PiecePuzzle(3,0,2,0,"Piece bd"));
		
		return deck;
	}
}