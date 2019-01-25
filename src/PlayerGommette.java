public class PlayerGommette extends Player<PieceGommette>{

    public PlayerGommette(String n){
        super(n);
    }

    public boolean exist(PieceGommette piece){
        for (PieceGommette p : pieces) {
            if(p.getValue1()==piece.getValue1() && p.getValue2()==piece.getValue2()){
                return true;
            }
        }
        return false;
    }

}