import java.awt.*;

/**
 * PieceGommette est une piece de domino,
 * avec un propriete en plus, comme la couleur.
 */

public class PieceGommette extends PieceDomino {

    /**Couleur utilisé par la piece domino gommette */
    private Color color1;
    private Color color2;
    
    public PieceGommette(int v1, int v2, Color c1, Color c2) {
        super(v1, v2);
        color1 = c1;
        color2 = c2;
    }

    public void changer(){
        super.changer();
        Color color0 = color1;
        color1 = color2;
        color2 = color0;
    }

    public String toString() {
        return "Piece: " + value1 + " de couleur: " + color1 + " et " + value2 + " de couleur: " + color2 + ".";
    }
    /** 
     * Getters et Setters 
     */
    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    public String getHex1(){
        return "#"+Integer.toHexString(color1.getRGB()).substring(2);
    }

    public String getHex2(){
        return "#"+Integer.toHexString(color2.getRGB()).substring(2);
    }

    public static String getRgbToHex(Color c){
        return "#"+Integer.toHexString(c.getRGB()).substring(2);
    }
}