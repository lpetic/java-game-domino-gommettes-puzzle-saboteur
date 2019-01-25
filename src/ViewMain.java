import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Classe de l'interface graphique de l'application generale.
 */
public class ViewMain extends JFrame{
    /** Container de l'interieur le JFrame */
    private Container container;
    /** JPanel principal */
    private JPanel panel;
    /** JLabel qui represente le nom de l'application */
    private JLabel label;
    /** Les boutons de navigation pour acceder aux jeux */
    private JButton [] button;
    /** Regroupement des actionCommande des boutons pour une meilleure organisation */
    private static final String [] BUTTON_ACTION_COMMAND = {"dom", "gom", "puz", "sab"};
    private static final String [] BUTTON_NAME = {"Domino", "Domino Gommette", "Puzzle", "Saboteur"};

    public ViewMain(){
        super("COLLECTION DE JEUX");
        container = getContentPane();
        frame();
        panel();
        label();
        button();
    }
    /**
     * Regroupement des fonction de la JFrame.
     */
    private void frame(){
        setPreferredSize(new Dimension(1000, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        icon();
        centerWindow();
        //background();
    }
    /**
     * Creation du title de l'application.
     */
    private void label(){
        label = new JLabel(getTitle());
        label.setForeground(Color.white);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label, BorderLayout.CENTER);
    }
    /**
     * Organisation du JPanel central
     */
    private void panel(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.GRAY);
        panel.setSize(new Dimension(200,200));
        container.add(panel, BorderLayout.CENTER);
    }
    /**
     * Creation et mise en place des boutons.
     */
    private void button(){
        ControllerMain controller = new ControllerMain(this);
        button = new JButton [BUTTON_NAME.length];
        for (int i = 0; i<button.length;i++){
            button[i]=new JButton(BUTTON_NAME[i]);
            button[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            button[i].setActionCommand(BUTTON_ACTION_COMMAND[i]);
            button[i].addActionListener(controller);
            panel.add(button[i], BorderLayout.CENTER);
        }
    }
    /**
     * Chargement de l'icone.
     */
    private void icon() {
        Image img = Toolkit.getDefaultToolkit().getImage("../img/frame/logo.png");
        setIconImage(img);
    }
    /**
     * Une image pour le font, mais pas mise en place 
     * a cause des problemes d'affichage.
     */
    private void background() {
        ImageIcon img = new ImageIcon("../img/frame/bg.jpg");
        JLabel bg = new JLabel("", img, JLabel.CENTER);
        bg.setBounds(0, 0, 1080, 1920);
        container.add(bg);
    }
    /**
     * Ouverture de la fenetre au milieu de l'ecran.
     */
    private void centerWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }
    /**
     * Getters
     */
    public JButton getButton(int i){
        return button[i];
    }

    public static String getButtonName(int i){
        return BUTTON_NAME[i];
    }

    public JPanel getPanel(){
        return panel;
    }
}