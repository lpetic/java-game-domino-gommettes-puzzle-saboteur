import javax.swing.*;
import java.awt.*;
import java.util.*;
/**
 * Classe de l'interface graphique du jeu: domino.
 */
public class ViewDomino extends JPanel{

    protected VueGenerale vue;
    /** JFrame generale*/
    protected ViewMain view;
    /** Container de l'interieur le JFrame obtenu avec: getContentPane()*/
    protected Container container;
    /** Controleur du jeu */
    protected ControllerDomino controller;
    /** Model */
    protected Domino<PieceDomino, PlayerDomino> domino;
    /** Panel des joeurs */
    protected JPanel [] panel;
    /** JLabel qui informe sur le tour des joeurs */
    protected JLabel turn;
    /** Constantes pour l'oraganisation et le design du contaier */
    protected static final String [] POSITION = {BorderLayout.LINE_START, BorderLayout.LINE_END, BorderLayout.PAGE_START, BorderLayout.PAGE_END, BorderLayout.CENTER};
    protected static final Color [] COLOR_PLAYERS = {Color.MAGENTA, Color.ORANGE, Color.BLUE, Color.GREEN};

    public ViewDomino(final ViewMain v){
        vue = new VueGenerale();
        view=v;
        container = view.getContentPane();
        container.add(this);
        initInfo();
        initGraphics();
        startGame();
    }
    /**
     * Initialisation des informations des joeurs.
     */
    protected void initInfo(){
        PlayerDomino [] player = playersInfo();
        domino = new Domino<>(player);
        controller = new ControllerDomino(this, domino);
    }
    /**
     * Initialisation de l'interface graphique.
     */
    protected void initGraphics(){
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(800,800));
        turn = new JLabel("");
        turn.setForeground(Color.WHITE);
        add(turn);
        /** Verification du besoin d'un bouton 'Piocher!'.*/
        if(domino.getPlayers().size()!=Domino.MAX_PLAYERS){
            JButton dig = new JButton(VueGenerale.dominoString(9));
            dig.setActionCommand("dig");
            dig.addActionListener(controller.getControllerDig());
            add(dig, POSITION[2]);
        }
    }
    /**
     * Regroupement des fonction pour lancer la partie.
     */
    protected void startGame(){
        domino.startGame();
        createPanelsForPlayers();
        showPieces();
        whichTurn();
    }
    /**
     * Indique quelle joueur a gagne
     */
    public void whoWin(){
        for (PlayerDomino player : domino.getPlayers()) {
            String name = player.getName();
            if(player.getPieces().isEmpty()){
                JLabel l = new JLabel(name + VueGenerale.dominoString(10));
                l.setForeground(Color.RED);
                add(l);
            }
            update();
        }
    }
    /**
     * Iteration du jeu
     */
    public void whichTurn(){
        for (PlayerDomino p : domino.players){
            if(p.getTurn()){
                turn.setText((VueGenerale.dominoString(5)+p.getName()));
                whoWin();
                update();
            }
        }
    }
    /**
     * Extrait les information des JLabel 
     * et fait le lien entre le model et la vue
     * @param s Nom du Jlabel
     * @return 
     */
    protected PieceDomino extract(String s){
        char c1 = s.charAt(0);
        char c2 = s.charAt(1);
        int v1 = Character.getNumericValue(c1);
        int v2 = Character.getNumericValue(c2);
        return new PieceDomino(v1,v2);
    }
    /**
     * Met une piece dans le JPanel central, c'est a dire le plateau.
     * @param l 
     */
    public void setOnPlateau(JLabel l){
        PieceDomino piece = extract(l.getName());
        for (PlayerDomino player : domino.players){
            if(player.getTurn()){ //revoir
                if(domino.setOnPlateau(player, piece)){
                    l.setVisible(false);
                    JLabel p = createPiece(piece.getValue1(), piece.getValue2());
                    p.removeMouseListener(controller.getControllerPiece());
                    p.setName("");
                    add(p);
                    domino.setNextTurn(player);
                    whichTurn();
                    update();
                }
            }
        }
    }
    /**
     * Boites de dialogue, qui recuperent 
     * des informations sur les jouers
     * @return
     */
    protected PlayerDomino [] playersInfo(){
        String [] CHOISE_PLAYERS = {"2","3","4"};
        int choise = JOptionPane.showOptionDialog(null,VueGenerale.dominoString(0),ViewMain.getButtonName(0), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, CHOISE_PLAYERS,CHOISE_PLAYERS[0]);
        int numberPlayers = Integer.parseInt(CHOISE_PLAYERS[choise]);
        PlayerDomino [] players = new PlayerDomino [numberPlayers];
        for(int i=0; i<players.length; i++){
            int n = i+1;
            String name = JOptionPane.showInputDialog(null, VueGenerale.dominoString(1)+" "+n);
            if(!name.isEmpty()){
                players[i] = new PlayerDomino(name.toUpperCase());
            }else{
                i--;
            }
        }
        return players;
    }
    /**
     * Creation a part de chaque JPanel de chaque joeur.
     * Pour organiser bien le rendu a l'ecran.
     */
    protected void createPanelsForPlayers(){
        ArrayList<PlayerDomino> player = domino.getPlayers();
        panel = new JPanel [player.size()];
        for (int i=0; i< player.size();i++){
            panel[i] = new JPanel();
            panel[i].setBackground(COLOR_PLAYERS[i]);
            panel[i].setPreferredSize(new Dimension(150,150));
            JLabel name = new JLabel(player.get(i).toString());
            name.setForeground(Color.WHITE);
            panel[i].add(name);
            container.add(panel[i],POSITION[i]);
        }
    }
    /**
     * Afficher les pieces de domino
     */
    protected void showPieces(){
        for (PlayerDomino player : domino.players) {
            for (PieceDomino piece : player.getPieces()) {
                JLabel p = createPiece(piece.getValue1(), piece.getValue2());
                int id = player.getId();
                panel[id].add(p);
            }
        }
    }
    /**
     * Creer des pieces JLabel a parir d'une piece du model.
     * @param v1 valeur1 du domino
     * @param v2 valeur2 du domino
     */
    protected JLabel createPiece(int v1, int v2){
        ImageIcon img = new ImageIcon("../img/domino/domino"+v1+v2+".jpg");
        JLabel piece = new JLabel(scaleImage(img, (int)(img.getIconWidth()*0.5), (int)(img.getIconHeight()*0.5)));
        piece.setName(""+v1+v2);
        piece.addMouseListener(controller.getControllerPiece());
        piece.addMouseMotionListener(controller.getControllerPiece());
        return piece;
    }
    /**
     * Permet de redimentioner une ImageIcon
     * @param icon
     * @param w
     * @param h
     * @return
     */
    private static ImageIcon scaleImage(ImageIcon icon, int w, int h){
        return new ImageIcon(icon.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
    }
    /**
     * Ajouter une piece a un jouer du point de vue, du model et de la vue.
     */
    public void addPieceToPlayer(){
        for (PlayerDomino player : domino.players){
            if(player.getTurn()){
                PieceDomino piece = domino.randomPieceDomino();
                JLabel p = createPiece(piece.getValue1(), piece.getValue2());
                player.addPieceToPlayer(piece);
                panel[player.getId()].add(p, BorderLayout.CENTER);
                update();
            }
        }
    }
    /**
     * Reactualise l'affichage des composants.
     */
    public void update(){
        repaint();
        revalidate();
    }
    /**
     * Desactivation du bouton 'Piocher!'.
     */
    public void setDigDisable(){
        Component[] components = getComponents();
        for(int j=0; j<components.length; j++){
            if(components[j] instanceof JButton){
                components[j].setEnabled(false);
            }
        }
    }
}
