/**
 * La classe PieceDomino est propre à la classe Domino.
 * Celle-ci reprend les caractéristiques d’une pièce de domino.
 * 
 */

public class PieceDomino extends Piece{

    /** Valeurs des côtés */
    protected int value1;
    protected int value2;

    /** Permet de savoir si les côtés sont couplés ou pas*/
    protected boolean couple1;
    protected boolean couple2;

    /**
     * Creation d'une pièce de domino.
     * @param v1 premier côté.
     * @param v2 deuxième côté.
     */
    public PieceDomino(final int v1, final int v2){
        super();
        value1=v1;
        value2=v2;
        couple1=false;
        couple2=false;
    }
    /**
     * Intérêt majeur pour décisider qui commence le jeu
     * @return permet de savoir si la pièce est un double ou pas.
     */
    public boolean isDouble(){
        return value1==value2;
    }

    public String toString(){
        return "["+value1+" "+value2+"]";
    }

    public void changer(){
        int value0 = value1;
        value1 = value2;
        value2 = value0;

        boolean couple0 = couple1;
        couple1 = couple2;
        couple2 = couple0;
    }

    public String helpper(){
        return "["+couple1+" "+couple2+"]";
    }

    /** Getters et Setters */
    public int getValue1(){
        return value1;
    }

    public int getValue2(){
        return value2;
    }

    public boolean getCouple1(){
        return couple1;
    }

    public boolean getCouple2(){
        return couple2;
    }

    public void setCouple1(final boolean b){
        couple1=b;
    }

    public void setCouple2(final boolean b){
        couple2=b;
    }



}