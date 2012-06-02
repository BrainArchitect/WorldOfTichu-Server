package cards;

public class Dragon extends Card{

	public Dragon(int value, int color) {
		super(value, color);
	}

	public boolean isDragon(){
		return true;
	}

	public int getPoints(){
		return 25;
	}
}
