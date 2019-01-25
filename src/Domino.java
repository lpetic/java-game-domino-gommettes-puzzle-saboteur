import java.util.*;
/**
 * Classe correspondant a toute les fonctionalites 
 * du jeu de Domino. Elle prend en comme parametres
 * le type de piece à utiliser et le type de jouer.
 * C'est particulierement utile pour la suite, 
 * lors de l'heritage de la classe Gommette,
 * pour des questions de compatibilite.
 * @param <U> type piece de domino
 * @param <E> type jouer 
 */

public class Domino<U extends PieceDomino, E extends PlayerDomino> implements GameBase{
    /** Liste des jouers de la partie*/
    public ArrayList<E> players;
    /** Liste des pieces sur le plateau*/
    protected LinkedList<U> plateau;
    /** Liste des pieces de domino du jeu en general*/
    protected LinkedList<U> domino;
    /** Permet de savoir si la partie est en cours ou pas.
     * L'utilite de ce denier est importante si on veut forcer les jeu,
     * que les pieces se range de facon automatise.
     * Mais aussi pour le jeu textuel.
     */
    protected boolean stuck;
    /** Valeurs connus a l'avance, ce sont les proprites du jeu*/
    protected static final int MIN_PLAYERS = 2;
    protected static final int MAX_PLAYERS = 4;
    protected static final int NUMBER_OF_PIECES = 28;
    /**
     * Initialisation des variables 
     * @param player Liste des jouers
     */
    public Domino(E [] player) {
        if (numberWrong(player.length)) {
            throw new RuntimeException(VueGenerale.dominoString(3));
        } else {
            domino = new LinkedList<>();
            plateau = new LinkedList<>();
            players = new ArrayList<>();
            stuck = false;
            for (int i = 0; i < player.length; i++) {
                VueGenerale.dominoString(1);
                players.add(player[i]);
            }
        }
    }

    public static boolean numberWrong(int x){
        return ((x < MIN_PLAYERS) || (x > MAX_PLAYERS));
    }
    /**
     * Regroupement des fonction pour lancer la partie.
     */
    public void startGame() {
        createPieces();
        distributionOfPieces();
        whichPlayerTurn();
    }
    /**
     * Creation des pieces de domino, 
     * et ajout dans la liste des dominos du jeu.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void createPieces() {
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= i; j++) {
                domino.add((U)new PieceDomino(i,j));
            }
        }
    }
    /**
     * Piocher une piece aleatoirement.
     */
    //@SuppressWarnings("unchecked")
    public U randomPieceDomino(){
        if (!domino.isEmpty()){
            int rand = (int) (Math.random() * domino.size());
            U p = domino.get(rand);
            domino.remove(p);
            return (U)p;
        }else{
            throw new RuntimeException("Exception: Il n'y a plus de dominos!");
        }
    }
    /**
     * Distribution des pieces entre les jouers 
     * lors du demarrage de la partie.
     */
    public void distributionOfPieces() {
        int dominoPerPlayer = players.size() == MIN_PLAYERS ? 7 : 6;
        for (int i = 0; i < dominoPerPlayer + 1; i++) {
            for (PlayerDomino p : players) {
                PieceDomino rand = randomPieceDomino();
                p.addPieceToPlayer(rand);
                domino.remove(rand);
            }
        }
    }
    /**
     * Decider quel est le jouer qui commence la partie.
     * Dans un premier temps en fonction des pieces doubles qu'il detient.
     * Puis, s'il n'y a pas de doubles de facon aleatoire.
     */
    public void whichPlayerTurn() {
        PlayerDomino p0 = null;
        PieceDomino pd0 = new PieceDomino(-1, -1);
        for (PlayerDomino player : players) {

            for (PieceDomino piece : player.getPieces()) {
                if (piece.isDouble() && pd0.getValue1() < piece.getValue1()) {
                    p0 = player;
                    pd0 = piece;
                }
            }
        }
        if (p0 != null) {
            p0.setTurn(true);
        } else {
            int rand = (int) (Math.random() * domino.size());
            for (PlayerDomino player : players) {
                if (player.getId() == rand) {
                    player.setTurn(true);
                    break;
                }
            }
        }
    }
    /**
     * Iteration du tour des jouers.
     */
    public void setNextTurn(PlayerDomino player) {
        int i = 0;
        if (player.getId() + 1 < players.size()) {
            i = player.getId() + 1;
        }
        player.setTurn(false);
        players.get(i).setTurn(true);
    }
    /**
     * Dimino textuel, porte d'entre du jeu
     */
    @SuppressWarnings("unchecked")
    public void iterationOfTurn(){
        for (E player : players) {
            if(player.getTurn()){
                System.out.println(VueGenerale.dominoString(4));
                System.out.println(VueGenerale.dominoString(5)+player.getName());
                int n=0; 
                System.out.println("0 - "+VueGenerale.dominoString(9));
                for(int i=0; i< player.getPieces().size(); i++){
                    n = i+1;
                    System.out.println(n+" - "+player.getPieces().get(i));
                }
                try {
					Scanner scr = new Scanner(System.in);
					n = scr.nextInt();
				} catch (Exception e) {
					System.out.println(VueGenerale.dominoString(3));
                }
                if(n>-1 && n<player.getPieces().size()+1){
                    if(n==0){
                        U rand = randomPieceDomino();
                        player.addPieceToPlayer(rand);
                    }else if(setOnPlateau(player, (U)player.getPieces().get(n-1))){
                        setNextTurn(player);
                    }else{
                        System.out.println(VueGenerale.dominoString(6));
                    }
                    break;
                }
            }
            if(player.getPieces().isEmpty()){
                stuck=true;
                System.out.println(player.getName()+VueGenerale.dominoString(10));
                return;
            }
        }
        System.out.println(VueGenerale.dominoString(8));
        System.out.println(VueGenerale.dominoString(7));
        for (PieceDomino p : plateau){
            System.out.print(p);
        }
        System.out.println();
        System.out.println(VueGenerale.dominoString(8));
    }

