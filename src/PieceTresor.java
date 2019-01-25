public class PieceTresor extends PieceRoute{

	private boolean visible, tresor;

	public PieceTresor(int h, int b, int g, int d, String v, boolean t){
		super(h, b, g, d, v);
		visible = false;
		tresor = t;
	}

	public boolean isTresor(){
		visible = true;
		return tresor;
	}

	public boolean getVisible(){
		return visible;
	}

	public boolean getTresorIfVisible(){
		if(visible == true)
			return tresor;
		return false;
	}

}