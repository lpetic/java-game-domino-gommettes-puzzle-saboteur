import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Permet de controler les pieces de 
 * puzzle dans la fenetre.
 */
public class ControllerPuzzle{

    private ViewPuzzle view;
    private Puzzle model;
    private ControllerPiece mouse;

    public ControllerPuzzle(ViewPuzzle v, Puzzle m) {
        view = v;
        model = m;
        mouse = new ControllerPiece();
    }
    /** 
     * Getters 
     */
    public ControllerPiece getControllerPiece(){
        return mouse;
    }
 
    private class ControllerPiece implements MouseListener, MouseMotionListener {

        private Point point;

        public ControllerPiece() {
            point = new Point(0, 0);
        }
        /**
         * Permet de bouger une piece afin 
         * d'organiser son jeu de puzzle.
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            point = e.getPoint();
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

        @Override
        public void mouseClicked(MouseEvent e){
            point = e.getPoint();
            JLabel l = (JLabel) e.getSource();
            view.setLabel(l);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
