import java.awt.*;
/**
 * Classe pour le jeu de domino gommette. 
 * @param <U> type piece de domino
 */
public class Gommette<U extends PieceGommette> extends Domino<U,PlayerDomino>{
    /** Couleurs des pieces gommette */
    private static Color [] COLOR_ARRAY = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.ORANGE, Color.GRAY, Color.PINK};

    public Gommette(PlayerDomino [] player){
        super(player);
    }
    /**
     * Creation des pieces du jeu Gommette 
     * est different de celui de domino classique.
     * A cause des pieces qui ont un proprite 
     * en plus: la couleur.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void createPieces(){
        for(int i=0; i<=6; i++){
            for(int j=0; j<=i; j++){
                int rand1 = (int) (Math.random() * COLOR_ARRAY.length);
                int rand2 = (int) (Math.random() * COLOR_ARRAY.length);
                PieceGommette p = new PieceGommette(i,j,COLOR_ARRAY[rand1], COLOR_ARRAY[rand2]);
                domino.add((U)p);
            }
        }
    }
    /**
     * Compatibilite avec une proprite en plus.
     * Verification de la compatibilite des couleurs.
     * @param player Le jouer dont c'est le tour.
     * @param pieceDomino Piece a placer.
     * @return
     */
    public boolean compatibility(PlayerDomino player, U pieceDomino){
        if(super.compatibility(player,pieceDomino)){
            return true;
        }else{
            U first = plateau.getFirst();
            U last = plateau.getLast();
        if (pieceDomino.getHex1().equals(first.getHex1()) && !first.getCouple1()) {
            first.setCouple1(true);
            pieceDomino.setCouple1(true);
            plateau.addFirst((U)pieceDomino);
            player.removePieceToPlayer(pieceDomino);
            return true;
        } else if (pieceDomino.getHex2().equals(first.getHex2()) && !first.getCouple2()) {
            first.setCouple2(true);
            pieceDomino.setCouple2(true);
            plateau.addFirst((U)pieceDomino);
            player.removePieceToPlayer(pieceDomino);
            return true;
        } else if (pieceDomino.getHex2().equals(first.getHex1()) && !first.getCouple1()) {
            first.setCouple1(true);
            pieceDomino.setCouple2(true);
            plateau.addFirst((U)pieceDomino);
            player.removePieceToPlayer(pieceDomino);
            return true;
        } else if (pieceDomino.getHex1().equals(first.getHex2()) && !first.getCouple2()) {
            first.setCouple2(true);
            pieceDomino.setCouple1(true);
            plateau.addFirst((U)pieceDomino);
            player.removePieceToPlayer(pieceDomino);
            return true;
        } else if (pieceDomino.getHex1().equals(last.getHex1()) && !last.getCouple1()) {
            last.setCouple1(true);
            pieceDomino.setCouple1(true);
            plateau.addLast((U)pieceDomino);
            player.removePieceToPlayer(pieceDomino);
            return true;
        } else if (pieceDomino.getHex2().equals(last.getHex2()) && !last.getCouple2()) {
            first.setCouple2(true);
            pieceDomino.setCouple2(true);
            plateau.addLast((U)pieceDomino);
            player.removePieceToPlayer(pieceDomino);
            return true;
        } else if (pieceDomino.getHex2().equals(last.getHex1()) && !last.getCouple1()) {
            last.setCouple1(true);
            pieceDomino.setCouple2(true);
            plateau.addLast((U)pieceDomino);
            player.removePieceToPlayer(pieceDomino);
            return true;
        } else if (pieceDomino.getHex1().equals(last.getHex2()) && !last.getCouple2()) {
            last.setCouple2(true);
            pieceDomino.setCouple1(true);
            plateau.addLast((U)pieceDomino);
            player.removePieceToPlayer(pieceDomino);
            return true;
        }
        return false;
        }
    }

}