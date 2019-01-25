import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Cette classe est responsable des interations 
 * avec l'utilisateur pour les jeux de dominos.
 */
public class ControllerDomino {

    /** Reprise de la vue principale */
    private ViewDomino view;
    /** Prise en charge du model correspondant */
    private Domino model;
    /** Utilisation d'un listener pour 
     * les evenement de la souris
     */
    private ControllerPiece mouse;
    /** Listener pour le bouton 'Piocher!' */
    private ControllerDig dig;

    public ControllerDomino(ViewDomino v, Domino m) {
        view = v;
        model = m;
        mouse = new ControllerPiece();
        dig = new ControllerDig();
    }
     /** 
     * Getters
     */
    public ControllerPiece getControllerPiece() {
        return mouse;
    }

    public ControllerDig getControllerDig() {
        return dig;
    }
    /**
     * Classes internes pour separer les fontionalites du listener. 
     */
    private class ControllerDig implements ActionListener {
        /**
         * Cette fonction permet de piocher une piece de domino,
         * si l'utilisateur en demande une.
         * Elle surveille aussi la disponibilité des 
         * pieces dans la pioche.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (!model.domino.isEmpty()) {
                if(command.equals("dig")){
                    view.addPieceToPlayer();
                }
            }
            if (model.domino.isEmpty()) {
                view.setDigDisable();
            }
        }
    }

    private class ControllerPiece implements MouseListener, MouseMotionListener {
        /**Localisation des coordonnees de la souris en temps reel */
        private Point point;

        public ControllerPiece() {
            point = new Point(0, 0);
        }

        /**
         * Permet de bouger une piece afin 
         * d'organiser son jeu, si besoin.
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            point = e.getPoint();
            JLabel l = (JLabel) e.getSource();
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            int x = e.getX() + e.getComponent().getX() - (int) point.getX();
            int y = e.getY() + e.getComponent().getY() - (int) point.getY();
            JLabel l = (JLabel) e.getSource();
            l.setLocation(x, y);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }
        /**
         * Met une piece sur le plateau, 
         * si jamais elle est compatible.
         */
        @Override
        public void mouseClicked(MouseEvent e){
            point = e.getPoint();
            JLabel l = (JLabel) e.getSource();
            if(!l.getName().equals("")){
                view.setOnPlateau(l);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
