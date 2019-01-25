import java.util.*;

public class JoueurSaboteur extends Player{

	protected ArrayList<Integer> effets;
	boolean saboteur;

	public JoueurSaboteur(String name){
		super(name);
		effets = new ArrayList<Integer>();
		saboteur = false;
	}

	public LinkedList<PieceAction> listeCarteAction(){
		LinkedList<PieceAction> liste = new LinkedList<>();

		for(int k = 0; k < this.getSize(); k++){
			if(this.getPieces().get(k) instanceof PieceAction)
				liste.add((PieceAction)this.getPieces().get(k));
		}

		for(int k = 0; k < liste.size();k++)
			System.out.println(k+" - "+liste.get(k));

		System.out.println();
		return liste;
	}

	public LinkedList<PieceRoute> listeCarteRoute(){
		LinkedList<PieceRoute> liste = new LinkedList<>();

		for(int k = 0; k < this.getSize(); k++){
			if(this.getPieces().get(k) instanceof PieceRoute)
				liste.add((PieceRoute)this.getPieces().get(k));
		}

		for(int k = 0; k < liste.size();k++)
			System.out.println(k+" - "+liste.get(k));

		System.out.println();
		return liste;
	}

	public String toString(){
		String mot = name;
		if(saboteur == true)
			mot += ", je suis un saboteur"; 
		else
			mot += ", je ne suis pas un saboteur";

		if(effets.size() != 0)
			mot += ", je suis affecte par "+effets;

		return mot;
	}
}

