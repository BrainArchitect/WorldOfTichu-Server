package cards;

import java.util.Comparator;

public class CardComparatorPerColorPerVal implements Comparator<Card>{

	@Override
	public int compare(Card card1, Card card2) {
		if(compareColors(card1,card2)!=0){
			return compareColors(card1,card2);
		}
		return card1.compareTo(card2);
	}
	
	private int compareColors(Card card1, Card card2) {
		return (card1.getColor()-card2.getColor());
	}
	
	
	


}
