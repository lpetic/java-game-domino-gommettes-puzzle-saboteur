import javax.swing.*;
import java.awt.*;
/**
 * Classe de l'interface graphique du jeu: gommette.
 */
public class ViewGommette extends ViewDomino{

    private static String FORM [] = {"*", "&", "#", "@", "%", "$", "^"};

    public ViewGommette(final ViewMain v){
        super(v);
    }
    /**
     * Separation des fonction pour des question 
     * d'heritare et redefinition.
     * Initialisation des informations des joeurs.
     */
    @SuppressWarnings("unchecked")
    protected void initInfo(){
        PlayerDomino [] player = playersInfo();
        domino = new Gommette(player);
        controller = new ControllerDomino(this, domino);
    }
    /**
     * Creer des pieces JLabel a parir d'une piece du model.
     * @param v1 valeur1 du domino
     * @param v2 valeur2 du domino
     * @param c1 couler1 du domino
     * @param v2 couler2 du domino
     */
    protected JLabel createPiece(int v1, int v2, Color c1, Color c2){
        String strC1 = PieceGommette.getRgbToHex(c1);
        String strC2 = PieceGommette.getRgbToHex(c2);
        JLabel piece = new JLabel("<html><h1 style='background-color: black'><font color='"+strC1+"'>"+FORM[v1]+"</font><font color='"+strC2+"'>"+FORM[v2]+"</font></h1></html>");
        piece.setName(""+v1+strC1+v2+strC2);
        piece.addMouseListener(controller.getControllerPiece());
        piece.addMouseMotionListener(controller.getControllerPiece());
        return piece;
    }
    /**
     * Afficher les pieces de domino
     */
    protected void showPieces(){
        for (PlayerDomino player : domino.getPlayers()) {
           for (int i=0; i<player.getPieces().size(); i++) {
                PieceGommette piece = (PieceGommette) player.getPieces().get(i);
                JLabel p = createPiece(piece.getValue1(), piece.getValue2(), piece.getColor1(), piece.getColor2());
                int id = player.getId();
                panel[id].add(p);
            } 
        }
    }
    /**
     * Extrait les information des JLabel 
     * et fait le lien entre le model et la vue
     * @param s Nom du Jlabel
     * @return 
     */
    protected PieceGommette extract(String s){
        char char1 = s.charAt(0);
        char char2 = s.charAt(8);
        int v1 = Character.getNumericValue(char1);
        int v2 = Character.getNumericValue(char2);
        int  r1 = Integer.valueOf(s.substring(2,4),16);
        int  g1 = Integer.valueOf(s.substring(4,6),16);
        int  b1 = Integer.valueOf(s.substring(6,8),16);
        int  r2 = Integer.valueOf(s.substring(10,12),16);
        int  g2 = Integer.valueOf(s.substring(12,14),16);
        int  b2 = Integer.valueOf(s.substring(14,16),16);
        return new PieceGommette(v1,v2,new Color(r1,g1,b1),new Color(r2,g2,b2));
    }
    /**
     * Ajouter une piece a un jouer du point de vue, du model et de la vue.
     */
    public void addPieceToPlayer(){
        for (PlayerDomino player : domino.players){
            if(player.getTurn()){
                PieceGommette piece = (PieceGommette)domino.randomPieceDomino();
                JLabel p = createPiece(piece.getValue1(), piece.getValue2(), piece.getColor1(), piece.getColor2());
                player.addPieceToPlayer(piece);
                panel[player.getId()].add(p, BorderLayout.CENTER);
                update();
            }
        }
    }
    /**
     * Met une piece dans le JPanel central, c'est a dire le plateau.
     * @param l 
     */
    public void setOnPlateau(JLabel l){
        PieceGommette piece = (PieceGommette)extract(l.getName());
        for (PlayerDomino player : domino.players){
            if(player.getTurn()){
                if(domino.setOnPlateau(player, piece)){
                    l.setVisible(false);
                    JLabel p = createPiece(piece.getValue1(), piece.getValue2(), piece.getColor1(), piece.getColor2());
                    add(p);
                    domino.setNextTurn(player);
                    whichTurn();
                    update();
                }
            }
        }
    }
}