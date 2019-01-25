/**
 * La classe Player est globale a l'application. 
 * Elle represente tous les joueurs de tous les jeux. 
 * 
 */

import java.util.*;

public class Player<E extends Piece>{

    /** Nom */
    protected final String name;

    /** Identifiant */
    protected final int id;

    /** Tour */
    protected boolean turn = false;

    /** Pieces dont le joueur possede */
    protected LinkedList<E> pieces;

    /** Incrementation de l'identifiant */
    protected static int incrementing=0;

    /**
     * Creation d un joeur. 
     * @param n nom du joeur. 
     */
    public Player(String n){
        name = n;
        id = incrementing;
        incrementing++;
        pieces = new LinkedList<>();
    }

    /**
     * Permet d ajouter une piece, a un joueur.
     * @param p Piece atribue de facon aleatoire.
     */
    public void addPieceToPlayer(E p){
        pieces.add(p);
    }

    /**
     * Permet de retirer une piece, a un joueur.
     * @param p joue par le jouer.
     * @exception RuntimeExeption c'est un cas qui ne devrait pas arriver.
     */
    public void removePieceToPlayer(E p){
       pieces.remove(p);
    }

    /**
     * 
     * @return des information sur le jouer.
     */
    public String getDescription(){
        String isTurn = turn ? " c'est sont tour": "";
        String s = "Joueur: "+name + isTurn +"\n";
        for (E p : pieces) {
            s+=p.toString()+"\n";
        }
        return s;
    }

    /**
     * Affiche les pieces d'un jouer.
     */
    public void listeCarte(){
        for(int k = 0; k < this.getSize(); k++)
            System.out.println(k+" - "+this.getPieces().get(k));
        System.out.println();
    }

    public String toString() {
        return name;
    }
    
    /** 
     * Getters et Setters 
     */
    public LinkedList<E> getPieces(){
        return pieces;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }
        
    public boolean getTurn(){
        return turn;
    }
        
    public void setTurn(boolean b){
        turn=b;
    }

    public int getSize(){
        return pieces.size();
    }
        
}