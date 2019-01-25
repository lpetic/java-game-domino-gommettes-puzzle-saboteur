import java.awt.event.*;
/**
 * Classe correspondant au controleur principal 
 * du menu de demarage de l'interface graphique.
 */
public class ControllerMain implements ActionListener{
    /** JFrame principale tout au long 
     * de l'execution de l'application.
     */
    private ViewMain view;

    public ControllerMain(ViewMain v){
        view=v;
    }
    /**
     * Listener pour le choix du jeu a demarrer.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e){   
        String c = e.getActionCommand();  
        if(c.equals("dom")){
            new ViewDomino(view);
        }else if(c.equals("gom")){
            new ViewGommette(view);  
        }else if(c.equals("puz")){
            new ViewPuzzle(view);
        }else if(c.equals("sab")){
            //Saboteur.startGame();
        }
        view.getPanel().setVisible(false);
    }
    
}