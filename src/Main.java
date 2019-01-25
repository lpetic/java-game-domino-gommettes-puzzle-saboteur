import javax.swing.*;
import java.util.*;
/**
 * Classe principale qui lance l'application.
 */
public class Main implements Runnable {
    @Override
    public void run(){
        ViewMain w = new ViewMain();
        w.setVisible(true);
    }
    /**
     * Fonction qui permet de lancer soit l'application 
     * en mode textuel, soit en interface graphique.
     */
    public static void launcher(){
        System.out.println("Choissisez le mode du jeu :  1 - Textuel");
        System.out.println("		  	     2 - Graphique");
        
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        
		if(choix == 1){
			JeuTextuel.choixJeu();
		}else if(choix == 2){
            SwingUtilities.invokeLater(new Main());  
		}else{
            System.out.println("Entrez une valeur coherente, 1 ou 2");
            launcher();
        }
    }
    public static void main(String[] args) {
        launcher();
    }
}