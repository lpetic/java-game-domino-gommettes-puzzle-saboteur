import java.util.*;

public class JeuTextuel{

	static Scanner sc = new Scanner(System.in);

	public static void choixJeu(){
		System.out.println("Choissisez votre jeu :  1 - Domino");
		System.out.println("		  	2 - Domino Gommette");
		System.out.println("		  	3 - Puzzle");
		System.out.println("			4 - Saboteur");

		int choix = sc.nextInt();

		if(choix == 1){
			jeuDomino();
		}
		if(choix == 2){
			//jeuGommette();
		}
		if(choix == 3){
			jeuPuzzle();
		}
		if(choix == 4){
			jeuSaboteur();
		}
	}

	public static void jeuPuzzle(){
		Puzzle jeu = new Puzzle(new Player("Benny"));

		while(jeu.plusDePieces() == false){
			jeu.jouerTour();
		}
	}

	public static void jeuDomino(){
		System.out.println(VueGenerale.dominoString(0));
		int choise = sc.nextInt();
		if(!Domino.numberWrong(choise)){
			PlayerDomino [] players = new PlayerDomino[choise];
			for (int i=0; i<players.length; i++) {
				int n = i+1;
				System.out.println(VueGenerale.dominoString(1)+" "+n);
				String name="";
				try {
					Scanner scr = new Scanner(System.in);
					name = scr.nextLine();
				} catch (Exception e) {
					System.out.println(VueGenerale.dominoString(3));;
				}
				if(!name.isEmpty()){
					players[i] = new PlayerDomino(name.toUpperCase());
				}else{
					System.out.println(VueGenerale.dominoString(3));
					i--;
				}
			}
			Domino<PieceDomino,PlayerDomino> domino = new Domino<>(players);
			domino.startGame();
			while(!domino.getStuck()){
				domino.iterationOfTurn();
			}
		}else{
			System.out.println(VueGenerale.dominoString(2));
			jeuDomino();			
		}
	}

	public static void jeuSaboteur(){

		Saboteur jeu = new Saboteur(arrayJoueurSaboteur());
		System.out.println("Ajout des cases tresors");
		jeu.addTresors();
		System.out.println("Lancement de la partie.");
		jeu.distributionOfPieces();
		System.out.println("Distribution des cartes");
		jeu.pickSaboteurs(3);
		System.out.println("Selection des saboteurs");
		jeu.selectStarter();
		System.out.println("Selection de la personne qui commence");
		System.out.println();
		
		while(jeu.tresorTrouve() == false){
			jeu.jouerTour();
		}
	}

	public ArrayList<Player> arrayPlayer(){
		System.out.println("Tapez 'stop' pour commencer la partie");
		System.out.println("Nom du nouveau joueur");

		String mot = sc.nextLine();
		ArrayList<Player> liste = new ArrayList<>();

		while(!(mot.equals("stop"))){
			liste.add(new Player(mot));
			System.out.println("Nom du nouveau joueur");
			mot = sc.nextLine();
		}

		return liste;
	}

	public static ArrayList<JoueurSaboteur> arrayJoueurSaboteur(){
		System.out.println("Tapez 'stop' pour commencer la partie");

		ArrayList<JoueurSaboteur> liste = new ArrayList<>();
		String mot = "";
		mot = sc.nextLine();


		while(!(mot.equals("stop")) && liste.size() < 7){
			System.out.println("Nom du nouveau joueur");
			mot = sc.nextLine();
			if(!(mot.equals("")) && !(mot.equals("stop")))
				liste.add(new JoueurSaboteur(mot));

			if(mot.equals("stop") && liste.size() < 5){
				System.out.println("Vous n'avez pas encore assez de joueurs !");
				mot = "";
			}
		}	
		return liste;
	}
}