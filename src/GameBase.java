/**
 * Les jeux implementes sont des jeux de plateau, et de pieces.
 * A un moment donnee il faut creer les pieces et les distribuer.
 */
public interface GameBase{
    /** Creation des pieces */
    public void createPieces();
    /** Distribution des pieces*/
    public void distributionOfPieces();

}