    /**
     * Met une piece sur le plateau en verifiant 
     * la compatibilite de la piece a placer.
     * 
     * @param player Le jouer dont c'est le tour.
     * @param pieceDomino Piece a placer.
     * @return  Savoir si le placement a eu lieu, ou pas.
     */
    public boolean setOnPlateau(E player, U pieceDomino) {
        if (!plateau.contains(pieceDomino) && player.getTurn()) {
            if (plateau.isEmpty()){ 
                plateau.addFirst((U)pieceDomino);
                setNextTurn(player);
                player.removePieceToPlayer(pieceDomino);
                return true;
            } else {
                boolean compat = compatibility(player, pieceDomino);
                if (compat) {
                    player.removePieceToPlayer(pieceDomino);
                    setNextTurn(player);
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Verifie tout les possibilites de compatibilite.
     * @param player Le jouer dont c'est le tour.
     * @param pieceDomino Piece a placer.
     * @return Compatibilite avec le plateau(ses extremites)
     */

    protected boolean compatibility(PlayerDomino player, U pieceDomino) {
        PieceDomino first = plateau.getFirst();
        PieceDomino last = plateau.getLast();
        if (pieceDomino.getValue1() == first.getValue1() && !first.getCouple1()) {
            first.setCouple1(true);
            pieceDomino.setCouple1(true);
            pieceDomino.changer();
            plateau.addFirst((U)pieceDomino);
            return true;
        } else if (pieceDomino.getValue2() == first.getValue2() && !first.getCouple2()) {
            first.setCouple2(true);
            pieceDomino.setCouple2(true);
            pieceDomino.changer();
            plateau.addFirst((U)pieceDomino);
            return true;
        } else if (pieceDomino.getValue2() == first.getValue1() && !first.getCouple1()) {
            first.setCouple1(true);
            pieceDomino.setCouple2(true);
            plateau.addFirst((U)pieceDomino);
            return true;
        } else if (pieceDomino.getValue1() == first.getValue2() && !first.getCouple2()) {
            first.setCouple2(true);
            pieceDomino.setCouple1(true);
            plateau.addFirst((U)pieceDomino);
            return true;
        } else if (pieceDomino.getValue1() == last.getValue1() && !last.getCouple1()) {
            last.setCouple1(true);
            pieceDomino.setCouple1(true);
            pieceDomino.changer();
            plateau.addLast((U)pieceDomino);
            return true;
        } else if (pieceDomino.getValue2() == last.getValue2() && !last.getCouple2()) {
            first.setCouple2(true);
            pieceDomino.setCouple2(true);
            pieceDomino.changer();
            plateau.addLast((U)pieceDomino);
            return true;
        } else if (pieceDomino.getValue2() == last.getValue1() && !last.getCouple1()) {
            last.setCouple1(true);
            pieceDomino.setCouple2(true);
            plateau.addLast((U)pieceDomino);
            return true;
        } else if (pieceDomino.getValue1() == last.getValue2() && !last.getCouple2()) {
            last.setCouple2(true);
            pieceDomino.setCouple1(true);
            plateau.addLast((U)pieceDomino);
            return true;
        }
        return false;
    }

    /** 
     * Getters 
     */
    public boolean getStuck(){
        return stuck;
    }

    public ArrayList<E> getPlayers(){
        return players;
    }

    public LinkedList<U> getPlateau(){
        return plateau;
    }

    public LinkedList<U> getPieces(){
        return plateau;
    }
}