/**
 * La classe Piece est globale a l'application. 
 * Elle represente tous les Pieces de tous les jeux 
 * en utilisant l'heritage.
 */
public abstract class Piece{
    /**
     * Coordonnees du point
     */
    protected int x;
    protected int y;
    /**
     * 
     * @param x coordonnees des abscisses
     * @param y coordonnees des ordonnees
     */
    public Piece(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Piece(){
        this.x=0;
        this.y=0;
    }
    /** 
     * Getters et Setters 
     */
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x=x;
    }
 
    public void setY(int y){
        this.y=y;
    }
}