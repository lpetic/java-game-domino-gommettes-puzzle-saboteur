# Introdution

    Le fichier de la racine contient tout les élements nécessaires 
    pour le bon fonctionement de l'application.
    L'arborescence est la suivante:
        ├───img
        │   ├───domino
        │   ├───frame
        │   └───puzzle
        └───src
    Avec /img qui contient toutes les images
    et /src tout le code source.

# Fonctionement 

    Pour exécuter l'application il suffit de se rendre 
    dans le dossier \src et de faire: 
        1) javac Main.java
        2) java Main

    En cas de problème, il peut être préférable de tenter : javac *.java avant de relancer : java Main

    Lors de exécution du Main, vous avez le choix entre le mode:
        1) Textuel où 3/4 des jeux sont disponibles (Domino, Puzzle et Saboteur).
        2) Graphique où 3/4 des jeux sont disponibles (Domino, Gommette et Puzzle).

    Utilisation des jeux :

    Puzzle : 

        Textuel :

            Un seul joueur, pieces prédéfinies.
            3 choix par tour, placer une piece, enlever une piece, tourner une piece de 90° depuis sa main.
            Placer et enlever une piece demandent des coordonnées x et y. Des indicateurs sont placés sur le plateau afin de facilement reconnaitre les emplacements disponibles.
            Le reste des indications sont données durant la partie.

        Graphique :

            Cliquer sur une pièce pour la selectionner.
            Cliquer sur une case pour y placer la pièce selectionnée.
            Cliquer sur une case déjà prise enleve la piece et la rajoute a la main.

    Saboteur :

        Textuel : 

            Nombre de joueurs entre 5 et 7, taper stop avant l'ajout des 7 joueurs pour commencer.
            Le joueur qui commence est choisis au hasard, ainsi que les saboteurs.
            Former une route jusqu'au vrai tresor remporte la partie pour les non saboteurs.
            Le jeu est joué en tour par tour.
            Instructions similaires a puzzle. Des coordonnées x et y sont demandées, des indicateurs sont présents et lors de la selection d'une pièce, un affichage des positions possible est donné.
            Les parties libres des cases trésor sont données lors de l'affichage avec une piece selectionnée.
            Le reste des indications sont données durant la partie.

    Domino :

        Textuel :

            Jeu en tour par tour.
            Chaque joueur peux placer une pièce si celle ci est compatible avec les pièces déjà présentes aux extrémités du terrain. La piece se place automatiquement en la selectionnant.
            Un joueur sans piece compatible doit piocher autant de fois que necessaire.
            Le premier joueur qui n'a plus de piece gagne automatiquement la partie

        Graphique :

            Même règle que Domino Textuel

    Domino Gommette :

        Graphique :

            Même fonctionnement que Domino, en ajoutant évidemment les règles du Domino Gommette
    
    
    

    


