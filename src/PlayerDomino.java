/**
 * Classe du joeur domino. 
 * Consu pour ne pas marquer a chaque 
 * fois le parametre de Player.
 */
public class PlayerDomino extends Player<PieceDomino>{

    public PlayerDomino(String n){
        super(n);
    }

    public void removePieceToPlayer(PieceDomino p){
        if(!pieces.remove(p)){
            for (PieceDomino piece : pieces) {
                if(piece.getValue1()==piece.getValue1() && piece.getValue2()==piece.getValue2()){
                    System.out.println(pieces.remove(piece));
                    break;
                }
            }
        }
    }

}