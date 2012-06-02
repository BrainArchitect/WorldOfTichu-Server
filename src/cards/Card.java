package cards;


public class Card implements Comparable<Card>{

	//0 = Green.
	//1 = Blue.
	//2 = Red.
	//3 = Black.
	//4 = Special Card.
	private int value;
	private int color;
	
	public Card (int value,int color){
		this.value=value;
		this.color=color;
	}
	
	public int getPoints(){
		if (value==5){
			return 5;
		}else if(value==10 || value==13){
			return 10;
		}
		return 0;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public boolean isMahjong(){
		return false;
	}
	public boolean isDog(){
		return false;
	}
	public boolean isDragon(){
		return false;
	}
	public boolean isPhoenix(){
		return false;
	}
	@Override
	public int compareTo(Card other) {
		return (this.value - other.value);
	}
	
	public boolean equals(Card other){
		if(this.compareTo(other)==0){
			return true;
		}
		return false;	
	}	
}
