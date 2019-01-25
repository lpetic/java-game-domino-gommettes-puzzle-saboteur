import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
/**
 * Classe de l'interface graphique du jeu: puzzle.
 */
public class ViewPuzzle extends JPanel{
    /** JFrame generale*/
    protected ViewMain view;
    /** Container de l'interieur le JFrame obtenu avec: getContentPane()*/
    protected Container container;
    /** Controleur du jeu */
    protected ControllerPuzzle controller;
    /** Model */
    protected Puzzle puzzle;
    protected LinkedList<PieceV> pieces;
    protected JPanel panelPiece;
    protected BufferedImage image;
    protected JLabel saved;
    protected static final int WIDTH=3;
    protected static final int HEIGHT=3;

    public ViewPuzzle(final ViewMain v){
        view=v;
        container = view.getContentPane();
        panelPiece = new JPanel();
        panelPiece.setLayout(new GridLayout(5,5));
        saved = new JLabel();

        for(int k = 0; k < 25; k++){
            JButton btn = new JButton();
            btn.setPreferredSize(new Dimension(120, 120));

            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    PieceV s = getSavedPiece();
                    if(btn.getIcon()!=null){
                        for (PieceV p : pieces) {
                            if(p.getLabel().getIcon().equals(btn.getIcon())){
                                p.getLabel().setVisible(true);
                            }
                        }
                        btn.setIcon(null);
                    }
                    if(s != null){
                        btn.setIcon(s.l.getIcon());
                        setLabel(null);      
                    }
                    repaint();
                    revalidate(); 
                }
            });
            panelPiece.add(btn);
        } 

    
        container.add(this, BorderLayout.NORTH);
        container.add(panelPiece, BorderLayout.SOUTH);

        setBackground(Color.GRAY);
        controller = new ControllerPuzzle(this, puzzle);
        pieces = new LinkedList<>();
        try {
            image=ImageIO.read(new File("../img/puzzle/pika.png")); 
        } catch (Exception e) {
            System.out.println(e.getMessage());    
        }
        startGame();
    }
    /**
     * Demarrage du jeu
     */
    protected void startGame(){
        cutting();
        for (PieceV p : pieces) {
            add(p.l);
        }
    }
    /**
     * Decoupage de l'image afin d'obtenir des pieces puzzle
     */
    protected void cutting(){
        for(int i=0; i < WIDTH; i ++){
            for(int j=0; j < HEIGHT; j ++){
                BufferedImage part = image.getSubimage(i*(int)image.getHeight()/HEIGHT,j*(int)image.getWidth()/WIDTH,(int)image.getHeight()/HEIGHT,(int)image.getWidth()/WIDTH); 
                JLabel icon = new JLabel("");
                icon.setIcon(new ImageIcon(part));
                icon.setName(""+i+j);
                icon.addMouseListener(controller.getControllerPiece());
                icon.addMouseMotionListener(controller.getControllerPiece());
                pieces.add(new PieceV(icon,new PiecePuzzle(1,1,1,1,"Piece")));
                //Espace entre les pieces
            }
        }
    }

    public PieceV getPieceFromLabel(JLabel j){
            for(int k = 0; k < pieces.size(); k++){
                if(j == pieces.get(k).l)
                    return pieces.get(k);
            }
            return null;
        }
    /**
     * Sauvegarder la piece choisie par l'utilisateur
     */
    public PieceV getSavedPiece(){
        if(getLabel()!=null){
            getLabel().setVisible(false);
            return getPieceFromLabel(getLabel());
        }
        return null;
    }
    /**
     * Liaison entre le model la va vue
     */
    public class PieceV{

        JLabel l;
        PiecePuzzle p;

        public PieceV(JLabel l, PiecePuzzle p){
            this.l = l;
            this.p = p;
        }

        public JLabel getLabel(){
            return l;
        }
    }

    public void setLabel(JLabel l){
        saved = l;
	}

	public JLabel getLabel(){
		return saved;
	}
}
