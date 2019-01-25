public class PieceAction extends PieceSaboteur{

	Integer typeAction;
			//Action positive = +; Action negative = -
			//Sabotage(transport) = 1;
	
			//Sabotage(outils) = 2; Sabotage(eclairage) = 3

	public PieceAction(int h, int b, int g, int d, String v, Integer ta){
		super(h, b, g, d, v);
		typeAction = ta;
	}

	public boolean jouerCarteActionSur(JoueurSaboteur j){
		if(j.effets.contains(this.typeAction)){
			if(this.typeAction > 0){
				j.effets.remove(this.typeAction);
				return true;
			} else {
				System.out.println("Un effet de ce type est deja actif sur la cible");
				return false;
			}
		} else {
			if(this.typeAction > 0){
				System.out.println("La cible n'a pas d'effet negatifs de ce type actif");				
				return false;
			} else {
				j.effets.add(typeAction);
				return true;
			}
		}
	}

	public String toString(){
		if(typeAction == 1)
			return "Reparation transport";
		if(typeAction == 2)
			return "Reparation outil";
		if(typeAction == 3)
			return "Reparation eclairage";
		if(typeAction == -1)
			return "Sabotage transport";
		if(typeAction == -2)
			return "Sabotage outil";
		if(typeAction == -3)
			return "Sabotage transport";
		return "carte inconnue";
	}
}

